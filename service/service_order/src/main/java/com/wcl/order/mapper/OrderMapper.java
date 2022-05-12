package com.wcl.order.mapper;

import com.wcl.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author wcl
 * @since 2022-05-05
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderEntity> {

}
