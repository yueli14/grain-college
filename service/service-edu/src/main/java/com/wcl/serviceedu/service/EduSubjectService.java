package com.wcl.serviceedu.service;

import com.wcl.serviceedu.entity.EduSubjectEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author wcl
 * @since 2022-04-08
 */
public interface EduSubjectService extends IService<EduSubjectEntity> {

    void importExcel(MultipartFile file, EduSubjectService subjectService);

    List getAll();
}
