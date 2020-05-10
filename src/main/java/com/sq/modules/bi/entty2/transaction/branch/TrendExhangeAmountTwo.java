package com.sq.modules.bi.entty2.transaction.branch;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * BI2.0 各平台兑换、支付、佣金趋势分析-兑换金额-支付金额-预估佣金模型类-模型类
 * </p>
 *
 * @author gl
 * @since 2018-08-14
 */
@Data
public class TrendExhangeAmountTwo  implements Serializable {

    private static final long serialVersionUID = 1L;

    //好品购总数
    private int self;
    //天猫总数
    private int tm;
    //淘宝总数
    private int tb;
    //京东总数
    private int jd;
    //拼多多总数
    private int pdd;

    //好品购集合
    private List<TrendExhangeAmountThree> selfRound;
    //天猫集合
    private List<TrendExhangeAmountThree> tmRound;
    //淘宝集合
    private List<TrendExhangeAmountThree> tbRound;
    //京东集合
    private List<TrendExhangeAmountThree> jdRound;
    //拼多多集合
    private List<TrendExhangeAmountThree> pddRound;

}
