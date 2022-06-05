/**
 * @Classname UserDetailsServiceImpl
 * @Description TODO
 * @Date 2022/5/20 15:41
 * @Created by 28327
 */

package com.wcl.acl.service.impl;


import com.wcl.acl.entity.UserEntity;
import com.wcl.acl.service.PermissionService;
import com.wcl.acl.service.UserService;
import com.wcl.serurity.entity.SecurityUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 * 自定义userDetailsService - 认证用户详情
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    /***
     * 根据账号获取用户信息
     * @param username:
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        UserEntity user = userService.selectByUsername(username);

        // 判断用户是否存在
        if (null == user) {
            //throw new UsernameNotFoundException("用户名不存在！");
            return null;
        }
        // 返回UserDetails实现类
        com.wcl.serurity.entity.User curUser = new com.wcl.serurity.entity.User();
        BeanUtils.copyProperties(user, curUser);

        List<String> authorities = permissionService.selectPermissionValueByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser(curUser);
        securityUser.setPermissionValueList(authorities);
        return securityUser;
    }

}