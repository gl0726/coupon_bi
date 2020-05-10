package com.sq.modules.bi.entity.vo.exchange;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 兑换金额
 * </p>
 *
 * @author zhongxunan
 * @since 2019-05-12
 */
@Data
public class ExchangeCardTotalVo implements Serializable {
    private static final long serialVersionUID = 3568211203498085114L;
    //昨日日期
    private String createTime;
    //昨日兑换金额总数
    private BigDecimal activieTotal;
    //昨日平均金额
    private BigDecimal avgAmount;


}
