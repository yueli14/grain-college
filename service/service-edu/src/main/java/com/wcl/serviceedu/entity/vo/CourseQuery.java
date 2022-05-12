/**
 * @Classname CourseQuery
 * @Description TODO
 * @Date 2022/4/15 14:58
 * @Created by 28327
 */

package com.wcl.serviceedu.entity.vo;

//import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    //    @ApiModelProperty(value = "教师名称,模糊查询")
    private String title;
    private String status;
    //    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    //    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}