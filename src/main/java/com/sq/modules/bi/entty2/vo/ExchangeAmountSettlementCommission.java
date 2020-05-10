package com.sq.modules.bi.entty2.vo;

import com.sq.modules.bi.entty2.Ranking.Platform;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * BI2.0 商品结算佣金排行榜-金额
 * </p>
 *
 * @author gl
 * @since 2018-08-09
 */
@Data
public class ExchangeAmountSettlementCommission implements Serializable {
    private static final long serialVersionUID = 1L;
    //全部
    private PlatformTwo all;
    //好品购
    private PlatformTwo gp;
    //淘宝
    private PlatformTwo tb;
    //天猫
    private PlatformTwo tm;
    //京东
    private PlatformTwo jd;
    //拼多多
    private PlatformTwo pdd;

}
