/**
 * @Classname EduFrontTeacherController
 * @Description TODO
 * @Date 2022/4/27 14:19
 * @Created by 28327
 */

package com.wcl.serviceedu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcl.serviceedu.entity.EduTeacherEntity;
import com.wcl.serviceedu.service.EduTeacherService;
import com.wcl.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("eduservice/teacher/user")
public class EduFrontTeacherController {
    @Resource
    private EduTeacherService teacherService;

    /**
     * 分页查询
     */
    @GetMapping("/page/{current}/{size}")
    public R getPage(@PathVariable long current, @PathVariable long size) {
        QueryWrapper<EduTeacherEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sort");
        Page<EduTeacherEntity> page = new Page<>(current, size);
        teacherService.page(page, queryWrapper);
        return R.ok().data("page", page);
    }

    @GetMapping("/get/{id}")
    public R getTeacher(@PathVariable String id) {
        EduTeacherEntity byId = teacherService.getById(id);
        return R.ok().data("teacher", byId);
    }
}