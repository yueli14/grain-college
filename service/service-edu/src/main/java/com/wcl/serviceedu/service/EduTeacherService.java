package com.wcl.serviceedu.service;

import com.wcl.serviceedu.entity.EduTeacherEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author wcl
 * @since 2022-04-01
 */
public interface EduTeacherService extends IService<EduTeacherEntity> {
    List getIndexTeacher();
}
