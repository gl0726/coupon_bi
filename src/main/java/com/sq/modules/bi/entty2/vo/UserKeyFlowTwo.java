package com.sq.modules.bi.entty2.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * BI2.0 用户关键流程漏斗分析-临时类
 * </p>
 *
 * @author gl
 * @since 2018-08-13
 */
@Data
public class UserKeyFlowTwo implements Serializable {
    private static final long serialVersionUID = 1L;

    //注册成功人数
    private int regUser;
    //激活成功人数
    private int activeUser;
    //兑换成功人数
    private int exchangeUser;
    //支付成功人数
    private int paymentUser;


}
