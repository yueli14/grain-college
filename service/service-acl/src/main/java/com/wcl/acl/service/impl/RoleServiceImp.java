package com.wcl.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wcl.acl.entity.RoleEntity;
import com.wcl.acl.entity.UserRoleEntity;
import com.wcl.acl.mapper.RoleMapper;
import com.wcl.acl.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcl.acl.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wcl
 * @since 2022-05-12
 */
@Service
public class RoleServiceImp extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {
    @Autowired
    private UserRoleService userRoleService;


    //根据用户获取角色数据
    @Override
    @Transactional
    public Map<String, Object> findRoleByUserId(String userId) {
        //查询所有的角色
        List<RoleEntity> allRolesList = baseMapper.selectList(null);

        //根据用户id，查询用户拥有的角色id
        List<UserRoleEntity> existUserRoleList = userRoleService.list(new QueryWrapper<UserRoleEntity>().eq("user_id", userId).select("role_id"));

        List<String> existRoleList = existUserRoleList.stream().map(c -> c.getRoleId()).collect(Collectors.toList());

        //对角色进行分类
        List<RoleEntity> assignRoles = new ArrayList<RoleEntity>();
        for (RoleEntity role : allRolesList) {
            //已分配
            if (existRoleList.contains(role.getId())) {
                assignRoles.add(role);
            }
        }

        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assignRoles", assignRoles);
        roleMap.put("allRolesList", allRolesList);
        return roleMap;
    }

    //根据用户分配角色
    @Override
    @Transactional
    public void saveUserRoleRealtionShip(String userId, String[] roleIds) {
        userRoleService.remove(new QueryWrapper<UserRoleEntity>().eq("user_id", userId));

        List<UserRoleEntity> userRoleList = new ArrayList<>();
        for (String roleId : roleIds) {
            if (StringUtils.isEmpty(roleId)) continue;
            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);

            userRoleList.add(userRole);
        }
        userRoleService.saveBatch(userRoleList);
    }

    @Override
    @Transactional
    public List<RoleEntity> selectRoleByUserId(String id) {
        //根据用户id拥有的角色id
        List<UserRoleEntity> userRoleList = userRoleService.list(new QueryWrapper<UserRoleEntity>().eq("user_id", id).select("role_id"));
        List<String> roleIdList = userRoleList.stream().map(item -> item.getRoleId()).collect(Collectors.toList());
        List<RoleEntity> roleList = new ArrayList<>();
        if (roleIdList.size() > 0) {
            roleList = baseMapper.selectBatchIds(roleIdList);
        }
        return roleList;
    }
}
