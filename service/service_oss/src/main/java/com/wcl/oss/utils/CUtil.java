/**
 * @Classname CUtil
 * @Description 读取属性中的常量类，解耦
 * @Date 2022/4/8 15:50
 * @Created by 28327
 */

package com.wcl.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//自动配置属性
//yaml注入不了，无法明确原因
//@ConfigurationProperties(prefix = "aliyun.oss.file")
//@EnableConfigurationProperties()
public class CUtil implements InitializingBean {

    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.keyid}")
    private String keyId;
    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;
//    @Value("${aliyun.oss.file.filehost}")
//    private String fileHost;
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;


    public static String ENDPOINT;
    public static String KEYID;
    public static String KEYSECRET;
    public static String BUCKETNAME;

    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = endpoint;
        KEYID =keyId;
        KEYSECRET = keySecret;
        BUCKETNAME = bucketName;

    }
}