package com.sq.modules.bi.entity;

import lombok.Data;
import java.io.Serializable;

/**
* <p>
    * 优惠券兑换频次人数统计表
    * </p>
*
* @author zhongxunan
* @since 2019-05-13
*/
@Data
public class ExchangeFrequency implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 统计日期
            */
    private String createTime;

            /**
            * 兑换次数类型
            */
    private String deductionCount;

            /**
            * 各种兑换次数的总人数
            */
    private Long totalCustomer;


}
