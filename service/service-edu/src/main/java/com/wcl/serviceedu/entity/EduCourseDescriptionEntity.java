package com.wcl.serviceedu.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程简介
 * </p>
 *
 * @author wcl
 * @since 2022-04-10
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("edu_course_description")

public class EduCourseDescriptionEntity extends Model<EduCourseDescriptionEntity> {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;


    @TableField("description")
    private String description;


    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;


    @TableField(value = "gmt_modified",fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
