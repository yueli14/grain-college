package com.wcl.acl.service;

import com.wcl.acl.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wcl
 * @since 2022-05-12
 */
public interface UserService extends IService<UserEntity> {
    UserEntity selectByUsername(String username);
}
