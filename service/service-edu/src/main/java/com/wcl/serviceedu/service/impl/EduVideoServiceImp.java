package com.wcl.serviceedu.service.impl;

import com.wcl.serviceedu.entity.EduVideoEntity;
import com.wcl.serviceedu.mapper.EduVideoMapper;
import com.wcl.serviceedu.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author wcl
 * @since 2022-04-10
 */
@Service
public class EduVideoServiceImp extends ServiceImpl<EduVideoMapper, EduVideoEntity> implements EduVideoService {

    @Override
    public List<String> getAllVideoSourceIdByCourseId(String id) {
        return baseMapper.getAllVideoSourceIdByCourseId(id);
    }
}
