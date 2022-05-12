package com.wcl.order.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPayUtil;
import com.wcl.order.client.EduServiceClient;
import com.wcl.order.entity.OrderEntity;
import com.wcl.order.entity.PayLogEntity;
import com.wcl.order.mapper.PayLogMapper;
import com.wcl.order.service.OrderService;
import com.wcl.order.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcl.order.utils.HttpClient;
import com.wcl.order.utils.WxUtil;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author wcl
 * @since 2022-05-05
 */
@Service
public class PayLogServiceImp extends ServiceImpl<PayLogMapper, PayLogEntity> implements PayLogService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private EduServiceClient eduServiceClient;

    //生成微信支付二维码
    @Override
    public Map<String, Object> createNativeCode(String orderNo) {
        try {
            //1.根据订单号查询订单信息
            QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("order_no", orderNo);
            OrderEntity order = orderService.getOne(queryWrapper);

            //2.使用map设置生成二维码需要的参数

            Map m = new HashMap<>();
            //设置支付参数
            m.put("appid", WxUtil.WX_PAY_APP_ID);
            m.put("mch_id", WxUtil.WX_PAY_MCH_ID);
            m.put("nonce_str", WXPayUtil.generateNonceStr());
            m.put("body", order.getCourseTitle());
            m.put("out_trade_no", orderNo);
            m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue() + "");
            m.put("spbill_create_ip", "127.0.0.1");
            m.put("notify_url", WxUtil.WX_PAY_NOTIFY_URL);
            m.put("trade_type", "NATIVE");

            //3.发送httpclient请求，传递参数
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            client.setXmlParam(WXPayUtil.generateSignedXml(m, WxUtil.WX_PAY_KEY));
            client.setHttps(true);
            //执行请求发送
            client.post();

            //4.得到发送请求返回结果
            //返回的内容是xml格式
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);

            //封装返回结果集
            Map<String, Object> map = new HashMap<>();
            map.put("out_trade_no", orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            map.put("result_code", resultMap.get("result_code"));//返回二维码状态码
            map.put("code_url", resultMap.get("code_url"));//二维码地址


            return map;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    //查询支付状态
    @Override
    public Map<String, String> getPayStatus(String orderNo) {
        try {
            //1、封装参数
            Map m = new HashMap<>();
            m.put("appid", WxUtil.WX_PAY_APP_ID);
            m.put("mch_id", WxUtil.WX_PAY_MCH_ID);
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());
            //2、设置请求
            HttpClient client = new
                    HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m,
                    WxUtil.WX_PAY_KEY));
            client.setHttps(true);
            client.post();
            //3、返回第三方的数据
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            //6、转成Map
            //7、返回
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @GlobalTransactional
    @Override
    public Boolean updateOrderStatus(Map<String, String> map) {
        //获取订单id
        String orderNo = map.get("out_trade_no");
        //根据订单id查询订单信息
        QueryWrapper<OrderEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderNo);
        OrderEntity order = orderService.getOne(wrapper);
        eduServiceClient.addBuyCount(order.getCourseId());
        if (order.getStatus().intValue() == 1) return true;
        order.setStatus(1);
        orderService.updateById(order);
        //记录支付日志
        PayLogEntity payLog = new PayLogEntity();
        payLog.setOrderNo(order.getOrderNo())//支付订单号
                .setPayTime(new Date())
                .setPayType(1)//支付类型
                .setTotalFee(order.getTotalFee())//总金额(分)
                .setTradeState(map.get("trade_state"))//支付状态
                .setTransactionId(map.get("transaction_id"))
                .setAttr(JSONUtil.toJsonStr(map));
        int insert = baseMapper.insert(payLog);
        return insert > 0;
        //插入到支付日志表
    }
}
