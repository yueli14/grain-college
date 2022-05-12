package com.wcl.serviceedu.controller;


import com.wcl.serviceedu.client.VodClient;
import com.wcl.serviceedu.entity.EduVideoEntity;
import com.wcl.serviceedu.service.EduVideoService;
import com.wcl.serviceedu.service.impl.EduVideoServiceImp;
import com.wcl.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author wcl
 * @since 2022-04-10
 */
@RestController
@RequestMapping("/eduservice/video/admin")
@CrossOrigin
public class EduVideoController {
    @Resource
    private EduVideoService eduVideoServiceImp;

    @Autowired
    private VodClient vodclient;

    @GetMapping("get/{id}")
    public R getVideoById(@PathVariable String id) {
        EduVideoEntity eduVideoEntity = eduVideoServiceImp.getById(id);
        return R.ok().data("video", eduVideoEntity);
    }

    /**
     * 这里是逻辑删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public R deleteVideoById(@PathVariable String id) {
        // 偷懒，将业务写到了controller
        EduVideoEntity eduVideoEntity = eduVideoServiceImp.getById(id);
        String videoSourceId = eduVideoEntity.getVideoSourceId();
        if (StringUtils.hasLength(videoSourceId)) {
            vodclient.deleteVideo(videoSourceId);
        }
        eduVideoServiceImp.removeById(id);
        return R.ok().message("删除成功");
    }

    @PutMapping("update")
    public R updateVideo(@RequestBody EduVideoEntity eduVideoEntity) {
        eduVideoServiceImp.updateById(eduVideoEntity);
        return R.ok().message("保存成功");
    }

    @PostMapping("add")
    public R getVideoById(@RequestBody EduVideoEntity eduVideoEntity) {
        eduVideoServiceImp.save(eduVideoEntity);
        return R.ok().message("保存成功");
    }

    @GetMapping("select/{id}")
    public R getVideoByBatch(@PathVariable(value = "id") String id) {
        List<String> allVideoSourceIdByCourseId = eduVideoServiceImp.getAllVideoSourceIdByCourseId(id);
        return R.ok().data("list", allVideoSourceIdByCourseId);
    }
}

