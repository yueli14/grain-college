/**
 * @Classname WxUtil
 * @Description TODO
 * @Date 2022/5/5 19:31
 * @Created by 28327
 */

package com.wcl.order.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WxUtil implements InitializingBean {
    @Value("${wx.pay.app_id}")
    private String appId;
    @Value("${wx.pay.mch_id}")
    private String mchId;
    @Value("${wx.pay.notify_url}")
    private String notifyUrl;
    @Value("${wx.pay.key}")
    private String key;
    public static String WX_PAY_APP_ID;
    public static String WX_PAY_MCH_ID;
    public static String WX_PAY_NOTIFY_URL;
    public static String WX_PAY_KEY;

    @Override
    public void afterPropertiesSet() throws Exception {
        WX_PAY_APP_ID = appId;
        WX_PAY_KEY = key;
        WX_PAY_MCH_ID = mchId;
        WX_PAY_NOTIFY_URL = notifyUrl;

    }
}