package com.sq.modules.bi.entty2.user.activation;


import lombok.Data;

import java.io.Serializable;

/**
 * 激活/兑换/支付人数
 * @author gl
 * @since 2018-08-013
 */
@Data
public class ActExgPayUser implements Serializable {
    private static final long serialVersionUID = 1L;

    //昨日
    private ActExgPayUserTwo lastDay;
    //近七天
    private ActExgPayUserTwo last7day;
    //近30天
    private ActExgPayUserTwo last30day;
    //月
    private ActExgPayUserTwo lastMonth;


}
