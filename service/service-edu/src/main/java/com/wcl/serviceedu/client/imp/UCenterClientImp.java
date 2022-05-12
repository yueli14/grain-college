/**
 * @Classname UCenterClientImp
 * @Description TODO
 * @Date 2022/5/4 17:12
 * @Created by 28327
 */

package com.wcl.serviceedu.client.imp;

import com.wcl.serviceedu.client.UCenterClient;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class UCenterClientImp implements UCenterClient {
    @Override
    public Map<String, String> getUserInfo(String id) {
        return null;
    }
}