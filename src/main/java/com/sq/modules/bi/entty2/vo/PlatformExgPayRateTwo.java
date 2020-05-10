package com.sq.modules.bi.entty2.vo;

import com.sq.modules.bi.entty2.Ranking.Platform;
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
public class PlatformExgPayRateTwo implements Serializable {
    private static final long serialVersionUID = 1L;

    //兑换
    private PlatformExgPayRateThree exchange;
    //支付
    private PlatformExgPayRateThree pay;
    //预估佣金
    private PlatformExgPayRateThree estAmount;
    //结算佣金
    private PlatformExgPayRateThree settleAmount;

}
