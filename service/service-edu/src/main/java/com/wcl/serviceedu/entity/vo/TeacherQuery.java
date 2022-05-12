/**
 * @Classname TeacherQuery
 * @Description vo类，分装条件查询的对象
 * @Date 2022/4/3 20:09
 * @Created by 28327
 */

package com.wcl.serviceedu.entity.vo;

//import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

import java.io.Serializable;

@Data
public class TeacherQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private Integer level;

    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    private String end;
}