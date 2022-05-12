package com.wcl.order.service;

import com.wcl.order.entity.PayLogEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author wcl
 * @since 2022-05-05
 */
public interface PayLogService extends IService<PayLogEntity> {
    //返回信息，包含二维码地址，还有其他需要的信息
    Map<String, Object> createNativeCode(String orderNo);
    //返回支付状态，包含二维码地址，还有其他需要的信息
    Map<String, String> getPayStatus(String orderNo);
    //更新订单状态
    Boolean updateOrderStatus(Map<String, String> map);
}
