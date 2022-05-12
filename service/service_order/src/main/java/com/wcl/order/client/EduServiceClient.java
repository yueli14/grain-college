/**
 * @Classname EduServiceClient
 * @Description TODO
 * @Date 2022/5/5 16:11
 * @Created by 28327
 */

package com.wcl.order.client;

import com.wcl.order.client.imp.EduServiceClientImp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Map;

@Component
@FeignClient(value = "service-edu", path = "/eduservice/course/admin", fallback = EduServiceClientImp.class)
public interface EduServiceClient {
    @GetMapping("get/course/teacher/info/{courseId}")
    Map<String, String> getCourseAndTeacherInfoByCourseId(@PathVariable("courseId") String courseId);

    @PutMapping("/add/buy/count/{courseId}")
    Boolean addBuyCount(@PathVariable("courseId") String courseId);
}