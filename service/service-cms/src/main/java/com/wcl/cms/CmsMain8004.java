/**
 * @Classname CmsMain8004
 * @Description 用于维护前端轮播图
 * @Date 2022/4/20 16:00
 * @Created by 28327
 */

package com.wcl.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@EnableCaching
@SpringBootApplication
@ComponentScan(basePackages = {"com.wcl"})
public class CmsMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(CmsMain8004.class, args);
    }
}