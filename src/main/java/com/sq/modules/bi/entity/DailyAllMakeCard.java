package com.sq.modules.bi.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* <p>
    * 生成卡总数和金额统计
    * </p>
*
* @author zhongxunan
* @since 2019-05-12
*/
@Data
public class DailyAllMakeCard implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 统计日期
         */
        private String createTime;

        /**
         * 生成卡总数
         */
        private Long totalCount;

        /**
         * 生成卡总金额
         */
        private Long totalValue;


}
