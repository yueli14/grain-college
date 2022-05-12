/**
 * @Classname EduFrontCourseController
 * @Description TODO
 * @Date 2022/4/27 15:49
 * @Created by 28327
 */

package com.wcl.serviceedu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcl.serviceedu.client.OrderClient;
import com.wcl.serviceedu.entity.EduCourseEntity;
import com.wcl.serviceedu.entity.frontvo.FrontCourseIdVo;
import com.wcl.serviceedu.entity.frontvo.FrontCourseVo;
import com.wcl.serviceedu.entity.vo.ChapterInfoVo;
import com.wcl.serviceedu.service.EduChapterService;
import com.wcl.serviceedu.service.EduCourseService;
import com.wcl.serviceedu.service.EduSubjectService;
import com.wcl.utils.JwtUtil;
import com.wcl.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("eduservice/course/user")
public class EduFrontCourseController {
    @Autowired
    private EduCourseService courseService;
    @Resource
    private EduSubjectService subjectService;
    @Resource
    private EduChapterService chapterService;
    @Autowired
    private OrderClient orderClient;

    /**
     * 根据讲师id查询课程
     *
     * @param teacherId
     * @return
     */
    @GetMapping("get/teacher/{teacherId}")
    public R getCourseByTeacherId(@PathVariable String teacherId) {
        QueryWrapper<EduCourseEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", teacherId);
        List<EduCourseEntity> list = courseService.list(queryWrapper);
        return R.ok().data("courseList", list);
    }

    /**
     * 前台系统条件分页查询
     *
     * @param current
     * @param size
     * @param courseVo
     * @return
     */
    @PostMapping("get/course/page/{current}/{size}")
    public R getCourseByQuery(@PathVariable long current, @PathVariable long size,
                              @RequestBody(required = false) FrontCourseVo courseVo) {
        Page<EduCourseEntity> page = new Page<>(current, size);
        courseService.getFrontCoursePage(page, courseVo);
        return R.ok().data("page", page);
    }

    @GetMapping("get/subject")
    public R getAllSubject() {
        List all = subjectService.getAll();
        return R.ok().data("subject", all);
    }

    /**
     * 根据课程id查询它所下面的章节和小节
     *
     * @param courseId
     * @return
     */
    @GetMapping("/get/chapter/{courseId}")
    public R getAllChapter(@PathVariable String courseId) {
        List<ChapterInfoVo> list = chapterService.getAllChapter(courseId);
        return R.ok().data("chapter", list);
    }

    @GetMapping("/get/course/info/{courseId}")
    public R getCourseInfoByCourseId(@PathVariable String courseId, HttpServletRequest request) {
        String memberId = JwtUtil.getMemberIdByJwtToken(request);
        if (StringUtils.hasLength(memberId)) {
            Boolean status = orderClient.getOrderInfo(courseId, memberId);
            FrontCourseIdVo frontCourseIdVo = courseService.getFrontCourseIdVo(courseId);
            return R.ok().data("course", frontCourseIdVo).data("status", status);
        }
        FrontCourseIdVo frontCourseIdVo = courseService.getFrontCourseIdVo(courseId);
        return R.ok().data("course", frontCourseIdVo);
    }
}