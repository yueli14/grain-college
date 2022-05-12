package com.wcl.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wcl.cms.entity.CrmBannerEntity;
import com.wcl.cms.service.impl.CrmBannerServiceImp;
import com.wcl.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

/**
 * <p>
 * 首页banner表 管理员操作
 * </p>
 *
 * @author wcl
 * @since 2022-04-20
 */
@CrossOrigin
@RestController
@RequestMapping("/educms/banner/admin")
public class CrmBannerAdminController {
    @Autowired
    private CrmBannerServiceImp serviceImp;


    @DeleteMapping("delete/{id}")
    public R deleteBanner(@PathVariable String id) {
        return serviceImp.removeById(id) ? R.ok().message("删除成功") : R.error().message("删除失败");
    }

    @PutMapping("update")
    public R updateBanner(@RequestBody CrmBannerEntity crmBannerEntity) {
        return serviceImp.updateById(crmBannerEntity) ? R.ok().message("保存成功") : R.error().message("保存失败");
    }

    @PostMapping("add")
    public R addBanner(@RequestBody CrmBannerEntity crmBannerEntity) {
        return serviceImp.save(crmBannerEntity) ? R.ok().message("保存成功") : R.error().message("保存失败");
    }

    @GetMapping("get/{id}")
    public R getBannerById(@PathVariable String id) {
        CrmBannerEntity crmBannerEntity = serviceImp.getById(id);
        return R.ok().data("banner", crmBannerEntity);
    }

    @GetMapping("page/{now}/{limit}")
    public R getBannerPage(@PathVariable Long now,
                           @PathVariable Long limit) {
        Page<CrmBannerEntity> crmBannerEntityPage = new Page<CrmBannerEntity>(now, limit);
        serviceImp.page(crmBannerEntityPage);
        return R.ok().data("page", crmBannerEntityPage);
    }
}

