/**
 * @Classname VodMain8003
 * @Description TODO
 * @Date 2022/4/16 12:10
 * @Created by 28327
 */

package com.wcl.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableDiscoveryClient
@EnableConfigurationProperties
@EnableWebMvc
@ComponentScan(basePackages = {"com.wcl"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class VodMain8003 {
    public static void main(String[] args) {
        SpringApplication.run(VodMain8003.class, args);
    }
}