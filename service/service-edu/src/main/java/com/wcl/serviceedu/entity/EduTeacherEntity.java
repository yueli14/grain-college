package com.wcl.serviceedu.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 讲师
 * </p>
 *
 * @author wcl
 * @since 2022-04-01
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("edu_teacher")
//@ApiModel(value = "EduTeacherEntity对象", description = "讲师")
public class EduTeacherEntity extends Model<EduTeacherEntity> {

//    @ApiModelProperty("讲师ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

//    @ApiModelProperty("讲师姓名")
    @TableField("`name`")
    private String name;

//    @ApiModelProperty("讲师简介")
    @TableField("intro")
    private String intro;

//    @ApiModelProperty("讲师资历,一句话说明讲师")
    @TableField("career")
    private String career;

//    @ApiModelProperty("头衔 1高级讲师 2首席讲师")
    @TableField("`level`")
    private Integer level;

//    @ApiModelProperty("讲师头像")
    @TableField("avatar")
    private String avatar;

//    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;
//    配置逻辑删除
    @TableLogic
//    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("is_deleted")
    private Integer isDeleted;

//    @ApiModelProperty("创建时间")
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    private Date gmtCreate;

//    @ApiModelProperty("更新时间")
    @TableField(value = "gmt_modified" , fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
