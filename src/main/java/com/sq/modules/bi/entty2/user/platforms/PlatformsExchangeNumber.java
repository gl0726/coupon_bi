package com.sq.modules.bi.entty2.user.platforms;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * BI2.0 用户-各平台兑换、支付趋势分析-兑换人数/支付人数-样本类
 * </p>
 *
 * @author gl
 * @since 2018-08-12
 */
@Data
public class PlatformsExchangeNumber implements Serializable {

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
    private List<Exchange> selfRound;
    //天猫集合
    private List<Exchange> tmRound;
    //淘宝集合
    private List<Exchange> tbRound;
    //京东集合
    private List<Exchange> jdRound;
    //拼多多集合
    private List<Exchange> pddRound;


}
