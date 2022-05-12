/**
 * @Classname FIleController
 * @Description 实现文件上传功能
 * @Date 2022/4/8 15:43
 * @Created by 28327
 */

package com.wcl.oss.controller;

import com.wcl.oss.service.FileService;
import com.wcl.utils.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("eduoss/file")
public class FIleController {
    @Resource
    private FileService fileService;

    @PostMapping("load")
    public R uploadFile(MultipartFile file) {
        String url = fileService.uploadFile(file);
        return R.ok().message("文件上传成功").data("url", url);
    }
}