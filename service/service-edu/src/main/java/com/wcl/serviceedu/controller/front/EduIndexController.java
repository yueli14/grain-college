/**
 * @Classname EduIndexController
 * @Description 前台首页类
 * @Date 2022/4/22 15:24
 * @Created by 28327
 */

package com.wcl.serviceedu.controller.front;

import com.wcl.serviceedu.service.EduCourseService;
import com.wcl.serviceedu.service.EduSubjectService;
import com.wcl.serviceedu.service.EduTeacherService;
import com.wcl.utils.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("eduservice/index/user")
public class EduIndexController {
    @Resource
    private EduCourseService courseService;
    @Resource
    private EduTeacherService teacherService;

    @GetMapping("get/index")
    public R getIndexCourseAndTeacher() {
        List indexCourse = courseService.getIndexCourse();
        List indexTeacher = teacherService.getIndexTeacher();

        return R.ok().data("indexCourse", indexCourse).data("indexTeacher", indexTeacher);
    }

}