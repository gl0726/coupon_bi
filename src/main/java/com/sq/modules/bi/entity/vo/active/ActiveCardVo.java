package com.sq.modules.bi.entity.vo.active;

import com.sq.modules.bi.entity.ActiveCard;
import com.sq.modules.bi.entity.ExchangeCard;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 激活金额
 */
@Data
public class ActiveCardVo implements Serializable {

    private static final long serialVersionUID = 3568211203498085157L;

    //昨日各个小时兑换金额数
    private List<ActiveCard> currentRound;
    //前日各个小时兑换金额数
    private List<ActiveCard> lastRound;
    //昨日日期
    private String dateBeginEnd;
    //昨日兑换金额总数
    private Long activieTotal;
    //上一日兑换金额总数
    private Long lastActivieTotal;
    //昨日上一日对比
    private String rat;
    //平均
    private BigDecimal avg;
}
