/**
 * @Classname VodUtil
 * @Description 读取属性中的常量类，解耦
 * @Date 2022/4/8 15:50
 * @Created by 28327
 */

package com.wcl.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
//自动配置属性
//yaml注入不了，无法明确原因
//@ConfigurationProperties(prefix = "aliyun.oss.file")
//@EnableConfigurationProperties()
public class VodUtil implements InitializingBean {


    @Value("${aliyun.vod.file.keyid}")
    private String keyId;
    @Value("${aliyun.vod.file.keysecret}")
    private String keySecret;


    public static String KEYSECRET;
    public static String KEYID;

    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        KEYSECRET = keySecret;
        KEYID = keyId;

    }
}