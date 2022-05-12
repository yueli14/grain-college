package com.wcl.serviceedu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-order", path = "/eduorder/order/user")
public interface OrderClient {
    @GetMapping("get/order/stutas/{courseId}/{memberId}")
    Boolean getOrderInfo(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}
