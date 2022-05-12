package com.wcl.order.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 支付日志表
 * </p>
 *
 * @author wcl
 * @since 2022-05-05
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_pay_log")
public class PayLogEntity extends Model<PayLogEntity> {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 订单号
     */
    @TableField("order_no")
    private String orderNo;

    /**
     * 支付完成时间
     */
    @TableField("pay_time")
    private Date payTime;

    /**
     * 支付金额（分）
     */
    @TableField("total_fee")
    private BigDecimal totalFee;

    /**
     * 交易流水号
     */
    @TableField("transaction_id")
    private String transactionId;

    /**
     * 交易状态
     */
    @TableField("trade_state")
    private String tradeState;

    /**
     * 支付类型（1：微信 2：支付宝）
     */
    @TableField("pay_type")
    private Integer payType;

    /**
     * 其他属性
     */
    @TableField("attr")
    private String attr;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
