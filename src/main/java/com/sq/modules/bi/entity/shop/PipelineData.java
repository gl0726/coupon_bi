package com.sq.modules.bi.entity.shop;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gl
 * @create 2020-04-28 10:55
 * 此类是数据统计中流水数据
 */
@Data
public class PipelineData implements OrderStatistics {
    //时间
    private String createTime;
    //店铺编码
    private String storeCode;
    //交易流水
    private double orderAmount;
    //成功订单流水
    private double successfulOrdersAmount;
    //售后订单金额
    private double afterSaleOrdersAmount;
    
}
