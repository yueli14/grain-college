package com.wcl.acl.mapper;

import com.wcl.acl.entity.PermissionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author wcl
 * @since 2022-05-12
 */
@Mapper
public interface PermissionMapper extends BaseMapper<PermissionEntity> {
    List<String> selectPermissionValueByUserId(String id);

    List<String> selectAllPermissionValue();

    List<PermissionEntity> selectPermissionByUserId(String userId);
}
