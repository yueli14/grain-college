package com.wcl.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wcl.serviceedu.entity.EduChapterEntity;
import com.wcl.serviceedu.entity.EduVideoEntity;
import com.wcl.serviceedu.entity.vo.ChapterInfoVo;
import com.wcl.serviceedu.entity.vo.VideoInfoVo;
import com.wcl.serviceedu.mapper.EduChapterMapper;
import com.wcl.serviceedu.mapper.EduVideoMapper;
import com.wcl.serviceedu.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author wcl
 * @since 2022-04-10
 */
@Service
public class EduChapterServiceImp extends ServiceImpl<EduChapterMapper, EduChapterEntity> implements EduChapterService {
    @Resource
    private EduVideoMapper eduVideoMapper;

    @Override
    public List<ChapterInfoVo> getAllChapter(String courseId) {
        ArrayList<ChapterInfoVo> returnList = new ArrayList<>();
        //现根据课程id对其下所有的章节名进行查询
        QueryWrapper<EduChapterEntity> eduChapterEntityQueryWrapper = new QueryWrapper<>();
        eduChapterEntityQueryWrapper.eq("course_id", courseId);
//        eduChapterEntityQueryWrapper.orderByAsc("sort", "id");
        List<EduChapterEntity> eduChapterEntities = baseMapper.selectList(eduChapterEntityQueryWrapper);
        //然后对video进行查询
        QueryWrapper<EduVideoEntity> eduVideoEntityQueryWrapper = new QueryWrapper<>();
        eduVideoEntityQueryWrapper.eq("course_id", courseId);
        eduVideoEntityQueryWrapper.orderByAsc("sort", "id");
        List<EduVideoEntity> eduVideoEntities = eduVideoMapper.selectList(eduVideoEntityQueryWrapper);

        //遍历封装为vo类
        for (EduChapterEntity eduChapterEntity : eduChapterEntities) {
            ChapterInfoVo chapterInfoVo = new ChapterInfoVo();
            BeanUtils.copyProperties(eduChapterEntity, chapterInfoVo);
            for (EduVideoEntity eduVideoEntity : eduVideoEntities) {
                if (eduChapterEntity.getId().equals(eduVideoEntity.getChapterId())) {
                    VideoInfoVo videoInfoVo = new VideoInfoVo();
                    BeanUtils.copyProperties(eduVideoEntity, videoInfoVo);
                    chapterInfoVo.getChildren().add(videoInfoVo);
                }
            }
            returnList.add(chapterInfoVo);
        }
        return returnList;
    }

    @Override
    public Boolean deleteChapterById(String chapterId) {
        //根据章节id查询下面的小节，如果有则不删除，否则删除
        QueryWrapper<EduVideoEntity> eduVideoEntityQueryWrapper = new QueryWrapper<>();
        eduVideoEntityQueryWrapper.eq("chapter_id", chapterId);
        Long aLong = eduVideoMapper.selectCount(eduVideoEntityQueryWrapper);
        if (aLong == 0) {
            baseMapper.deleteById(chapterId);
            return true;
        } else {
            return false;
        }
    }
}
