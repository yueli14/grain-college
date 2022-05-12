/**
 * @Classname VodService
 * @Description TODO
 * @Date 2022/4/16 12:17
 * @Created by 28327
 */

package com.wcl.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    String uploadVideo(MultipartFile file);

    Boolean removeVideo(String id);

    Boolean removeVideoList(List<String> videoIdList);

    String getAuthVideo(String id);
}