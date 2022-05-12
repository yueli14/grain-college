package com.wcl.serviceedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcl.serviceedu.entity.EduCourseEntity;
import com.wcl.serviceedu.entity.EduTeacherEntity;
import com.wcl.serviceedu.entity.vo.CourseInfo;
import com.wcl.serviceedu.entity.vo.CoursePublishVo;
import com.wcl.serviceedu.entity.vo.CourseQuery;
import com.wcl.serviceedu.entity.vo.TeacherQuery;
import com.wcl.serviceedu.service.EduCourseService;
import com.wcl.serviceedu.service.impl.EduCourseServiceImp;
import com.wcl.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author wcl
 * @since 2022-04-10
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/course/admin")
public class EduCourseController {
    @Autowired
    private EduCourseService courseServiceImp;

    /**
     * 添加课程
     *
     * @param courseInfo
     * @return
     */
    @PostMapping("/add/course")
    public R addCourse(@RequestBody CourseInfo courseInfo) {
        String id = courseServiceImp.addCourse(courseInfo);
        return R.ok().message("保存成功").data("courseId", id);
    }

    /**
     * 根据id查找课程
     */
    @GetMapping("/find/{id}")
    public R getCourseById(@PathVariable String id) {
        CourseInfo info = courseServiceImp.getCourseAndDescription(id);
        return R.ok().data("course", info);
    }

    /**
     * 根据id修改课程
     */
    @PutMapping("/update")
    public R updateCourseById(@RequestBody CourseInfo courseInfo) {
        String id = courseServiceImp.updateCourse(courseInfo);
        return R.ok().message("保存成功").data("courseId", id);
    }

    /**
     * 得到所有发布确认信息
     *
     * @param id
     * @return
     */
    @GetMapping("/get/publish/info/{id}")
    public R getPublishInfoById(@PathVariable String id) {
        CoursePublishVo coursePublishVoById = courseServiceImp.getCoursePublishVoById(id);
        return R.ok().data("coursePublish", coursePublishVoById);
    }

    @PutMapping("/publish/{id}")
    public R publishCourseById(@PathVariable String id) {
        return courseServiceImp.publishCourseById(id) ? R.ok().message("发布成功") : R.ok().message("发布失败");
    }

    //查找所有
    @GetMapping("/findall")
    public R getAll() {
        return R.ok().data("course", courseServiceImp.list());
    }


    //分页查询

    /**
     * @param 当前页码
     * @param 每页记录数
     * @return R
     */
    @GetMapping("page/{now}/{limit}")
    public R getPageCourse(@PathVariable Long now,
                           @PathVariable Long limit) {
        //创建存放类
        Page<EduCourseEntity> eduCourseEntityPage = new Page<>(now, limit);
        //数据都放在teacher中
        courseServiceImp.page(eduCourseEntityPage);
        //得到数据列表
//        List<EduTeacherEntity> records = teacher.getRecords();
        //总数
//        long total = teacher.getTotal();
        return R.ok().data("course", eduCourseEntityPage);
    }

    /**
     * 条件查询
     *
     * @param now         当前页数
     * @param limit       一页的数据
     * @param CourseQuery 条件参数
     * @return R
     */
    @PostMapping("pagefilter/{now}/{limit}")
    public R getPageCourseFilter(@PathVariable Long now,
                                 @PathVariable Long limit,
                                 //就json对象封装为java对象
                                 @RequestBody(required = false) CourseQuery courseQuery) {
        Page<EduCourseEntity> eduCourseEntityPage = new Page<>(now, limit);
        QueryWrapper<EduCourseEntity> wrapper = new QueryWrapper<>();
        //构建条件
        if (StringUtils.hasLength(courseQuery.getBegin())) {
            wrapper.ge("gmt_create", courseQuery.getBegin());
        }
        if (StringUtils.hasLength(courseQuery.getBegin())) {
            wrapper.le("gmt_create", courseQuery.getBegin());
        }
        if (!ObjectUtils.isEmpty(courseQuery.getStatus())) {
            wrapper.eq("status", courseQuery.getStatus());
        }
        if (StringUtils.hasLength(courseQuery.getTitle())) {
            wrapper.like("title", courseQuery.getTitle());
        }
        courseServiceImp.page(eduCourseEntityPage, wrapper);

        return R.ok().data("course", eduCourseEntityPage);

    }

    /**
     * 物理删除
     * 配置了删除 会自动实行逻辑删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("delete/true/{id}")
    public R trueDeleteById(@PathVariable String id) {
        if (ObjectUtils.isEmpty(id)) {
            return R.error().message("删除失败");
        }
        return courseServiceImp.trueRemoveById(id) ? R.ok().message("删除成功") : R.error().message("删除失败");
    }

    /**
     * client接口
     *
     * @param courseId
     * @return
     */
    @GetMapping("get/course/teacher/info/{courseId}")
    public Map<String, String> getCourseAndTeacherInfoByCourseId(@PathVariable String courseId) {
        Map<String, String> map = courseServiceImp.getCourseAndTeacherInfo(courseId);
        if (!(map == null)) {
            return map;
        }
        return null;
    }

    /**
     * 购买数+1
     */
    @PutMapping("/add/buy/count/{courseId}")
    public Boolean addBuyCount(@PathVariable("courseId") String courseId) {
        return courseServiceImp.addBuyCount(courseId);
    }
}

