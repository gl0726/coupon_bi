package com.sq.modules.bi.entty2.transaction;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 兑换/支付次数-模型类
 * @author gl
 * @since 2018-08-13
 */
@Data
public class ExgPayTimesTwo implements Serializable {
    private static final long serialVersionUID = 1L;

    //兑换次数
    private int exchangeTotal;
    //支付笔数
    private int payTotal;

    //各时间段兑换次数
    private List<ExgPayTimesThree> exchangeRound;
    //各时间段支付比数
    private List<ExgPayTimesThree> payRound;

}
