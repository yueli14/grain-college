/**
 * @Classname ServiceEduMain8001
 * @Description TODO
 * @Date 2022/4/1 17:31
 * @Created by 28327
 */

package com.wcl.serviceedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
//开启数据库事务管理
@EnableTransactionManagement
////扫描导入进来的swagger配置
//@EnableWebMvc
@ComponentScan(basePackages = {"com.wcl"})
public class ServiceEduMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceEduMain8001.class, args);
    }
}