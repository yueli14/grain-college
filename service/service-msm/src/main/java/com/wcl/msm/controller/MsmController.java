/**
 * @Classname MsmController
 * @Description TODO
 * @Date 2022/4/22 19:42
 * @Created by 28327
 */

package com.wcl.msm.controller;

import cn.hutool.core.util.RandomUtil;
import com.wcl.msm.service.MsmService;
import com.wcl.utils.R;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;


@CrossOrigin
@RestController
@RequestMapping("edumsm/msm")
public class MsmController {
    @Resource
    private MsmService msmService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/send/{phone}")
    public R getPhoneCode(@PathVariable String phone) {
        if(!Pattern.matches("^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$",phone)){
            //手机号码格式正确
            return R.error().message("发送失败");
        }
        //避免重复发送
        if (StringUtils.hasLength(redisTemplate.opsForValue().get(phone))) {
            return R.ok().message("发送成功");
        }
        if (!StringUtils.hasLength(phone)) return R.error().message("发送失败");
        String code = RandomUtil.randomNumbers(4);
        Boolean send = msmService.send(code, phone);
        if (send) {
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return R.ok().message("发送成功");
        }
        return R.error().message("发送失败");
    }
}
