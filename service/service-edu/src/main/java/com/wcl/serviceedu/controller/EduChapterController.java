package com.wcl.serviceedu.controller;


import com.wcl.serviceedu.entity.EduChapterEntity;
import com.wcl.serviceedu.entity.vo.ChapterInfoVo;
import com.wcl.serviceedu.service.EduChapterService;
import com.wcl.serviceedu.service.impl.EduChapterServiceImp;
import com.wcl.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
@RequestMapping("/eduservice/chapter/admin")
public class EduChapterController {
    @Resource
    private EduChapterService chapterServiceImp;

    /**
     * 根据课程id查询它所下面的章节和小节
     *
     * @param id
     * @return
     */
    @GetMapping("/all/{courseId}")
    public R getAllChapter(@PathVariable String courseId) {
        List<ChapterInfoVo> list = chapterServiceImp.getAllChapter(courseId);
        return R.ok().data("chapter", list);
    }

    @PostMapping("/add")
    public R allChapter(@RequestBody EduChapterEntity eduChapterEntity) {
        chapterServiceImp.save(eduChapterEntity);
        return R.ok().message("保存成功");
    }

    @GetMapping("/get/{chapterId}")
    public R getChapterById(@PathVariable String chapterId) {
        return R.ok().data("chapter", chapterServiceImp.getById(chapterId));
    }

    @PutMapping("/update")
    public R updateChapterById(@RequestBody EduChapterEntity eduChapterEntity) {
        chapterServiceImp.updateById(eduChapterEntity);
        return R.ok().message("保存成功");
    }

    /**
     * 采取的删除策略，当删除的章节有小节的时候，取消删除
     *
     * @param chapterId
     * @return
     */
    @DeleteMapping("/delete/{chapterId}")
    public R deleteChapterById(@PathVariable String chapterId) {
        Boolean flag = chapterServiceImp.deleteChapterById(chapterId);
        return flag ? R.ok().message("删除成功") : R.error().message("删除失败，请先删除小节");
    }
}

