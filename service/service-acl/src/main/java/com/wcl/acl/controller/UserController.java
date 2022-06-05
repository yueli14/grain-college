package com.wcl.acl.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcl.acl.entity.UserEntity;
import com.wcl.acl.service.RoleService;
import com.wcl.acl.service.UserService;
import com.wcl.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wcl
 * @since 2022-05-12
 */
@CrossOrigin
@RestController
@RequestMapping("/acl/user/admin")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @PostMapping("get/{page}/{limit}")
    public R index(
            @PathVariable Long page,
            @PathVariable Long limit,
            @RequestBody(required = false) UserEntity userQueryVo) {
        Page<UserEntity> pageParam = new Page<>(page, limit);
        if (userQueryVo != null) {

            QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
            if (!StringUtils.isEmpty(userQueryVo.getUsername())) {
                wrapper.like("username", userQueryVo.getUsername());
            }

            IPage<UserEntity> pageModel = userService.page(pageParam, wrapper);
            return R.ok().data("items", pageModel.getRecords()).data("total", pageModel.getTotal());
        } else {
            IPage<UserEntity> pageModel = userService.page(pageParam);
            return R.ok().data("items", pageModel.getRecords()).data("total", pageModel.getTotal());
        }
    }

    @PostMapping("save")
    public R save(@RequestBody UserEntity user) {
        user.setPassword(SecureUtil.md5(user.getPassword()));
        userService.save(user);
        return R.ok();
    }


    @PutMapping("update")
    public R updateById(@RequestBody UserEntity user) {
        userService.updateById(user);
        return R.ok();
    }


    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        userService.removeById(id);
        return R.ok();
    }


    @DeleteMapping("batch/remove")
    public R batchRemove(@RequestBody List<String> idList) {
        userService.removeByIds(idList);
        return R.ok();
    }


    @GetMapping("/to/assign/{userId}")
    public R toAssign(@PathVariable String userId) {
        Map<String, Object> roleMap = roleService.findRoleByUserId(userId);
        return R.ok().data(roleMap);
    }


    @PostMapping("/do/assign")
    public R doAssign(@RequestParam String userId, @RequestParam String[] roleId) {
        roleService.saveUserRoleRealtionShip(userId, roleId);
        return R.ok();
    }

}

