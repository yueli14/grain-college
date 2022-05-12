/**
 * @Classname EduLoginController
 * @Description TODO
 * @Date 2022/4/5 15:11
 * @Created by 28327
 */

package com.wcl.serviceedu.controller;

import com.wcl.utils.R;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/eduuser/user/admin")
@CrossOrigin
public class EduLoginController {
    /**
     * 登录
     *
     * @return
     */
    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");
    }
    /**
     * 获取管理员信息
     *
     * @return
     */
    @GetMapping("info")
    public R info() {
        return R.ok().data("name", "管理员")
                .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    /**
     *登出
     */
    @PostMapping("loginout")
    public R logout() {
        return R.ok();
    }

}