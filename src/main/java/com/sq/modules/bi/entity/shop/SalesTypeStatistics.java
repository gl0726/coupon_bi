package com.sq.modules.bi.entity.shop;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gl
 * @create 2020-05-06 9:53
 * 销售种类统计
 */
@Data
public class SalesTypeStatistics implements Serializable {
    //排名
    private int ranking;
    //商品分类
    private String saleType;
    //支付金额
    private double paymentAmount;
    //支付金额占比
    private double paymentsThan;

}
