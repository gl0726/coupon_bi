package com.sq.modules.bi.entty2.user.activation;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 激活/兑换/支付人数-模型类
 * @author gl
 * @since 2018-08-013
 */
@Data
public class ActExgPayUserTwo implements Serializable {
    private static final long serialVersionUID = 1L;

    //激活人数
    private int activieTotal;
    //兑换人数
    private int lastActivieTotal;
    //支付人数
    private int payUser;

    //昨日/7/30各个时间激活人数
    private List<ActExgPayUserThree> activieRound;
    //昨日/7/30各个时间兑换人数
    private List<ActExgPayUserThree> exchangeRound;
    //昨日/7/30各个时间支付人数
    private List<ActExgPayUserThree> payRound;

}
