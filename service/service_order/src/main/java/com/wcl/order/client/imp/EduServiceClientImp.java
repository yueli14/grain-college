/**
 * @Classname EduServiceClientImp
 * @Description TODO
 * @Date 2022/5/5 16:15
 * @Created by 28327
 */

package com.wcl.order.client.imp;

import com.wcl.order.client.EduServiceClient;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EduServiceClientImp implements EduServiceClient {
    @Override
    public Map<String, String> getCourseAndTeacherInfoByCourseId(String courseId) {
        return null;
    }

    @Override
    public Boolean addBuyCount(String courseId) {
        return null;
    }
}