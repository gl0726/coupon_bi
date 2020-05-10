package com.sq.modules.bi.entty2.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * BI2.0 用户账户余额人数统计-临时类
 * </p>
 *
 * @author gl
 * @since 2018-08-13
 */
@Data
public class UserCountTwo implements Serializable {
    private static final long serialVersionUID = 1L;

    //余额名
    private String amount_name;
    //余额数
    private int total;

}
