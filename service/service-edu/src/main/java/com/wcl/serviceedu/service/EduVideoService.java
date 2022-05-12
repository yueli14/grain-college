package com.wcl.serviceedu.service;

import com.wcl.serviceedu.entity.EduVideoEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author wcl
 * @since 2022-04-10
 */
public interface EduVideoService extends IService<EduVideoEntity> {
    List<String> getAllVideoSourceIdByCourseId(String id);
}
