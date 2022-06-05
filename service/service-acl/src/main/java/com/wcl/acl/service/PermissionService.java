package com.wcl.acl.service;

import com.alibaba.fastjson.JSONObject;
import com.wcl.acl.entity.PermissionEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author wcl
 * @since 2022-05-12
 */
public interface PermissionService extends IService<PermissionEntity> {
    //获取全部菜单
    List<PermissionEntity> queryAllMenu();

    //根据角色获取菜单
    List<PermissionEntity> selectAllMenu(String roleId);

    //给角色分配权限
    void saveRolePermissionRelationShip(String roleId, String[] permissionId);

    //递归删除菜单
    void removeChildById(String id);

    //根据用户id获取用户菜单
    List<String> selectPermissionValueByUserId(String id);

    List<JSONObject> selectPermissionByUserId(String id);

    //获取全部菜单
    List<PermissionEntity> queryAllMenuGuli();

    //递归删除菜单
    void removeChildByIdGuli(String id);

    //给角色分配权限
    void saveRolePermissionRealtionShipGuli(String roleId, String[] permissionId);

}
