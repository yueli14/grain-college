package com.wcl.order.service;

import com.wcl.order.entity.OrderEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author wcl
 * @since 2022-05-05
 */
public interface OrderService extends IService<OrderEntity> {

    String createOrderByCourseId(String courseId, String token);
}
