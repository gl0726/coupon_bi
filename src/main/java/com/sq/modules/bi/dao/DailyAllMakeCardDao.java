package com.sq.modules.bi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sq.modules.bi.entity.*;
import com.sq.modules.bi.entity.vo.exchange.ExchangeCardTotalVo;
import com.sq.modules.bi.entity.vo.active.ActiveCardTotalVo;
import com.sq.modules.bi.entty2.Ranking.Ranking;
import com.sq.modules.bi.entty2.transaction.ExgPayEstAmountThree;
import com.sq.modules.bi.entty2.transaction.ExgPayTimesThree;
import com.sq.modules.bi.entty2.transaction.branch.TrendExhangeAmountThree;
import com.sq.modules.bi.entty2.user.ActiveSum;
import com.sq.modules.bi.entty2.user.ActiveUser;
import com.sq.modules.bi.entty2.user.activation.ActExgPayUserThree;
import com.sq.modules.bi.entty2.user.platforms.Exchange;
import com.sq.modules.bi.entty2.vo.*;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 基本信息统计
 * </p>
 *
 * @author zhongxunan
 * @since 2019-05-12
 */
@Mapper
public interface DailyAllMakeCardDao extends BaseMapper<DailyAllMakeCard> {
    DailyAllMakeCard getMaxMakeCard();

    DailyAllActivateCard getMaxActivateCard();

    DailyExchangeInformation getMaxExchangeInfor();

    DailyRegisteredUser getTotalUser();

    DailyRegisteredUserBalance getTotalUserBalance();

    //BI 2.0 首页基本信息，10个新字段
    ActivateExchange getAE();

    UserPayment getUP();

    EstimatedCommission getEC();

    UserSettlement getUS();



    List<OrdinaryCard> getOrdinaryCardRate(String date);

    List<CustomCard> getCustomCardRate(String date);

    List<PlatformCouponExchange> getPlatformAnalyze(String date);

    List<ExchangeFrequency> getFrequencyAnalyze(String date);

    List<ExchangedGoodsRank> getSkuAnalyze(String date);

    //兑换卡金额  开始
    List<ExchangeCard>   getExchangeCard(String date);

    ExchangeCardTotalVo getExchangeCardTotal(String date);

    List<ExchangeCard>   get7ExchangeCard(String date);

    ExchangeCardTotalVo get7ExchangeCardTotal(String date);

    ExchangeCardTotalVo getNext7ExchangeCardTotal(String date);

    List<ExchangeCard>  get30ExchangeCard(String date);

    ExchangeCardTotalVo get30ExchangeCardTotal(String date);

    ExchangeCardTotalVo getNext30ExchangeCardTotal(String date);

    List<ExchangeCard> getMonthExchangeCard(String theMonth);

    ExchangeCardTotalVo get12MonthExchangeCardAvg(Map map);

    List<ExchangeCard> get12MonthExchangeCard(Map map);
    //兑换卡金额  结束

    //激活卡金额 开始
    List<ExchangeCard>   getActiveCard(String date);

    ExchangeCardTotalVo getActiveCardTotal(String date);

    List<ExchangeCard>   get7ActiveCard(String date);

    ExchangeCardTotalVo get7ActiveCardTotal(String date);

    ExchangeCardTotalVo getNext7ActiveCardTotal(String date);

    List<ExchangeCard>  get30ActiveCard(String date);

    ExchangeCardTotalVo get30ActiveCardTotal(String date);

    ExchangeCardTotalVo getNext30ActiveCardTotal(String date);

    List<ExchangeCard> getMonthActiveCard(String theMonth);

    ExchangeCardTotalVo get12MonthActiveCardAvg(Map map);

    List<ExchangeCard> get12MonthActiveCard(Map map);
    //激活卡金额  结束

    //激活卡数  开始
    List<ActiveCard>   getActiveCardNum(String date);

    ActiveCardTotalVo getActiveCardTotalNum(String date);

    List<ActiveCard>   get7ActiveCardNum(String date);

    ActiveCardTotalVo get7ActiveCardTotalNum(String date);

    ActiveCardTotalVo getNext7ActiveCardTotalNum(String date);

    List<ActiveCard>  get30ActiveCardNum(String date);

    ActiveCardTotalVo get30ActiveCardTotalNum(String date);

    ActiveCardTotalVo getNext30ActiveCardTotalNum(String date);

    List<ActiveCard> getMonthActiveCardNum(String theMonth);

