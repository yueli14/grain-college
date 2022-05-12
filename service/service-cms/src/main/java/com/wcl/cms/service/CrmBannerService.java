package com.wcl.cms.service;

import com.wcl.cms.entity.CrmBannerEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wcl.utils.R;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author wcl
 * @since 2022-04-20
 */
public interface CrmBannerService extends IService<CrmBannerEntity> {
    List<CrmBannerEntity> getListLimit();
}
