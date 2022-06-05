package com.wcl.acl.controller;


import com.wcl.acl.entity.PermissionEntity;
import com.wcl.acl.service.PermissionService;
import com.wcl.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author wcl
 * @since 2022-05-12
 */

@CrossOrigin
@RestController
@RequestMapping("/acl/permission/admin")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    //获取全部菜单
    @GetMapping("all")
    public R indexAllPermission() {
        List<PermissionEntity> list =  permissionService.queryAllMenuGuli();
        return R.ok().data("children",list);
    }


    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        permissionService.removeChildByIdGuli(id);
        return R.ok();
    }


    @PostMapping("/do/assign")
    public R doAssign(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRealtionShipGuli(roleId,permissionId);
        return R.ok();
    }

//
//    @GetMapping("to/assign/{roleId}")
//    public R toAssign(@PathVariable String roleId) {
//        List<PermissionEntity> list = permissionService.selectAllMenu(roleId);
//        return R.ok().data("children", list);
//    }




    @PostMapping("save")
    public R save(@RequestBody PermissionEntity permission) {
        permissionService.save(permission);
        return R.ok();
    }


    @PutMapping("update")
    public R updateById(@RequestBody PermissionEntity permission) {
        permissionService.updateById(permission);
        return R.ok();
    }

}

