package com.sq.modules.bi.entity;

import lombok.Data;

import java.io.Serializable;

/**
* <p>
    * 首页-基本概况-激活人数/兑换人数（个）
    * </p>
*
* @author gl
* @since 2019-08-16
*/
@Data
public class ActivateExchange implements Serializable {

        private static final long serialVersionUID = 1L;

        private String createTime;
        /**
         * 激活人数
         */
        private Long activationTotalCount;

        /**
         * 兑换人数
         */
        private Long exchangeTotalCount;

}
