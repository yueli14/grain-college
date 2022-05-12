//package com.wcl.servicebase.config;
//
//import lombok.Data;
//
///**
// * @Classname SwaggerProperties
// * @Description Swagger3配置 spring 2.6 版本冲突
// * @Date 2022/4/18 16:48
// * @Created by 28327
// */
//
//
//import lombok.Data;
//import lombok.experimental.Accessors;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//@Data
//@Accessors(chain = true)
//
//@ConfigurationProperties("swagger")
//public class SwaggerProperties {
//    /**
//     * 是否开启swagger，生产环境一般关闭，所以这里定义一个变量
//     */
//    private Boolean enable;
//
//
//    /**
//     * 项目应用名
//     */
//    private String applicationName;
//
//
//    /**
//     * 项目版本信息
//     */
//    private String applicationVersion;
//
//
//    /**
//     * 项目描述信息
//     */
//    private String applicationDescription;
//
//
//    /**
//     * 接口调试地址
//     */
//    private String tryHost;
//
//
//}