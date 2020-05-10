package com.sq.modules.bi.entty2.Ranking;

import lombok.Data;

import java.io.Serializable;
/**
 * 兑换-数量类-Ranking的子类  注意！！后面的商品支付排行榜，结算佣金排行榜，因为产品要求没有小数点，所以将使用此模型类！
 * @author gl
 * @since 2018-08-08
 */
@Data
public class ExchangeQuantity extends Ranking implements Serializable {
    private static final long serialVersionUID = 1L;
    //兑换次数
    private long exchangeAmout;
}
