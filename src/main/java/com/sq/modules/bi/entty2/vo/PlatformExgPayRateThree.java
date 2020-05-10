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
public class PlatformExgPayRateThree implements Serializable {
    private static final long serialVersionUID = 1L;
    //好品购
    private int self;
    //天猫
    private int tm;
    //淘宝
    private int tb;
    //京东
    private int jd;
    //拼多多
    private int pdd;
}
