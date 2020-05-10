package com.sq.modules.bi.entty2.transaction;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 兑换/支付次数
 * @author gl
 * @since 2018-08-13
 */
@Data
public class ExgPayTimes implements Serializable {
    private static final long serialVersionUID = 1L;

    //昨日
    private ExgPayTimesTwo lastDay;
    //近七天
    private ExgPayTimesTwo last7day;
    //近30天
    private ExgPayTimesTwo last30day;
    //月
    private ExgPayTimesTwo lastMonth;

}
