package com.sq.modules.bi.entity.vo;

import com.sq.modules.bi.entity.ExchangedGoodsRank;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class SkuAnalyzeVo implements Serializable {

    private static final long serialVersionUID = 3568211203498085157L;

    private List<ExchangedGoodsRank> top10;

}
