package com.sq.modules.customer.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lq
 * @date 2019\4\17 0017 16:50
 */

@Data
public class SigninStatisticsVo implements Serializable {
    private static final long serialVersionUID = 5862033754693071777L;
    /**
     * 连续签到天数
     */
    private Long continuitySigin;
    /**
     *
     */
    private BigDecimal bonusesTotal;
}
