package com.wcl.serviceedu.controller;


import com.wcl.serviceedu.service.EduSubjectService;
import com.wcl.utils.R;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 实现excel导入数据库
 * </p>
 *
 * @author wcl
 * @since 2022-04-08
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/subject/admin")
public class EduSubjectController {
    @Resource
    private EduSubjectService subjectService;

    @PostMapping("uploadexcel")
    public R uploadExcel(MultipartFile file) {
        subjectService.importExcel(file, subjectService);
        return R.ok().message("导入成功");
    }

    @GetMapping("get/all")
    public R getAll() {
        List all = subjectService.getAll();
        return R.ok().data("list", all);
    }

}

