package com.wcl.acl.mapper;

import com.wcl.acl.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author wcl
 * @since 2022-05-12
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}