    ActiveCardTotalVo get12MonthActiveCardAvgNum(Map map);

    List<ActiveCard> get12MonthActiveCardNum(Map map);
    //激活卡数  结束

    //定制卡排行 开始
    List<BusinessMakeAmountRank> getCreateAmountTop10(String date);

    List<BusinessMakeAmountRank> getActiceAmountTop10(String date);

    List<BusinessMakeAmountRank> getExcahngeAmountTop10(String date);

    List<BusinessMakeCardRank> getCreateCardTop10(String date);

    List<BusinessMakeCardRank> getExchangeCardTop10(String date);
    //定制卡排行 结束


    //###################################################### BI2.0  gl

    //商品兑换排行榜-数量-开始

    List<Ranking> getExchangeQuantityAllYesterday(String date);

    List<Ranking> getExchangeQuantityAll7Day(String date);

    List<Ranking> getExchangeQuantityAll30Day(String date);

    List<Ranking> getExchangeQuantityAllCumulativeDay(String date);


    List<Ranking> getExchangeQuantityGPYesterday(String date);

    List<Ranking> getExchangeQuantityGP7Day(String date);

    List<Ranking> getExchangeQuantityGP30Day(String date);

    List<Ranking> getExchangeQuantityGPCumulativeDay(String date);


    List<Ranking> getExchangeQuantityTBYesterday(String date);

    List<Ranking> getExchangeQuantityTB7Day(String date);

    List<Ranking> getExchangeQuantityTB30Day(String date);

    List<Ranking> getExchangeQuantityTBCumulativeDay(String date);


    List<Ranking> getExchangeQuantityTMYesterday(String date);

    List<Ranking> getExchangeQuantityTM7Day(String date);

    List<Ranking> getExchangeQuantityTM30Day(String date);

    List<Ranking> getExchangeQuantityTMCumulativeDay(String date);


    List<Ranking> getExchangeQuantityJDYesterday(String date);

    List<Ranking> getExchangeQuantityJD7Day(String date);

    List<Ranking> getExchangeQuantityJD30Day(String date);

    List<Ranking> getExchangeQuantityJDCumulativeDay(String date);


    List<Ranking> getExchangeQuantityPDDYesterday(String date);

    List<Ranking> getExchangeQuantityPDD7Day(String date);

    List<Ranking> getExchangeQuantityPDD30Day(String date);

    List<Ranking> getExchangeQuantityPDDCumulativeDay(String date);

    //商品兑换排行榜-数量-结束


    //商品兑换排行榜-金额-开始

    List<Ranking> getCommodityExchangeAmountAllYesterday(String date);

    List<Ranking> getCommodityExchangeAmountAll7Day(String date);

    List<Ranking> getCommodityExchangeAmountAll30Day(String date);

    List<Ranking> getCommodityExchangeAmountAllCumulativeDay(String date);


    List<Ranking> getCommodityExchangeAmountGPYesterday(String date);

    List<Ranking> getCommodityExchangeAmountGP7Day(String date);

    List<Ranking> getCommodityExchangeAmountGP30Day(String date);

    List<Ranking> getCommodityExchangeAmountGPCumulativeDay(String date);


    List<Ranking> getCommodityExchangeAmountTBYesterday(String date);

    List<Ranking> getCommodityExchangeAmountTB7Day(String date);

    List<Ranking> getCommodityExchangeAmountTB30Day(String date);

    List<Ranking> getCommodityExchangeAmountTBCumulativeDay(String date);


    List<Ranking> getCommodityExchangeAmountTMYesterday(String date);

    List<Ranking> getCommodityExchangeAmountTM7Day(String date);

    List<Ranking> getCommodityExchangeAmountTM30Day(String date);

    List<Ranking> getCommodityExchangeAmountTMCumulativeDay(String date);


    List<Ranking> getCommodityExchangeAmountJDYesterday(String date);

    List<Ranking> getCommodityExchangeAmountJD7Day(String date);

    List<Ranking> getCommodityExchangeAmountJD30Day(String date);

    List<Ranking> getCommodityExchangeAmountJDCumulativeDay(String date);


    List<Ranking> getCommodityExchangeAmountPDDYesterday(String date);

    List<Ranking> getCommodityExchangeAmountPDD7Day(String date);

    List<Ranking> getCommodityExchangeAmountPDD30Day(String date);

    List<Ranking> getCommodityExchangeAmountPDDCumulativeDay(String date);

    //商品兑换排行榜-金额-结束

