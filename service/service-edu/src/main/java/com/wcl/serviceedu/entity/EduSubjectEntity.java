package com.wcl.serviceedu.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程科目
 * </p>
 *
 * @author wcl
 * @since 2022-04-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("edu_subject")
//@ApiModel(value = "EduSubjectEntity对象", description = "课程科目")
public class EduSubjectEntity extends Model<EduSubjectEntity> {

//    @ApiModelProperty("课程类别ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

//    @ApiModelProperty("类别名称")
    @TableField("title")
    private String title;

//    @ApiModelProperty("父ID")
    @TableField("parent_id")
    private String parentId;

//    @ApiModelProperty("排序字段")
    @TableField("sort")
    private Integer sort;

//    @ApiModelProperty("创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

//    @ApiModelProperty("更新时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
