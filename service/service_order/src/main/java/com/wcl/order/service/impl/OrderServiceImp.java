package com.wcl.order.service.impl;

import com.wcl.order.client.EduServiceClient;
import com.wcl.order.client.UCenterClient;
import com.wcl.order.entity.OrderEntity;
import com.wcl.order.mapper.OrderMapper;
import com.wcl.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author wcl
 * @since 2022-05-05
 */
@Service
public class OrderServiceImp extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {
    @Autowired
    private EduServiceClient eduServiceClient;

    @Autowired
    private UCenterClient uCenterClient;
    @GlobalTransactional
    @Override
    public String createOrderByCourseId(String courseId, String token) {
        Map<String, String> userInfo = uCenterClient.getUserInfo(token);
        Map<String, String> info = eduServiceClient.getCourseAndTeacherInfoByCourseId(courseId);
        OrderEntity order = new OrderEntity();
        order.setCourseId(courseId)
                .setCourseCover(info.get("courseCover"))
                .setCourseTitle(info.get("courseTitle"))
                .setTeacherName(info.get("teacherName"))
                .setNickname(userInfo.get("nickname"))
                .setStatus(0)
                .setMobile(userInfo.get("mobile"))
                .setMemberId(userInfo.get("id"))
                .setOrderNo(System.currentTimeMillis() + order.getMobile());
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
