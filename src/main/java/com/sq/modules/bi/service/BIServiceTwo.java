package com.sq.modules.bi.service;

import com.sq.modules.bi.entity.vo.*;
import com.sq.modules.bi.entity.vo.active.ActiveCardResultVo;
import com.sq.modules.bi.entity.vo.exchange.ExchangeAmountResultVo;
import com.sq.modules.bi.entty2.transaction.ExgPayEstAmount;
import com.sq.modules.bi.entty2.transaction.ExgPayTimes;
import com.sq.modules.bi.entty2.transaction.branch.TrendExhangeAmount;
import com.sq.modules.bi.entty2.user.NumberOfRegisteredUsers;
import com.sq.modules.bi.entty2.user.RegisteredUser;
import com.sq.modules.bi.entty2.user.activation.ActExgPayUser;
import com.sq.modules.bi.entty2.user.platforms.ExchangeNumberOfPlatforms;
import com.sq.modules.bi.entty2.vo.*;

import java.text.ParseException;

/**
 * <p>
 * 雀实省钱BI 服务类
 * </p>
 *
 * @author gl
 * @since 2019-08-08
 */
public interface BIServiceTwo {

    /**
     * 商品兑换排行榜-数量
     */
    ExchangeNumber getExchangeNumber();


    /**
     * 商品兑换排行榜-金额
     */
    ExchangeAmount getCommodityExchangeAmount();



    /**
     *  注册用户数
     */
    NumberOfRegisteredUsers getNumberOfRegisteredUsers();



    /**
     *  各平台兑换、支付趋势分析--兑换人数
     */
    ExchangeNumberOfPlatforms getPlatformExchange();


    /**
     *  各平台兑换、支付趋势分析--支付人数
     */
    ExchangeNumberOfPlatforms getPaymentNumber();


    /**
     *  用户账户余额人数统计
     */
    UserCount getUserAccount();


    /**
     *  用户账户余额为0元趋势分析
     */
    TrendAnalysis getbalanceAnalyze();


    /**
     *  用户关键流程漏斗分析
     */
    UserKeyFlow getUserKeyFlow();


    /**
     *  兑换、支付频次人数分布
     */
    FrequencyAnalyze getFrequencyAnalyze();


    /**
     *  激活/兑换/支付人数
     */
    ActExgPayUser getActExgPayUser();


    /**
     *  兑换/支付次数
     */
    ExgPayTimes getExgPayTimes();



    /**
     *  兑换/支付/预估佣金金额
     */
    ExgPayEstAmount getExgPayEstAmount();


    /**
     *  兑换/支付/预估佣金金额---兑换金额
     */
    TrendExhangeAmount getTrendExhangeAmount();


    /**
     *  兑换/支付/预估佣金金额---支付金额
     */
    TrendExhangeAmount getTrendPayAmount();


    /**
     *  兑换/支付/预估佣金金额---预估佣金
     */
    TrendExhangeAmount getTrendEstAmount();


    /**
     *  兑换/支付/预估佣金金额---兑换次数
     */
    TrendExhangeAmount getTrendExchangTimes();


    /**
     *  兑换/支付/预估佣金金额---支付比数
     */
    TrendExhangeAmount getTrendPayTimes();

    /**
     *  商品支付排行榜-金额
     */
    ExchangeAmount getExchangePayment();

    /**
     *  商品支付排行榜-数量
     */
    ExchangeAmount getExchangePaymentNumber();

    /**
     * 商品结算佣金排行榜-金额
     */
    ExchangeAmountSettlementCommission getSettlementCommission();


    /**
     * 商品结算佣金排行榜-金额
     */
    ExchangeAmountSettlementCommission getSettlementCommissionNumber();

    /**
     * 获取基本信息
     */
    BasicInfoVo getBasicInfo();


    /**
     * 获取基本信息
     */
    PlatformExgPayRate getPlatformExgPayRate();




}
