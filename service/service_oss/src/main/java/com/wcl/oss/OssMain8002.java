/**
 * @Classname OssMain8002
 * @Description 实现文件上传功能
 * @Date 2022/4/8 15:06
 * @Created by 28327
 */

package com.wcl.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import springfox.documentation.oas.annotations.EnableOpenApi;

/*
Failed to configure a DataSource: 'url' attribute is
 not specified and no embedded datasource could be configured.
 报错
 解决排除数据源启动项
 */
@EnableDiscoveryClient
@EnableConfigurationProperties
//@EnableOpenApi
@ComponentScan(basePackages = {"com.wcl"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class OssMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(OssMain8002.class, args);
    }
}