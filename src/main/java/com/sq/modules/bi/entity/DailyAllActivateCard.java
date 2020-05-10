package com.sq.modules.bi.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 激活卡总数和激活卡总金额统计
 * </p>
 *
 * @author zhongxunan
 * @since 2019-05-12
 */
@Data
public class DailyAllActivateCard implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 统计日期
     */
    private String createTime;

    /**
     * 激活卡总数
     */
    private Long totalCount;

    /**
     * 激活卡总金额
     */
    private Long totalValue;


}
