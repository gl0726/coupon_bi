package com.sq.modules.bi.entty2.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * BI2.0 用户关键流程漏斗分析
 * </p>
 *
 * @author gl
 * @since 2018-08-13
 */
@Data
public class UserKeyFlow implements Serializable {
    private static final long serialVersionUID = 1L;

    //开始日期
    private String beginDate;
    //结束日期
    private String endDate;
    //总体转化率
    private String totalRate;
    //注册成功人数
    private int regUser;
    //激活转化率
    private String activeRate;
    //激活成功人数
    private int activeUser;
    //兑换转化率
    private String exchangeRate;
    //兑换成功人数
    private int exchangeUser;
    //支付转化率
    private String paymentRate;
    //支付成功人数
    private int paymentUser;


}