    //商品支付排行榜-金额-开始   这里表结构变了，都在一张表里，所以查询就方便多了
    List<Ranking> getExchangePaymentInfo(Map map);
    //商品支付排行榜-金额-结束

    //商品支付排行榜-数量-开始   这里表结构变了，都在一张表里，所以查询就方便多了
    List<Ranking> getExchangePaymentNumberInfo(Map map);
    //商品支付排行榜-数量-结束


    //商品结算佣金排行榜-金额-开始   这里表结构变了，都在一张表里，所以查询就方便多了
    List<Ranking> getSettlementCommissionInfo(Map map);
    //商品结算佣金排行榜-金额-结束


    //商品结算佣金排行榜-数量-开始   这里表结构变了，都在一张表里，所以查询就方便多了
    List<Ranking> getSettlementCommissionNumberInfo(Map map);
    //商品结算佣金排行榜-数量-结束


    //注册用户数-开始
    List<ActiveUser> getActiveUsersNum(String date);

    List<ActiveUser> get7ActiveUsersNum(String date);

    List<ActiveUser> get30ActiveUsersNum(String date);

    List<ActiveUser> getMonthActiveUsersNum(String date);

    ActiveSum getActiveUsersTotalNum(String date);

    ActiveSum get7ActiveUsersTotalNum(String date);

    ActiveSum get30ActiveUsersTotalNum(String date);

    ActiveSum get12MonthActiveUsersAvgNum(String date);

    List<ActiveUser> get12MonthActiveUsersNum(Map map);
    //注册用户数-结束

    //各平台兑换、支付趋势分析--兑换人数  开始
    List<Exchange> getPlatformNumber(Map map);

    List<Exchange> get7PlatformNumber(Map map);

    List<Exchange> get30PlatformNumber(Map map);

    List<Exchange> get12PlatformNumber(Map map);
    //各平台兑换、支付趋势分析--兑换人数  结束


    //各平台兑换、支付趋势分析--支付人数  开始
    List<Exchange> getPaymentNumber(Map map);

    List<Exchange> get7PaymentNumber(Map map);

    List<Exchange> get30PaymentNumber(Map map);

    List<Exchange> get12PaymentNumber(Map map);
    //各平台兑换、支付趋势分析--支付人数  结束

    //用户账户余额人数统计  开始
    List<UserCountTwo> getUserAccount(String date);
    //用户账户余额人数统计  结束


    //用户账户余额为0元趋势分析  开始
    List<TrendAnalysisTwo> getbalanceAnalyze(Map date);
    //用户账户余额为0元趋势分析  结束

    //用户关键流程漏斗分析  开始
    UserKeyFlowTwo getUserKeyFlow(String date);
    //用户关键流程漏斗分析  结束


    //兑换、支付频次人数分布  开始
    List<FrequencyAnalyzeTwo> getFrequencyAnalyzePay(String date);

    List<FrequencyAnalyzeTwo> getFrequencyAnalyzeExchange(String date);
    //兑换、支付频次人数分布  结束

    //激活/兑换/支付人数    开始
    List<ActExgPayUserThree> getActExgPayUserInfoAct(String date);

    List<ActExgPayUserThree> getActExgPayUserInfoExg(String date);

    List<ActExgPayUserThree> getActExgPayUserInfoPay(String date);

    List<ActExgPayUserThree> get7ActExgPayUserInfoAct(String date);

    List<ActExgPayUserThree> get7ActExgPayUserInfoExg(String date);

    List<ActExgPayUserThree> get7ActExgPayUserInfoPay(String date);

    List<ActExgPayUserThree> get30ActExgPayUserInfoAct(String date);

    List<ActExgPayUserThree> get30ActExgPayUserInfoExg(String date);

    List<ActExgPayUserThree> get30ActExgPayUserInfoPay(String date);

    List<ActExgPayUserThree> get12ActExgPayUserInfoAct(Map map);

    List<ActExgPayUserThree> get12ActExgPayUserInfoExg(Map map);

    List<ActExgPayUserThree> get12ActExgPayUserInfoPay(Map map);
    //激活/兑换/支付人数    结束


    //兑换/支付次数    开始
    List<ExgPayTimesThree> getExgTimesInfoDao(String date);

    List<ExgPayTimesThree> getPayTimesInfoDao(String date);

    List<ExgPayTimesThree> get7ExgTimesInfoDao(String date);

    List<ExgPayTimesThree> get7PayTimesInfoDao(String date);

