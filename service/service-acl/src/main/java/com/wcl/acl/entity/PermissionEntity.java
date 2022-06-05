package com.wcl.acl.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author wcl
 * @since 2022-05-12
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("acl_permission")
public class PermissionEntity extends Model<PermissionEntity> {
    @TableField(exist = false)
    private List<PermissionEntity> children;
    @TableField(exist = false)
    private Integer level;
    @TableField(exist = false)
    private boolean isSelect;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 所属上级
     */
    @TableField("pid")
    private String pid;

    /**
     * 名称
     */
    @TableField("`name`")
    private String name;

    /**
     * 类型(1:菜单,2:按钮)
     */
    @TableField("`type`")
    private Integer type;

    /**
     * 权限值
     */
    @TableField("permission_value")
    private String permissionValue;

    /**
     * 访问路径
     */
    @TableField("path")
    private String path;

    /**
     * 组件路径
     */
    @TableField("component")
    private String component;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 状态(0:禁止,1:正常)
     */
    @TableField("`status`")
    private Integer status;


    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 更新时间
     */

    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
