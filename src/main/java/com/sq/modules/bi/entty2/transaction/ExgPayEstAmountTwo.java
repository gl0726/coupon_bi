package com.sq.modules.bi.entty2.transaction;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 兑换/支付/预估佣金金额-模型类
 * @author gl
 * @since 2018-08-14
 */
@Data
public class ExgPayEstAmountTwo implements Serializable {
    private static final long serialVersionUID = 1L;

    //兑换金额
    private int exchangeTotal;
    //支付金额
    private int payUser;
    //预估金额
    private int estAmount;

    //各时间段兑换金额
    private List<ExgPayEstAmountThree> exchangeRound;
    //各时间段支付金额
    private List<ExgPayEstAmountThree> payRound;
    //各时间段预计佣金
    private List<ExgPayEstAmountThree> estRound;

}
