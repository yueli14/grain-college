package com.wcl.order.mapper;

import com.wcl.order.entity.PayLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 支付日志表 Mapper 接口
 * </p>
 *
 * @author wcl
 * @since 2022-05-05
 */
@Mapper
public interface PayLogMapper extends BaseMapper<PayLogEntity> {

}
