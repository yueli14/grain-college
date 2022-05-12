package com.wcl.serviceedu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wcl.serviceedu.entity.EduVideoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 课程视频 Mapper 接口
 * </p>
 *
 * @author wcl
 * @since 2022-04-10
 */
@Mapper
public interface EduVideoMapper extends BaseMapper<EduVideoEntity> {
    List<String> getAllVideoSourceIdByCourseId(String courseId);
}
