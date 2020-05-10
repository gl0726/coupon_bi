package com.sq.modules.bi.entty2.user.platforms;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * BI2.0 用户-各平台兑换、支付趋势分析-兑换人数/支付人数
 * </p>
 *
 * @author gl
 * @since 2018-08-12
 */
@Data
public class Exchange implements Serializable {
    private static final long serialVersionUID = 1L;

    //日期
    private String theTime;
    //兑换人数 或者 支付人数
    private int totalCount;

}
