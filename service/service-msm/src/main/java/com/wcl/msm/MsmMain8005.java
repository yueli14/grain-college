/**
 * @Classname MsmMain8005
 * @Description TODO
 * @Date 2022/4/22 19:39
 * @Created by 28327
 */

package com.wcl.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(value = {"com.wcl"})

public class MsmMain8005 {
    public static void main(String[] args) {
        SpringApplication.run(MsmMain8005.class, args);
    }
}