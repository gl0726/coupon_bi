package com.sq.modules.bi.entity;

import lombok.Data;

import java.io.Serializable;

/**
* <p>
    * 首页-基本概况-结算佣金/已结算订单(个)/人均结算佣金
    * </p>
*
* @author gl
* @since 2019-08-16
*/
@Data
public class UserSettlement implements Serializable {

        private static final long serialVersionUID = 1L;
        private String createTime;
        /**
         * 已结算订单(个)
         */
        private Long paymentTimes;

        /**
         * 结算佣金
         */
        private Long paymentAmount;

        /**
         * 人均结算佣金
         */
        private Long perCommission;

}
