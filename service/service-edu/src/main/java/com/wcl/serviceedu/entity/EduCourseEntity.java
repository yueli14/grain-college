package com.wcl.serviceedu.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("edu_course")

public class EduCourseEntity extends Model<EduCourseEntity> {


    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;


    @TableField("teacher_id")
    private String teacherId;

    @TableField("subject_id")
    private String subjectId;

    @TableField("subject_parent_id")
    private String subjectParentId;


    @TableField("title")
    private String title;


    @TableField("price")
    private BigDecimal price;


    @TableField("lesson_num")
    private Integer lessonNum;


    @TableField("cover")
    private String cover;


    @TableField("buy_count")
    private Long buyCount;


    @TableField("view_count")
    private Long viewCount;


    @TableField("version")
    @Version
    private Long version;


    @TableField("`status`")
    private String status;



    @TableField(value = "is_deleted")
    @TableLogic(value = "0", delval = "1")
    private Integer isDeleted;

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
