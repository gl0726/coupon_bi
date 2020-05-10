package com.sq.modules.bi.entty2.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * BI2.0 各平台兑换、支付、佣金占比分析
 * </p>
 *
 * @author gl
 * @since 2018-08-16
 */
@Data
public class PlatformExgPayRateFour implements Serializable {
    private static final long serialVersionUID = 1L;
    //临时类-金额
    private int price;
    //临时类-数量
    private int numbers;
}
