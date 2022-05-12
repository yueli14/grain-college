/**
 * @Classname CoursePublishVo
 * @Description 课程发布页的封装类
 * @Date 2022/4/15 13:15
 * @Created by 28327
 */

package com.wcl.serviceedu.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class CoursePublishVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}