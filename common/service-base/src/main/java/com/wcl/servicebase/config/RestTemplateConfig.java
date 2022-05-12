/**
 * @Classname RestTemplateConfig
 * @Description TODO
 * @Date 2022/4/26 16:35
 * @Created by 28327
 */

package com.wcl.servicebase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();

    }
}