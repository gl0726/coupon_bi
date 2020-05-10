package com.sq.modules.bi.entity.shop;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gl
 * @create 2020-05-06 14:18
 * 此类是用户下单统计
 */
@Data
public class UserOrderStatistics implements Serializable {
    //序号
    private int ranking;
    //姓名
    private String username;
    //电话
    private long mobile;
    //省份
    private String province;
    //地址
    private String address;
    //下单总量
    private long orderCounts;
    //消费总价
    private double totalPrice;
    //日期
    private String theDate;


}
