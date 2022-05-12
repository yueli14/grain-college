package com.wcl.ucenter.service;

import com.wcl.ucenter.entity.UcenterMemberEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wcl.ucenter.entity.vo.LoginVoInfo;
import com.wcl.ucenter.entity.vo.RegisterVoInfo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author wcl
 * @since 2022-04-23
 */
public interface UcenterMemberService extends IService<UcenterMemberEntity> {

    String login(LoginVoInfo voInfo);
    String register(RegisterVoInfo voInfo);
    UcenterMemberEntity getMemberByOpenid(String openid);
}
