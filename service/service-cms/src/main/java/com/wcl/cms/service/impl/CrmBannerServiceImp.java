package com.wcl.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wcl.cms.entity.CrmBannerEntity;
import com.wcl.cms.mapper.CrmBannerMapper;
import com.wcl.cms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcl.utils.R;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author wcl
 * @since 2022-04-20
 */
@Service
public class CrmBannerServiceImp extends ServiceImpl<CrmBannerMapper, CrmBannerEntity> implements CrmBannerService {

    //redis缓存
    //key必须用‘’包裹起来
    //清空缓存
//    @CacheEvict(value = "banner", allEntries=true)
    @Cacheable(key = "'bannerList'", value = "banner")
    @Override
    public List<CrmBannerEntity> getListLimit() {
        QueryWrapper<CrmBannerEntity> crmBannerEntityQueryWrapper = new QueryWrapper<>();
        crmBannerEntityQueryWrapper.orderByDesc("sort");
        crmBannerEntityQueryWrapper.last("limit 2");
        return baseMapper.selectList(crmBannerEntityQueryWrapper);
    }
}
