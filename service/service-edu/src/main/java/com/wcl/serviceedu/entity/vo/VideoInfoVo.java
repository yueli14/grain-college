/**
 * @Classname VideoInfoVo
 * @Description TODO
 * @Date 2022/4/11 15:34
 * @Created by 28327
 */

package com.wcl.serviceedu.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class VideoInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private int free;
    private String videoSourceId;
}