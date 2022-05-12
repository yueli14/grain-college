/**
 * @Classname WechatLoginController
 * @Description TODO
 * @Date 2022/4/26 15:39
 * @Created by 28327
 */

package com.wcl.ucenter.controller;

import cn.hutool.core.util.EscapeUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTUtil;
import com.google.gson.Gson;
import com.wcl.ucenter.entity.UcenterMemberEntity;
import com.wcl.ucenter.service.UcenterMemberService;
import com.wcl.ucenter.utils.ConstantPropertiesU;
import com.wcl.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

@CrossOrigin
//拒绝返回数据，而是去访问重定向地址
@Controller
//会直接返回数据
//@RestController
@Slf4j
@RequestMapping("/api/ucenter/wx")
public class WechatLoginController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UcenterMemberService memberService;

    /**
     * 获取微信登陆二维码
     *
     * @param session
     * @return
     */
    @GetMapping("login")
    public String getErWeiCode(HttpSession session) {
        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        // 回调地址
        String redirectUrl = ConstantPropertiesU.WX_OPEN_REDIRECT_URL; //获取业务服务器重定向地址
        try {
            //重编码
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8"); //url编码
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
        // 防止csrf攻击（跨站请求伪造攻击）
        //String state = UUID.randomUUID().toString().replaceAll("-", "");//一般情况下会使用一个随机数
//        String state = "localhost:8160";//为了让大家能够使用我搭建的外网的微信回调跳转服务器，这里填写你在ngrok的前置域名
//        System.out.println("state = " + state);
        // 采用redis等进行缓存state 使用sessionId为key 30分钟后过期，可配置
        //键："wechar-open-state-" + httpServletRequest.getSession().getId()
        //值：satte
        //过期时间：30分钟

        //生成qrcodeUrl
        String qrcodeUrl = String.format(
                baseUrl,
                ConstantPropertiesU.WX_OPEN_APP_ID,
                redirectUrl,
                "atguigu"
        );
        return "redirect:" + qrcodeUrl;
    }

    /**
     * 实现微信登录
     *
     * @param code
     * @param state
     * @return
     */
    @GetMapping("callback")
    public String callback(String code, String state) {//得到授权临时票据code
        log.error(code + "|" + state);
        //从redis中将state获取出来，和当前传入的state作比较
        //如果一致则放行，如果不一致则抛出异常：非法访问
        //向认证服务器发送请求换取access_token，得到{
        //"access_token":"ACCESS_TOKEN",
        //"expires_in":7200,
        //"refresh_token":"REFRESH_TOKEN",
        //"openid":"OPENID",
        //"scope":"SCOPE"
        //}
        String baseAccessTokenUrl =
                "https://api.weixin.qq.com/sns/oauth2/access_token" +
                        "?appid=%s" +
                        "&secret=%s" +
                        "&code=%s" +
                        "&grant_type=authorization_code";
        String accessTokenUrl = String.format(baseAccessTokenUrl,
                ConstantPropertiesU.WX_OPEN_APP_ID,
                ConstantPropertiesU.WX_OPEN_APP_SECRET,
                code);
        String str = restTemplate.getForObject(accessTokenUrl, String.class);
        //解析json字符串
        Gson gson = new Gson();
        HashMap map = gson.fromJson(str, HashMap.class);
        String accessToken = (String) map.get("access_token");
        String openid = (String) map.get("openid");

        //对opneid进行查询，看是否为第一次登录
        UcenterMemberEntity memberByOpenid = memberService.getMemberByOpenid(openid);
        if (memberByOpenid == null) {
            //此中发送请求，得到用户信息
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";
            String userInfoUrl = String.format(baseUserInfoUrl, accessToken, openid);
            String userInfo = restTemplate.getForObject(userInfoUrl, String.class);
            Gson gsonUser = new Gson();
            HashMap mapUser = gsonUser.fromJson(userInfo, HashMap.class);
            String nickname = (String) mapUser.get("nickname");
            String h = (String) mapUser.get("headimgurl");
            String headimgurl = EscapeUtil.safeUnescape(h);
            memberByOpenid = new UcenterMemberEntity();
            memberByOpenid.setNickname(nickname);
            memberByOpenid.setOpenid(openid);
            memberByOpenid.setAvatar(headimgurl);
            memberService.save(memberByOpenid);
        }
        //生成token
        String token = JwtUtil.getJwtToken(memberByOpenid.getId(), memberByOpenid.getNickname());
        return "redirect:http://localhost:3000?token=" + token;
    }

}