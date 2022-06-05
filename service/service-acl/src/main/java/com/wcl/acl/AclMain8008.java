package com.wcl.acl;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(value = {"com.wcl"})
public class AclMain8008 {
    public static void main(String[] args) {
        SpringApplication.run(AclMain8008.class, args);
    }

}
