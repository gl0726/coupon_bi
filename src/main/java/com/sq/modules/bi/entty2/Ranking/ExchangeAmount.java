package com.sq.modules.bi.entty2.Ranking;

import lombok.Data;

import java.io.Serializable;

/**
 * 兑换-金额类-Ranking的子类
 * @author gl
 * @since 2018-08-08
 */
@Data
public class ExchangeAmount extends Ranking implements Serializable {
    private static final long serialVersionUID = 1L;
    //兑换金额
    private double exchangeAmount;
}
