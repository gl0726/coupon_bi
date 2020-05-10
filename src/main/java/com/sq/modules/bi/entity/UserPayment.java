package com.sq.modules.bi.entity;

import lombok.Data;

import java.io.Serializable;

/**
* <p>
    * 首页-基本概况-支付金额/支付人数/支付笔数/客单价（个）
    * </p>
*
* @author gl
* @since 2019-08-16
*/
@Data
public class UserPayment implements Serializable {

        private static final long serialVersionUID = 1L;
        private String createTime;
        /**
         * 支付人数
         */
        private Long paymentCount;

        /**
         * 支付笔数
         */
        private Long paymentTimes;

        /**
         * 支付金额
         */
        private Long paymentAmount;

        /**
         * 客单价
         */
        private Long unitPrice;

}
