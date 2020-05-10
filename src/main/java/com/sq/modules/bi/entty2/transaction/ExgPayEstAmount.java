package com.sq.modules.bi.entty2.transaction;

import lombok.Data;

import java.io.Serializable;

/**
 * 兑换/支付/预估佣金金额
 * @author gl
 * @since 2018-08-14
 */
@Data
public class ExgPayEstAmount implements Serializable {
    private static final long serialVersionUID = 1L;

    //昨日
    private ExgPayEstAmountTwo lastDay;
    //近七天
    private ExgPayEstAmountTwo last7day;
    //近30天
    private ExgPayEstAmountTwo last30day;
    //月
    private ExgPayEstAmountTwo lastMonth;

}