    List<ExgPayTimesThree> get30ExgTimesInfoDao(String date);

    List<ExgPayTimesThree> get30PayTimesInfoDao(String date);

    List<ExgPayTimesThree> get12ExgTimesInfoDao(Map map);

    List<ExgPayTimesThree> get12PayTimesInfoDao(Map map);
    //兑换/支付次数    结束


    //兑换/支付/预估佣金金额   开始
    List<ExgPayEstAmountThree> getExgPayEstAmountInfoExg(String date);

    List<ExgPayEstAmountThree> getExgPayEstAmountInfoPay(String date);

    List<ExgPayEstAmountThree> getExgPayEstAmountInfoEst(String date);

    List<ExgPayEstAmountThree> get7ExgPayEstAmountInfoExg(String date);

    List<ExgPayEstAmountThree> get7ExgPayEstAmountInfoPay(String date);

    List<ExgPayEstAmountThree> get7ExgPayEstAmountInfoEst(String date);

    List<ExgPayEstAmountThree> get30ExgPayEstAmountInfoExg(String date);

    List<ExgPayEstAmountThree> get30ExgPayEstAmountInfoPay(String date);

    List<ExgPayEstAmountThree> get30ExgPayEstAmountInfoEst(String date);

    List<ExgPayEstAmountThree> get12ExgPayEstAmountInfoExg(Map map);

    List<ExgPayEstAmountThree> get12ExgPayEstAmountInfoPay(Map map);

    List<ExgPayEstAmountThree> get12ExgPayEstAmountInfoEst(Map map);
    //兑换/支付/预估佣金金额    结束

    //兑换/支付/预估佣金金额---兑换金额    开始
    List<TrendExhangeAmountThree> getTrendExhangeAmount(Map map);

    List<TrendExhangeAmountThree> get7TrendExhangeAmount(Map map);

    List<TrendExhangeAmountThree> get30TrendExhangeAmount(Map map);

    List<TrendExhangeAmountThree> get12TrendExhangeAmount(Map map);
    //兑换/支付/预估佣金金额---兑换金额    结束


    //兑换/支付/预估佣金金额---支付金额    开始
    List<TrendExhangeAmountThree> getTrendPayAmount(Map map);

    List<TrendExhangeAmountThree> get7TrendPayAmount(Map map);

    List<TrendExhangeAmountThree> get30TrendPayAmount(Map map);

    List<TrendExhangeAmountThree> get12TrendPayAmount(Map map);
    //兑换/支付/预估佣金金额---支付金额    结束


    //兑换/支付/预估佣金金额---预估佣金    开始
    List<TrendExhangeAmountThree> getTrendEstAmountInfo(Map map);

    List<TrendExhangeAmountThree> get7TrendEstAmountInfo(Map map);

    List<TrendExhangeAmountThree> get30TrendEstAmountInfo(Map map);

    List<TrendExhangeAmountThree> get12TrendEstAmountInfo(Map map);
    //兑换/支付/预估佣金金额---预估佣金    结束


    //兑换/支付/预估佣金金额---兑换次数    开始
    List<TrendExhangeAmountThree> getTrendExchangTimes(Map map);

    List<TrendExhangeAmountThree> get7TrendExchangTimes(Map map);

    List<TrendExhangeAmountThree> get30TrendExchangTimes(Map map);

    List<TrendExhangeAmountThree> get12TrendExchangTimes(Map map);
    //兑换/支付/预估佣金金额---兑换次数    结束

    //兑换/支付/预估佣金金额---支付笔数    开始
    List<TrendExhangeAmountThree> getTrendPayTimesInfo(Map map);

    List<TrendExhangeAmountThree> get7TrendPayTimesInfo(Map map);

    List<TrendExhangeAmountThree> get30TrendPayTimesInfo(Map map);

    List<TrendExhangeAmountThree> get12TrendPayTimesInfo(Map map);
    //兑换/支付/预估佣金金额---支付笔数    结束

    //各平台兑换、支付、佣金占比分析    开始
    List<PlatformExgPayRateFour> getPlatformExgPayRateInfoDH(String date);

    List<PlatformExgPayRateFour> getPlatformExgPayRateInfoZF(String date);

    List<PlatformExgPayRateFour> getPlatformExgPayRateInfoYJYJ(String date);

    List<PlatformExgPayRateFour> getPlatformExgPayRateInfoYJ(String date);
    //各平台兑换、支付、佣金占比分析    结束



}
