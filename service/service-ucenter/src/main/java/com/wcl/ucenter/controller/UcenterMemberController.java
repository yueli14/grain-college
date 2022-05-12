package com.wcl.ucenter.controller;


import com.wcl.ucenter.entity.UcenterMemberEntity;
import com.wcl.ucenter.entity.vo.LoginVoInfo;
import com.wcl.ucenter.entity.vo.RegisterVoInfo;
import com.wcl.ucenter.service.UcenterMemberService;
import com.wcl.utils.JwtUtil;
import com.wcl.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 会员表 用户登陆的场景
 *
 * </p>
 *
 * @author wcl
 * @since 2022-04-23
 */
@RestController
@CrossOrigin
@RequestMapping("/educenter/center")
public class UcenterMemberController {
    @Resource
    private UcenterMemberService memberService;

    @PostMapping("/login")
    public R login(@RequestBody LoginVoInfo voInfo) {
        String login = memberService.login(voInfo);
        if (login.equals("登陆失败")) {
            return R.error().data("token", login);
        }
        return R.ok().data("token", login);
    }

    @PostMapping("/register")
    public R register(@RequestBody RegisterVoInfo voInfo) {
        String register = memberService.register(voInfo);
        if (register.equals("注册失败")) {
            return R.error().message(register);
        }
        return R.ok().message(register);
    }

    /**
     * 根据token获取用户信息
     */
    @GetMapping("/get/login/info")
    public R getLoginInfo(HttpServletRequest request) {
        String memberId = JwtUtil.getMemberIdByJwtToken(request);
        UcenterMemberEntity entity = memberService.getById(memberId);
        entity.setPassword("");
        return R.ok().data("info", entity);
    }

    /**
     * 根据用户id获取用户信息
     */
    @GetMapping("/get/info/{id}")
    public Map<String, String> getUserInfo(@PathVariable String id) {
        UcenterMemberEntity byId = memberService.getById(id);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("avatar", byId.getAvatar());
        map.put("nickname", byId.getNickname());
        map.put("mobile", byId.getMobile());
        return map;
    }
}

