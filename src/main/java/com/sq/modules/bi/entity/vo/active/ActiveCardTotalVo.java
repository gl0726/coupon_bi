package com.sq.modules.bi.entity.vo.active;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 激活金额 统计
 * </p>
 *
 * @author zhongxunan
 * @since 2019-05-12
 */
@Data
public class ActiveCardTotalVo implements Serializable {
    private static final long serialVersionUID = 3568211203498085114L;
    //日期
    private String createTime;
    //兑换金额总数
    private Long activieTotal;
    //平均金额
    private BigDecimal avgAmount;


}
