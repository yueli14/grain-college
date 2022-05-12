/**
 * @Classname CrmBannerUserController
 * @Description 用户
 * @Date 2022/4/20 16:14
 * @Created by 28327
 */

package com.wcl.cms.controller;


import com.wcl.cms.entity.CrmBannerEntity;
import com.wcl.cms.service.impl.CrmBannerServiceImp;
import com.wcl.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/educms/banner/user")
public class CrmBannerUserController {
    @Autowired
    private CrmBannerServiceImp serviceImp;

    @GetMapping("get/all")
    public R getAllBanner() {
        List<CrmBannerEntity> list = serviceImp.getListLimit();
        return R.ok().data("bannerList", list);
    }
}