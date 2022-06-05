package com.wcl.acl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcl.acl.entity.RoleEntity;
import com.wcl.acl.entity.UserEntity;
import com.wcl.acl.service.RoleService;
import com.wcl.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * d
 * </p>
 *
 * @author wcl
 * @since 2022-05-12
 */
@CrossOrigin
@RestController
@RequestMapping("/acl/role/admin")
public class RoleController {
    @Autowired
    private RoleService roleService;


    @PostMapping("get/{page}/{limit}")
    public R index(
            @PathVariable Long page,
            @PathVariable Long limit,
            @RequestBody(required = false) RoleEntity role) {
        Page<RoleEntity> pageParam = new Page<>(page, limit);
        if (!(role == null)) {
            QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
            if (!StringUtils.isEmpty(role.getRoleName())) {
                wrapper.like("role_name", role.getRoleName());
            }
            roleService.page(pageParam, wrapper);
            return R.ok().data("items", pageParam.getRecords()).data("total", pageParam.getTotal());
        } else {
            roleService.page(pageParam);
            return R.ok().data("items", pageParam.getRecords()).data("total", pageParam.getTotal());

        }

    }


    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        RoleEntity role = roleService.getById(id);
        return R.ok().data("item", role);
    }


    @PostMapping("save")
    public R save(@RequestBody RoleEntity role) {
        roleService.save(role);
        return R.ok();
    }


    @PutMapping("update")
    public R updateById(@RequestBody RoleEntity role) {
        roleService.updateById(role);
        return R.ok();
    }


    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        roleService.removeById(id);
        return R.ok();
    }


    @DeleteMapping("batch/remove")
    public R batchRemove(@RequestBody List<String> idList) {
        roleService.removeByIds(idList);
        return R.ok();
    }
}

