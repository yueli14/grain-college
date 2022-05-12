/**
 * @Classname ChapterInfoVo
 * @Description TODO
 * @Date 2022/4/11 15:33
 * @Created by 28327
 */

package com.wcl.serviceedu.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class ChapterInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private List<VideoInfoVo> children = new ArrayList<>();

}