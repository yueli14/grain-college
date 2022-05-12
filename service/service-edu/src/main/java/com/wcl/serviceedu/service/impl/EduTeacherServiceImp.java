package com.wcl.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wcl.serviceedu.entity.EduTeacherEntity;
import com.wcl.serviceedu.mapper.EduTeacherMapper;
import com.wcl.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author wcl
 * @since 2022-04-01
 */
@Service
public class EduTeacherServiceImp extends ServiceImpl<EduTeacherMapper, EduTeacherEntity> implements EduTeacherService {

    @Override
    public List getIndexTeacher() {
        QueryWrapper<EduTeacherEntity> eduTeacherEntityQueryWrapper = new QueryWrapper<>();
        eduTeacherEntityQueryWrapper.orderByDesc("sort");
        eduTeacherEntityQueryWrapper.last("limit 4");
        return baseMapper.selectList(eduTeacherEntityQueryWrapper);

    }
}
