package com.sq.modules.bi.entity.vo;

import com.sq.modules.bi.entity.BusinessMakeAmountRank;
import com.sq.modules.bi.entity.BusinessMakeCardRank;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * 定制卡top10 返回结果
 */
@Data
public class CustomCardTop10ResultVo implements Serializable {

    private static final long serialVersionUID = 3568211203498085157L;

    //生成金额
    private List<BusinessMakeAmountRank> createAmountTop10;
    //激活金额
    private List<BusinessMakeAmountRank> activeAmountTop10;
    //兑换金额
    private List<BusinessMakeAmountRank> exchangeAmountTop10;
    //生成卡数
    private List<BusinessMakeCardRank> createCardTop10;
    //激活卡数
    private List<BusinessMakeCardRank> activeCardTop10;
}
