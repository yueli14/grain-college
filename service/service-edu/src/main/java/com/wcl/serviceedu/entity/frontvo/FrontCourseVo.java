/**
 * @Classname FrontCourseVo
 * @Description TODO
 * @Date 2022/4/27 18:10
 * @Created by 28327
 */

package com.wcl.serviceedu.entity.frontvo;

import lombok.Data;

import java.io.Serializable;

@Data
public class FrontCourseVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;

    private String teacherId;

    private String subjectParentId;

    private String subjectId;

    private String buyCountSort;

    private String gmtCreateSort;

    private String priceSort;
}