/**
 * @Classname CourseInfo
 * @Description 接收前端表单的课程添加信息的实体类
 * @Date 2022/4/10 20:17
 * @Created by 28327
 */

package com.wcl.serviceedu.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CourseInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    //    @ApiModelProperty(value = "课程ID")
    private String id;
    //    @ApiModelProperty("subject_parent_id")
    private String subjectParentId;
    //    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;
    //    @ApiModelProperty(value = "课程专业ID")
    private String subjectId;
    //    @ApiModelProperty(value = "课程标题")
    private String title;
    //    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;
    //    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;
    //    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;
    //    @ApiModelProperty(value = "课程简介")
    private String description;
}