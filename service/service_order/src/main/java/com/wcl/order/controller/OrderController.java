package com.wcl.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wcl.order.entity.OrderEntity;
import com.wcl.order.service.OrderService;
import com.wcl.utils.JwtUtil;
import com.wcl.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author wcl
 * @since 2022-05-05
 */
@CrossOrigin
@RestController
@RequestMapping("/eduorder/order/user")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 生成订单
     *
     * @return
     */
    @PostMapping("create/order/{courseId}")
    public R createOrder(@PathVariable String courseId, HttpServletRequest request) {
        String token = JwtUtil.getMemberIdByJwtToken(request);
        if (!StringUtils.hasLength(token)) {
            return R.error().message("请先登录");
        }
        String orderNo = orderService.createOrderByCourseId(courseId, token);
        return R.ok().data("orderNo", orderNo);
    }

    /**
     * 查询
     */
    @GetMapping("get/order/{orderNo}")
    public R getOrderInfo(@PathVariable String orderNo) {
        QueryWrapper<OrderEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderNo);
        OrderEntity order = orderService.getOne(wrapper);
        return R.ok().data("order", order);
    }

    @GetMapping("get/order/stutas/{courseId}/{memberId}")
    public Boolean getOrderInfo(@PathVariable String courseId, @PathVariable String memberId) {
        QueryWrapper<OrderEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId)
                .eq("member_id", memberId)
                .eq("status", 1);
        return orderService.count(wrapper) > 0;
    }
}

