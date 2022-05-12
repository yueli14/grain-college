/**
 * @Classname GateWayMain9526
 * @Description TODO
 * @Date 2022/5/8 16:10
 * @Created by 28327
 */

package com.wcl.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GateWayMain9526 {
    public static void main(String[] args) {
        SpringApplication.run(GateWayMain9526.class, args);

    }
}