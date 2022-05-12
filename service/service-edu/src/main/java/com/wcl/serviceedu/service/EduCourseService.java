package com.wcl.serviceedu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcl.serviceedu.entity.EduCourseEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wcl.serviceedu.entity.frontvo.FrontCourseIdVo;
import com.wcl.serviceedu.entity.frontvo.FrontCourseVo;
import com.wcl.serviceedu.entity.vo.CourseInfo;
import com.wcl.serviceedu.entity.vo.CoursePublishVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author wcl
 * @since 2022-04-10
 */
public interface EduCourseService extends IService<EduCourseEntity> {

    String addCourse(CourseInfo courseInfo);

    String updateCourse(CourseInfo courseInfo);

    CourseInfo getCourseAndDescription(String id);

    CoursePublishVo getCoursePublishVoById(String id);

    Boolean publishCourseById(String id);

    boolean trueRemoveById(String id);

    List getIndexCourse();

    Page<EduCourseEntity> getFrontCoursePage(Page<EduCourseEntity> page, FrontCourseVo courseVo);

    FrontCourseIdVo getFrontCourseIdVo(String courseId);

    Map<String,String> getCourseAndTeacherInfo(String courseId);

    Boolean addBuyCount(String courseId);
}
