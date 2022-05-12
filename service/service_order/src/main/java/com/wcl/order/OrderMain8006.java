/**
 * @Classname OrderMain8006
 * @Description TODO
 * @Date 2022/5/5 15:08
 * @Created by 28327
 */

package com.wcl.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.wcl"})
@EnableDiscoveryClient
@EnableFeignClients
public class OrderMain8006 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain8006.class, args);
    }
}