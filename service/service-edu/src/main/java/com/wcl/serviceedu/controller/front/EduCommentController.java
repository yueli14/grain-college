package com.wcl.serviceedu.controller.front;


import cn.hutool.http.server.HttpServerRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcl.serviceedu.client.UCenterClient;
import com.wcl.serviceedu.entity.EduCommentEntity;
import com.wcl.serviceedu.service.EduCommentService;
import com.wcl.utils.JwtUtil;
import com.wcl.utils.R;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author wcl
 * @since 2022-05-04
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/comment/user")
public class EduCommentController {
    @Autowired
    private EduCommentService commentService;
    @Autowired
    private UCenterClient uCenterClient;

    /**
     * 根据课程id进行分页查询
     */
    @GetMapping("page/{current}/{size}/{courseId}")
    public R getPage(@PathVariable Long current,
                     @PathVariable String courseId,
                     @PathVariable Long size) {
        Page<EduCommentEntity> page = new Page<>(current, size);
        QueryWrapper<EduCommentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.orderByDesc("gmt_modified");
        commentService.page(page, queryWrapper);
        return R.ok().data("page", page);
    }
    @GlobalTransactional
    @PostMapping("add/comment")
    public R addComment(@RequestBody EduCommentEntity entity, HttpServletRequest request) {
        String id = JwtUtil.getMemberIdByJwtToken(request);
        if (!StringUtils.hasLength(id)) {
            return R.error().message("请登录");
        }
        Map<String, String> userInfo = uCenterClient.getUserInfo(id);
//        BeanUtils.copyProperties(entity, userInfo);
        entity.setMemberId(id);
        entity.setAvatar(userInfo.get("avatar"));
        entity.setNickname(userInfo.get("nickname"));
        return commentService.save(entity) ? R.ok().message("评论成功") : R.error().message("评论失败");
    }
}

