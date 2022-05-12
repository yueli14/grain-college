package com.wcl.serviceedu.mapper;

import com.wcl.serviceedu.entity.EduCourseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wcl.serviceedu.entity.frontvo.FrontCourseIdVo;
import com.wcl.serviceedu.entity.vo.CoursePublishVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author wcl
 * @since 2022-04-10
 */
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourseEntity> {
    CoursePublishVo getCoursePublishVoById(String id);
    FrontCourseIdVo getFrontCourseIdVo(String id);
}
