package com.sq.modules.bi.entity.shop;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gl
 * @create 2020-04-28 9:47
 */

@Data
public class Transaction implements Serializable {

    //下单人数
    private int numberOrder;
    //下单笔数
    private int countOrder;
    //付款人数
    private int numberPayment;
    //付款笔数
    private int countPayment;
    //下单金额
    private double orderAmount;
    //客单价
    private double guestUnitPrice;
    //付款金额
    private double paymentAmount;
    //付款件数
    private int quantityPayment;
    //退款金额
    private double refundAmount;
    //售后订单数
    private double afterSaleOrder;
    //注册用户数
    private int registeredUsers;
    //积分发放量
    private int productComponent;
    //积分领取用户量
    private int pointCollection;


}
