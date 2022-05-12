package com.wcl.serviceedu.client;

import com.wcl.serviceedu.client.imp.UCenterClientImp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Component
@FeignClient(value = "service-ucenter", path = "/educenter/center",fallback = UCenterClientImp.class)
public interface UCenterClient {
    @GetMapping("/get/info/{id}")
    Map<String, String> getUserInfo(@PathVariable String id);
}
