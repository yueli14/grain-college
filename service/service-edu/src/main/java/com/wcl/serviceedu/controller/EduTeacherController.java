package com.wcl.serviceedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcl.serviceedu.entity.EduTeacherEntity;
import com.wcl.serviceedu.entity.vo.TeacherQuery;
import com.wcl.serviceedu.service.EduTeacherService;
import com.wcl.utils.R;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author wcl
 * @since 2022-04-01
 */

@RestController
@RequestMapping("/eduservice/teacher/admin")
//跨域设置
@CrossOrigin
public class EduTeacherController {
    @Resource
    private EduTeacherService teacherService;

    //查找所有
    @GetMapping("/findall")
    public R getAll() {
        return R.ok().data("teacher", teacherService.list());
    }

    //逻辑删除
    @DeleteMapping("/delete/{id}")
    public R logicDeleteById(@PathVariable String id) {
        return teacherService.removeById(id) ? R.ok().message("删除成功") : R.error().message("删除失败");
    }
    //分页查询

    /**
     * @param 当前页码
     * @param 每页记录数
     * @return R
     */
    @GetMapping("page/{now}/{limit}")
    public R getPageTeacher(@PathVariable Long now,
                            @PathVariable Long limit) {
        //创建存放类
        Page<EduTeacherEntity> teacher = new Page<>(now, limit);
        //数据都放在teacher中
        teacherService.page(teacher);
        //得到数据列表
//        List<EduTeacherEntity> records = teacher.getRecords();
        //总数
//        long total = teacher.getTotal();
        return R.ok().data("teacher", teacher);
    }

    /**
     * 条件查询
     *
     * @param now          当前页数
     * @param limit        一页的数据
     * @param teacherQuery 条件参数
     * @return R
     */
    @PostMapping("pagefilter/{now}/{limit}")
    public R getPageTeacherFilter(@PathVariable Long now,
                                  @PathVariable Long limit,
                                  //就json对象封装为java对象
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<EduTeacherEntity> teacher = new Page<>(now, limit);
        QueryWrapper<EduTeacherEntity> wrapper = new QueryWrapper<>();
        //构建条件
        if (StringUtils.hasLength(teacherQuery.getBegin())) {
            wrapper.ge("gmt_create", teacherQuery.getBegin());
        }
        if (StringUtils.hasLength(teacherQuery.getBegin())) {
            wrapper.le("gmt_create", teacherQuery.getBegin());
        }
        if (!ObjectUtils.isEmpty(teacherQuery.getLevel())) {
            wrapper.eq("level", teacherQuery.getLevel());
        }
        if (StringUtils.hasLength(teacherQuery.getName())) {
            wrapper.like("name", teacherQuery.getName());
        }
        teacherService.page(teacher, wrapper);

        return R.ok().data("teacher", teacher);

    }

    @PostMapping("add")
    public R addTeacher(@RequestBody(required = false) EduTeacherEntity eduTeacherEntity) {
        return  teacherService.save(eduTeacherEntity) ? R.ok() : R.error();
    }


    @GetMapping("/find/{id}")
    public R findById(@PathVariable String id) {
        return R.ok().data("teacher", teacherService.getById(id));
    }

    @PutMapping("/update")
    public R updateById(@RequestBody EduTeacherEntity eduTeacherEntity) {
        return teacherService.updateById(eduTeacherEntity) ? R.ok() : R.error();
    }
}

