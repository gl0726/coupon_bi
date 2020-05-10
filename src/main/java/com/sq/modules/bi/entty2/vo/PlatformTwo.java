package com.sq.modules.bi.entty2.vo;

import com.sq.modules.bi.entty2.Ranking.Ranking;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品结算佣金排行榜-金额
 * @author gl
 * @since 2018-08-08
 */
@Data
public class PlatformTwo implements Serializable {
    private static final long serialVersionUID = 1L;
    //排行
    private List<Ranking> list;
}
