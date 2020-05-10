package com.sq.modules.bi.entity.shop;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author gl
 * @create 2020-04-28 10:55
 * 此类是数据统计中订单数据
 */
@Data
public class OrderData implements OrderStatistics {
    //时间
    private String createTime;
    //店铺编码
    private String storeCode;
    //订单数量
    private int orderQuantity;
    //成功订单数
    private int successfulOrders;
    //售后订单数
    private int afterSaleOrders;

}
