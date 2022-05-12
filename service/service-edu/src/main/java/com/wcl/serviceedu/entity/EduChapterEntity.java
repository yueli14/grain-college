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
 * 课程
 * </p>
 *
 * @author wcl
 * @since 2022-04-10
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("edu_chapter")
public class EduChapterEntity extends Model<EduChapterEntity> {


    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;


    @TableField("course_id")
    private String courseId;


    @TableField("title")
    private String title;


    @TableField("sort")
    private Integer sort;


    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;


    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
