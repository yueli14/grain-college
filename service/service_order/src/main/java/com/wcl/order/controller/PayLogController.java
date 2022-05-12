package com.wcl.order.controller;


import com.wcl.order.service.PayLogService;
import com.wcl.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author wcl
 * @since 2022-05-05
 */
@CrossOrigin
@RestController
@RequestMapping("/eduorder/pay/user")
public class PayLogController {
    @Autowired
    private PayLogService payLogService;

    /**
     * 根绝订单号创建微信支付二维码
     *
     * @param orderNo
     * @return
     */
    @GetMapping("get/code/{orderNo}")
    public R getCode(@PathVariable String orderNo) {
        Map<String, Object> code = payLogService.createNativeCode(orderNo);
        if (code == null) {
            return R.error().message("创建二维码失败");
        }
        return R.ok().data(code);
    }

    /**
     * 根据订单号返回支付状态
     *
     * @param orderNo
     * @return
     */
    @GetMapping("/get/pay/status/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {
        //调用查询接口
        Map<String, String> map = payLogService.getPayStatus(orderNo);
        if (map == null) {//出错
            return R.error().message("支付出错");
        }
        if (map.get("trade_state").equals("SUCCESS")) {//如果成功
            //更改订单状态
            return payLogService.updateOrderStatus(map) ? R.ok().message("支付成功") : R.error().message("支付失败");

        }
        return R.ok().code(25000).message("支付中");
    }
}

