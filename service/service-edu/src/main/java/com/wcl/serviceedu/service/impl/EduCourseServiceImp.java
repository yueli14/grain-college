package com.wcl.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcl.serviceedu.client.VodClient;
import com.wcl.serviceedu.entity.*;
import com.wcl.serviceedu.entity.frontvo.FrontCourseIdVo;
import com.wcl.serviceedu.entity.frontvo.FrontCourseVo;
import com.wcl.serviceedu.entity.vo.CourseInfo;
import com.wcl.serviceedu.entity.vo.CoursePublishVo;
import com.wcl.serviceedu.mapper.*;
import com.wcl.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author wcl
 * @since 2022-04-10
 */
@Slf4j
@Service
public class EduCourseServiceImp extends ServiceImpl<EduCourseMapper, EduCourseEntity> implements EduCourseService {
    @Resource
    private EduCourseDescriptionMapper eduCourseDescriptionMapper;
    @Resource
    private EduVideoMapper eduVideoMapper;
    @Resource
    private EduChapterMapper eduChapterMapper;
    @Resource
    private VodClient vodClient;
    @Resource
    private EduTeacherMapper teacherMapper;

    @Transactional
    @Override
    public String addCourse(CourseInfo courseInfo) {
        //添加第一张表course数据
        EduCourseEntity eduCourseEntity = new EduCourseEntity();

        BeanUtils.copyProperties(courseInfo, eduCourseEntity);
        //不清楚为什么逻辑注入失败
        eduCourseEntity.setIsDeleted(0);
        baseMapper.insert(eduCourseEntity);
        String id = eduCourseEntity.getId();
        //添加第二张表course description,手动设置id
        EduCourseDescriptionEntity eduCourseDescriptionEntity = new EduCourseDescriptionEntity();
        BeanUtils.copyProperties(courseInfo, eduCourseDescriptionEntity);
        eduCourseDescriptionEntity.setId(id);
        eduCourseDescriptionMapper.insert(eduCourseDescriptionEntity);
        return id;
    }

    @Override
    @Transactional
    public String updateCourse(CourseInfo courseInfo) {
        //添加第一张表course数据
        EduCourseEntity eduCourseEntity = new EduCourseEntity();
        BeanUtils.copyProperties(courseInfo, eduCourseEntity);
        //不清楚为什么逻辑注入失败
        eduCourseEntity.setIsDeleted(0);
        baseMapper.updateById(eduCourseEntity);
        String id = eduCourseEntity.getId();
        //添加第二张表course description,手动设置id
        EduCourseDescriptionEntity eduCourseDescriptionEntity = new EduCourseDescriptionEntity();
        BeanUtils.copyProperties(courseInfo, eduCourseDescriptionEntity);
        eduCourseDescriptionEntity.setId(id);
        eduCourseDescriptionMapper.updateById(eduCourseDescriptionEntity);
        return id;
    }

    @Transactional
    @Override
    public CourseInfo getCourseAndDescription(String id) {
        CourseInfo courseInfo = new CourseInfo();
        EduCourseEntity eduCourseEntity = baseMapper.selectById(id);
        if (eduCourseEntity == null) {
            return null;
        }
        BeanUtils.copyProperties(eduCourseEntity, courseInfo);
        EduCourseDescriptionEntity eduCourseDescriptionEntity = eduCourseDescriptionMapper.selectById(id);
        if (eduCourseDescriptionEntity != null) {
            BeanUtils.copyProperties(eduCourseDescriptionEntity, courseInfo);
            return courseInfo;
        } else {
            return courseInfo;
        }
    }

    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {
        return baseMapper.getCoursePublishVoById(id);
    }

    @Override
    public Boolean publishCourseById(String id) {
        EduCourseEntity eduCourseEntity = new EduCourseEntity();
        eduCourseEntity.setId(id);
        eduCourseEntity.setStatus("Normal");
        int i = baseMapper.updateById(eduCourseEntity);
        return i > 0 ? true : false;
    }

    /**
     * 假物理删除，因为设置了逻辑删除，只是跟着老师敲一敲，查询方法写的mapper
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public boolean trueRemoveById(String id) {
        //删除阿里云的视频
        List<String> allVideoSourceIdByCourseId = eduVideoMapper.getAllVideoSourceIdByCourseId(id);
        if (!ObjectUtils.isEmpty(allVideoSourceIdByCourseId)) {
            vodClient.deleteVideoByBatch(allVideoSourceIdByCourseId);
        }
        //删除video
        QueryWrapper<EduVideoEntity> eduVideoEntity = new QueryWrapper<>();
        eduVideoEntity.eq("course_id", id);
        eduVideoMapper.delete(eduVideoEntity);

        //删除chapter
        QueryWrapper<EduChapterEntity> eduChapterEntity = new QueryWrapper<>();
        eduChapterEntity.eq("course_id", id);
        eduChapterMapper.delete(eduChapterEntity);
        int i = baseMapper.deleteById(id);
        return i > 0 ? true : false;
    }

    @Override
    public List getIndexCourse() {
        QueryWrapper<EduCourseEntity> eduCourseEntityQueryWrapper = new QueryWrapper<>();
        eduCourseEntityQueryWrapper.orderByDesc("view_count");
        eduCourseEntityQueryWrapper.last("limit 8");
        return baseMapper.selectList(eduCourseEntityQueryWrapper);
    }

    @Override
    public Page<EduCourseEntity> getFrontCoursePage(Page<EduCourseEntity> page, FrontCourseVo courseVo) {
        QueryWrapper<EduCourseEntity> queryWrapper = new QueryWrapper<>();
        if (ObjectUtils.isEmpty(courseVo)) {
            baseMapper.selectPage(page, queryWrapper);
            return page;
        }
        if (StringUtils.hasLength(courseVo.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id",
                    courseVo.getSubjectParentId());
        }
        if (StringUtils.hasLength(courseVo.getSubjectId())) {
            queryWrapper.eq("subject_id", courseVo.getSubjectId());
        }
        if (StringUtils.hasLength(courseVo.getBuyCountSort())) {
            queryWrapper.orderByDesc("buy_count");
        }
        if (StringUtils.hasLength(courseVo.getGmtCreateSort())) {
            queryWrapper.orderByDesc("gmt_create");
        }
        if (StringUtils.hasLength(courseVo.getPriceSort())) {
            queryWrapper.orderByDesc("price");
        }
        baseMapper.selectPage(page, queryWrapper);
        return page;
    }

    @Override
    @Transactional
    public FrontCourseIdVo getFrontCourseIdVo(String courseId) {
        return baseMapper.getFrontCourseIdVo(courseId);
    }

    @Override
    public Map<String, String> getCourseAndTeacherInfo(String courseId) {
        EduCourseEntity course = baseMapper.selectById(courseId);
        String teacherId = course.getTeacherId();
        EduTeacherEntity teacher = teacherMapper.selectById(teacherId);
        HashMap<String, String> map = new HashMap<>();
        map.put("courseId", course.getId());
        map.put("courseTitle", course.getTitle());
        map.put("courseCover", course.getCover());
        map.put("teacherName", teacher.getName());
        return map;
    }

    @Override
    public Boolean addBuyCount(String courseId) {
        EduCourseEntity course = baseMapper.selectById(courseId);
        Long buyCount = course.getBuyCount();
        course.setBuyCount(buyCount + 1);
        return baseMapper.updateById(course) > 0;
    }
}
