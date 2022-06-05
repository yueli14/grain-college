package com.wcl.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wcl.acl.entity.UserEntity;
import com.wcl.acl.mapper.UserMapper;
import com.wcl.acl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wcl
 * @since 2022-05-12
 */
@Service
public class UserServiceImp extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    public UserEntity selectByUsername(String username) {
        QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();
        userEntityQueryWrapper.eq("username", username);
        return baseMapper.selectOne(userEntityQueryWrapper);
    }
}
