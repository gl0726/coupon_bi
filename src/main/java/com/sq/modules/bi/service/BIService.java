package com.sq.modules.bi.service;

import com.sq.modules.bi.entity.vo.*;
import com.sq.modules.bi.entity.vo.active.ActiveCardResultVo;
import com.sq.modules.bi.entity.vo.exchange.ExchangeAmountResultVo;

/**
 * <p>
 * 雀实省钱BI 服务类
 * </p>
 *
 * @author zhongxunan
 * @since 2019-05-12
 */
public interface BIService {


    /**
     * 生成卡各面值金额占比分析
     */
    CardFaceAnalyzeVo getCardFaceAnalyze();

    /**
     * 各平台优惠券兑换情况分析
     */
    PlatformAnalyzeVo getPlatformAnalyze();

    /**
     * 优惠券兑换频次人数分布情况
     */
    FrequencyAnalyzeVo getFrequencyAnalyze();

    /**
     * 优惠券兑换次数商品排行榜
     */
    SkuAnalyzeVo getSkuAnalyze();

    /**
     * 兑换金额
     */
    ExchangeAmountResultVo getExchangeAmount();

    /**
     * 激活金额
     */
    ExchangeAmountResultVo getActiveAmount();

    /**
     * 激活卡数
     */
    ActiveCardResultVo getActiveCard();

    /**
     * 定制卡排行
     */
    CustomCardTop10ResultVo getCustomCardTop10();

}
