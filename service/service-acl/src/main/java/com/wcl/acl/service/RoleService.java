package com.wcl.acl.service;

import com.wcl.acl.entity.RoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wcl
 * @since 2022-05-12
 */
public interface RoleService extends IService<RoleEntity> {
    //根据用户获取角色数据
    Map<String, Object> findRoleByUserId(String userId);

    //根据用户分配角色
    void saveUserRoleRealtionShip(String userId, String[] roleId);

    List<RoleEntity> selectRoleByUserId(String id);
}
