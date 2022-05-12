package com.wcl.ucenter.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wcl.ucenter.entity.UcenterMemberEntity;
import com.wcl.ucenter.entity.vo.LoginVoInfo;
import com.wcl.ucenter.entity.vo.RegisterVoInfo;
import com.wcl.ucenter.mapper.UcenterMemberMapper;
import com.wcl.ucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcl.utils.JwtUtil;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author wcl
 * @since 2022-04-23
 */
@Service
public class UcenterMemberServiceImp extends ServiceImpl<UcenterMemberMapper, UcenterMemberEntity> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(LoginVoInfo voInfo) {


        if (!StringUtils.hasLength(voInfo.getPassword()) || !StringUtils.hasLength(voInfo.getMobile())) {
            return "登陆失败";
        }
        QueryWrapper<UcenterMemberEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", voInfo.getMobile());
        UcenterMemberEntity entity = baseMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(entity)) {
            return "登陆失败";
        }

        //密码判断
        if (!SecureUtil.md5(voInfo.getPassword()).equals(entity.getPassword())) {
            return "登陆失败";
        }

        //禁用判断
        if (entity.getDisabled()) {
            return "登陆失败";
        }

        //生成token
        return JwtUtil.getJwtToken(entity.getId(), entity.getNickname());
    }

    @Override
    public String register(RegisterVoInfo voInfo) {
        if (!StringUtils.hasLength(voInfo.getPassword()) || !StringUtils.hasLength(voInfo.getMobile()) ||
                !StringUtils.hasLength(voInfo.getNickName()) || !StringUtils.hasLength(voInfo.getCode())) {
            return "注册失败";
        }
        //验证码判断
        // 取出来的值会多一对引号
        String mobileCode = redisTemplate.opsForValue().get(voInfo.getMobile());
        if (!mobileCode.equals(voInfo.getCode())) {
            return "注册失败";
        }

        //判断唯一手机号
        QueryWrapper<UcenterMemberEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", voInfo.getMobile());
        Long aLong = baseMapper.selectCount(wrapper);
        if (aLong != 0) {
            return "注册失败";
        }

        //加密写入数据库
        String password = SecureUtil.md5(voInfo.getPassword());
        UcenterMemberEntity ucenterMemberEntity = new UcenterMemberEntity();
        BeanUtils.copyProperties(voInfo, ucenterMemberEntity);
        ucenterMemberEntity.setDisabled(false);
        ucenterMemberEntity.setPassword(password);
        return baseMapper.insert(ucenterMemberEntity) > 0 ? "注册成功" : "注册失败";
    }

    /**
     * 根据openid查询shujuku
     *
     * @param openid
     * @return
     */
    @Override
    public UcenterMemberEntity getMemberByOpenid(String openid) {
        QueryWrapper<UcenterMemberEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);

        return baseMapper.selectOne(queryWrapper);
    }


}
