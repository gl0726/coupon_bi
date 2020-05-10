package com.sq.modules.bi.entity;

import lombok.Data;

import java.io.Serializable;

/**
* <p>
    * 首页-基本概况-预估佣金(元)
    * </p>
*
* @author gl
* @since 2019-08-16
*/
@Data
public class EstimatedCommission implements Serializable {

        private static final long serialVersionUID = 1L;

        private String createTime;
        /**
         * 预估佣金
         */
        private Long estimatePrice;


}
