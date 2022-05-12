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
 * 评论
 * </p>
 *
 * @author wcl
 * @since 2022-05-04
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("edu_comment")
public class EduCommentEntity extends Model<EduCommentEntity> {

    /**
     * 讲师ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 课程id
     */
    @TableField("course_id")
    private String courseId;

    /**
     * 讲师id
     */
    @TableField("teacher_id")
    private String teacherId;

    /**
     * 会员id
     */
    @TableField("member_id")
    private String memberId;

    /**
     * 会员昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 会员头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 评论内容
     */
    @TableField("content")
    private String content;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableField("is_deleted")
    @TableLogic
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
