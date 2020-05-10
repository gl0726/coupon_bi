package com.sq.modules.bi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 各平台优惠券兑换统计表
 * </p>
 *
 * @author zhongxunan
 * @since 2019-05-13
 */
@Data
public class PlatformCouponExchange implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 统计日期
     */
    private String createTime;

    /**
     * 抵扣平台
     */
    private String deduction;

    /**
     * 各抵扣平台的兑换金额总数
     */
    private BigDecimal totalAmount;

    /**
     * 各抵扣平台的兑换人数
     */
    private Long totalCustomer;

    /**
     * 占比
     */
    private Double rat;

    /**
     * 占比
     */
    private Double personRat;
}
