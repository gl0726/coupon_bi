package com.sq.modules.bi.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 兑换信息统计表
 * </p>
 *
 * @author zhongxunan
 * @since 2019-05-12
 */
@Data
public class DailyExchangeInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 统计日期
     */
    private String createTime;

    /**
     * 总兑换金额总数
     */
    private Long totalAmount;

    /**
     * 人均兑换金额
     */
    private Long perExchangeAmount;


}
