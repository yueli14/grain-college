/**
 * @Classname UCenterMain8160
 * @Description TODO
 * @Date 2022/4/23 13:18
 * @Created by 28327
 */

package com.wcl.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = {"com.wcl"})
@SpringBootApplication
@EnableDiscoveryClient
public class UCenterMain8160 {
    public static void main(String[] args) {
        SpringApplication.run(UCenterMain8160.class, args);
    }
}