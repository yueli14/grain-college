/**
 * @Classname OneReclassify
 * @Description 课程分类展示的实体类
 * @Date 2022/4/9 20:48
 * @Created by 28327
 */

package com.wcl.serviceedu.entity.vo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class OneReclassify {
    private String id;
    private String title;
    private ArrayList<OneReclassify> children = new ArrayList();
}