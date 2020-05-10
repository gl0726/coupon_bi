package com.sq.modules.bi.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 注册会员总余额统计表
 * </p>
 *
 * @author zhongxunan
 * @since 2019-05-12
 */
@Data
public class DailyRegisteredUserBalance implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 统计日期
     */
    private String createTime;

    /**
     * 注册会员总余额
     */
    private Long totalAmount;


}
