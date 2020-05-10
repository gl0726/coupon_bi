package com.sq.modules.bi.entty2.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户-注册用户数-临时数据封装类
 * @author gl
 * @since 2018-08-09
 */
@Data
public class ActiveSum implements Serializable {

    private static final long serialVersionUID = 1L;

    //开始结束时间
    private String createTime;

    //一日/近七天/月注册人数总数
    private long activieTotal;

    //一日/近七天/月注册人数总数-平均值
    private double avgAmount;


}
