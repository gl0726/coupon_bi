package com.sq.modules.bi.entity.vo.exchange;

import com.sq.modules.bi.entity.ExchangeCard;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ExchangeAmountVo implements Serializable {

    private static final long serialVersionUID = 3568211203498085157L;

    //昨日各个小时兑换金额数
    private List<ExchangeCard> currentRound;
    //前日各个小时兑换金额数
    private List<ExchangeCard> lastRound;
    //昨日日期
    private String dateBeginEnd;
    //昨日兑换金额总数
    private BigDecimal activieTotal;
    //上一日兑换金额总数
    private BigDecimal lastActivieTotal;
    //昨日上一日对比
    private String rat;
    //昨日平均
    private BigDecimal avg;
}
