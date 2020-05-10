package com.sq.modules.bi.entity.vo.exchange;

import lombok.Data;
import java.io.Serializable;

/**
 *  兑换金额 返回结果
 */
@Data
public class ExchangeAmountResultVo implements Serializable {

    private static final long serialVersionUID = 3568211203498085157L;

    //昨日
    private ExchangeAmountVo lastDay;
    //近七天
    private ExchangeAmountVo last7day;
    //近30天
    private ExchangeAmountVo last30day;
    //月
    private ExchangeAmountVo lastMonth;
}
