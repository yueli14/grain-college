/**
 * @Classname VodController
 * @Description TODO
 * @Date 2022/4/16 12:27
 * @Created by 28327
 */

package com.wcl.vod.controller;

import com.wcl.utils.R;

import com.wcl.vod.service.imp.VodServiceImp;
import org.apache.poi.util.StringUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/eduvod/video")
public class VodController {
    @Resource
    private VodServiceImp vodService;

    @PostMapping("upload")
    public R uploadVideo(MultipartFile file) {
        String id = vodService.uploadVideo(file);
        return R.ok().data("videoId", id);
    }

    @DeleteMapping("delete/{id}")
    public R deleteVideo(@PathVariable String id) {
        return vodService.removeVideo(id) ? R.ok().message("删除成功") : R.ok().message("删除失败");

    }

    @DeleteMapping("delete/batch")
    public R deleteVideoByBatch(@RequestParam(value = "videoId") List<String> videoId) {
        return vodService.removeVideoList(videoId) ? R.ok().message("删除成功") : R.ok().message("删除失败");
    }

    @GetMapping("get/auth/{id}")
    public R getAuthPlayVideo(@PathVariable String id) {
        String authVideo = vodService.getAuthVideo(id);
        if (!authVideo.equals("null")) {
            return R.ok().data("auth", authVideo);
        } else return R.error().message("数据拉取失败");

    }
}