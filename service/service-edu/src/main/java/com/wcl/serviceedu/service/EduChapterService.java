package com.wcl.serviceedu.service;

import com.wcl.serviceedu.entity.EduChapterEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wcl.serviceedu.entity.vo.ChapterInfoVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author wcl
 * @since 2022-04-10
 */
public interface EduChapterService extends IService<EduChapterEntity> {

    List<ChapterInfoVo> getAllChapter(String id);

    Boolean deleteChapterById(String chapterId);
}
