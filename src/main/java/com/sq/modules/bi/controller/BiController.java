package com.sq.modules.bi.controller;


import com.sq.common.utils.R;
import com.sq.modules.bi.entity.vo.BasicInfoVo;
import com.sq.modules.bi.service.BIService;
import com.sq.modules.bi.service.BIServiceTwo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * <p>
 * 雀实省钱BI 数据统计
 * </p>
 *
 * @author zhongxunan
 * @since 2019-05-12
 */
@RestController
@RequestMapping("/bi")
public class BiController {

    @Autowired
    private BIService biService;
    @Autowired
    private BIServiceTwo biServiceTwo;

    /**
     * 基本信息
     */
    @GetMapping("/basic")
//    @RequiresPermissions("rechargeable:consume:list")
    @ApiOperation(value = "基本信息", notes = "基本信息")
    public R basic() {
        BasicInfoVo basicInfo = biServiceTwo.getBasicInfo();
        return R.ok().put("data", basicInfo);
    }

    /**
     * 生成卡各面值金额占比分析
     */
    @GetMapping("/cardFaceAnalyze")
//    @RequiresPermissions("rechargeable:consume:list")
    @ApiOperation(value = "生成卡各面值金额占比分析", notes = "生成卡各面值金额占比分析")
    public R cardFaceAnalyze() {
        return R.ok().put("data", biService.getCardFaceAnalyze());
    }

    /**
     * 各平台优惠券兑换情况分析
     */
    @GetMapping("/platformAnalyze")
//    @RequiresPermissions("rechargeable:consume:list")
    @ApiOperation(value = "各平台优惠券兑换情况分析", notes = "各平台优惠券兑换情况分析")
    public R platformAnalyze() {
        return R.ok().put("data", biService.getPlatformAnalyze());
    }

    /**
     * 优惠券兑换频次人数分布情况
     */
    @GetMapping("/frequencyAnalyze")
//    @RequiresPermissions("rechargeable:consume:list")
    @ApiOperation(value = "优惠券兑换频次人数分布情况", notes = "优惠券兑换频次人数分布情况")
    public R frequencyAnalyze() {
        return R.ok().put("data", biService.getFrequencyAnalyze());
    }

    /**
     * 优惠券兑换次数商品排行榜
     */
    @GetMapping("/skuAnalyze")
//    @RequiresPermissions("rechargeable:consume:list")
    @ApiOperation(value = "优惠券兑换次数商品排行榜", notes = "优惠券兑换次数商品排行榜")
    public R skuAnalyze() {
        return R.ok().put("data", biService.getSkuAnalyze());
    }

    /**
     * 兑换金额
     */
    @GetMapping("/exchangeAmount")
//    @RequiresPermissions("rechargeable:consume:list")
    @ApiOperation(value = "兑换金额", notes = "兑换金额")
    public R exchangeAmount() {
        return R.ok().put("data", biService.getExchangeAmount());
    }

    /**
     * 激活金额
     */
    @GetMapping("/activeAmount")
//    @RequiresPermissions("rechargeable:consume:list")
    @ApiOperation(value = "激活金额", notes = "激活金额")
    public R activeAmount() {
        return R.ok().put("data", biService.getActiveAmount());
    }

    /**
     *  激活卡数
     */
    @GetMapping("/activeCard")
//    @RequiresPermissions("rechargeable:consume:list")
    @ApiOperation(value = "激活卡数", notes = "激活卡数")
    public R activeCard() {
        return R.ok().put("data", biService.getActiveCard());
    }

    /**
     * 定制卡排行榜
     */
    @GetMapping("/customCardAnalyze")
//    @RequiresPermissions("rechargeable:consume:list")
    @ApiOperation(value = "定制卡排行榜", notes = "定制卡排行榜")
    public R customCardAnalyze() {
        return R.ok().put("data", biService.getCustomCardTop10());
    }


    //#######################################################    BI2.0    author:gl
    /**
     *  商品兑换排行榜-数量
     *
     *  请求URL：/skuTop10/exchangeNumber
     */
    @GetMapping("/skuTop10/exchangeNumber")
    @ApiOperation(value = "商品兑换排行榜-数量", notes = "商品兑换排行榜-数量")
    public R exchangeNumber() {
        return R.ok().put("data", biServiceTwo.getExchangeNumber());
    }


    /**
     *  商品兑换排行榜-金额
     *
     *  请求URL：/skuTop10/amount
     */
    @GetMapping("/skuTop10/amount")
    @ApiOperation(value = "商品兑换排行榜-金额", notes = "商品兑换排行榜-金额")
    public R commodityExchangeAmount() {
        return R.ok().put("data", biServiceTwo.getCommodityExchangeAmount());
    }









    /**
     *  注册用户数
     *
     *  请求URL：/regUser
     */
    @GetMapping("/regUser")
    @ApiOperation(value = "注册用户数", notes = "注册用户数")
    public R getNumberOfRegisteredUsers() {
        return R.ok().put("data", biServiceTwo.getNumberOfRegisteredUsers());
    }



    /**
     * <p>
     * BI2.0 用户-各平台兑换、支付趋势分析-兑换人数
     * </p>
     *
     *  请求URL：/platformExchange
     *
     * @author gl
     * @since 2018-08-12
     */
    @GetMapping("/platformExchange")
    @ApiOperation(value = "用户-各平台兑换、支付趋势分析-兑换人数", notes = "用户-各平台兑换、支付趋势分析-兑换人数")
    public R getPlatformExchange() {
        return R.ok().put("data", biServiceTwo.getPlatformExchange());
    }

    /**
     * <p>
     * BI2.0 用户-各平台兑换、支付趋势分析-支付人数
     * </p>
     *
     *  请求URL：/platformPay
     *
     * @author gl
     * @since 2018-08-13
     */
    @GetMapping("/platformPay")
    @ApiOperation(value = "各平台兑换、支付趋势分析—支付人数", notes = "各平台兑换、支付趋势分析—支付人数")
    public R getPaymentNumber() {
        return R.ok().put("data", biServiceTwo.getPaymentNumber());
    }

    /**
     * <p>
     * BI2.0 用户账户余额人数统计
     * </p>
     *
     *  请求URL：/userAccount
     *
     * @author gl
     * @since 2018-08-13
     */
    @GetMapping("/userAccount")
    @ApiOperation(value = "用户账户余额人数统计", notes = "用户账户余额人数统计")
    public R getUserAccount() {
        return R.ok().put("data", biServiceTwo.getUserAccount());
    }

    /**
     * <p>
     * BI2.0 用户账户余额为0元趋势分析
     * </p>
     *
     *  请求URL：/balanceAnalyze
     *
     * @author gl
     * @since 2018-08-13
     */
    @GetMapping("/balanceAnalyze")
    @ApiOperation(value = "用户账户余额为0元趋势分析", notes = "用户账户余额为0元趋势分析")
    public R getBalanceAnalyze() {
        return R.ok().put("data", biServiceTwo.getbalanceAnalyze());
    }


    /**
     * <p>
     * BI2.0 用户关键流程漏斗分析
     * </p>
     *
     *  请求URL：/userKeyFlow
     *
     * @author gl
     * @since 2018-08-13
     */
    @GetMapping("/userKeyFlow")
    @ApiOperation(value = "用户关键流程漏斗分析", notes = "用户关键流程漏斗分析")
    public R getUserKeyFlow() {
        return R.ok().put("data", biServiceTwo.getUserKeyFlow());
    }

    /**
     * <p>
     * BI2.0 兑换、支付频次人数分布
     * </p>
     *
     *  请求URL：/frequencyAnalyzeTwo
     *
     * @author gl
     * @since 2018-08-13
     */
    @GetMapping("/frequencyAnalyzeTwo")
    @ApiOperation(value = "兑换、支付频次人数分布", notes = "兑换、支付频次人数分布")
    public R getFrequencyAnalyze() {
        return R.ok().put("data", biServiceTwo.getFrequencyAnalyze());
    }


    /**
     * <p>
     * BI2.0 激活/兑换/支付人数
     * </p>
     *
     *  请求URL：/actExgPayUser
     *
     * @author gl
     * @since 2018-08-13
     */
    @GetMapping("/actExgPayUser")
    @ApiOperation(value = "激活/兑换/支付人数", notes = "激活/兑换/支付人数")
    public R getActExgPayUser() {
        return R.ok().put("data", biServiceTwo.getActExgPayUser());
    }



    /**
     * <p>
     * BI2.0 兑换/支付次数
     * </p>
     *
     *  请求URL：/ExgPayTimes
     *
     * @author gl
     * @since 2018-08-13
     */
    @GetMapping("/ExgPayTimes")
    @ApiOperation(value = "兑换/支付次数", notes = "兑换/支付次数")
    public R getExgPayTimes() {
        return R.ok().put("data", biServiceTwo.getExgPayTimes());
    }



    /**
     * <p>
     * BI2.0 兑换/支付/预估佣金金额
     * </p>
     *
     *  请求URL：/exgPayEstAmount
     *
     * @author gl
     * @since 2018-08-14
     */
    @GetMapping("/exgPayEstAmount")
    @ApiOperation(value = "兑换/支付/预估佣金金额", notes = "兑换/支付/预估佣金金额")
    public R getExgPayEstAmount() {
        return R.ok().put("data", biServiceTwo.getExgPayEstAmount());
    }


    /**
     * <p>
     * BI2.0 兑换/支付/预估佣金金额---兑换金额
     * </p>
     *
     *  请求URL：/platformTrend/exhangeAmount
     *
     * @author gl
     * @since 2018-08-14
     */
    @GetMapping("/platformTrend/exhangeAmount")
    @ApiOperation(value = "兑换/支付/预估佣金金额---兑换金额", notes = "兑换/支付/预估佣金金额---兑换金额")
    public R getTrendExhangeAmount() {
        return R.ok().put("data", biServiceTwo.getTrendExhangeAmount());
    }


    /**
     * <p>
     * BI2.0 兑换/支付/预估佣金金额---支付金额
     * </p>
     *
     *  请求URL：/platformTrend/payAmount
     *
     * @author gl
     * @since 2018-08-14
     */
    @GetMapping("/platformTrend/payAmount")
    @ApiOperation(value = "兑换/支付/预估佣金金额---支付金额", notes = "兑换/支付/预估佣金金额---支付金额")
    public R getTrendPayAmount() {
        return R.ok().put("data", biServiceTwo.getTrendPayAmount());
    }


    /**
     * <p>
     * BI2.0 兑换/支付/预估佣金金额---预估佣金
     * </p>
     *
     *  请求URL：/platformTrend/estAmount
     *
     * @author gl
     * @since 2018-08-14
     */
    @GetMapping("/platformTrend/estAmount")
    @ApiOperation(value = "兑换/支付/预估佣金金额---预估佣金", notes = "兑换/支付/预估佣金金额---预估佣金")
    public R getTrendEstAmount() {
        return R.ok().put("data", biServiceTwo.getTrendEstAmount());
    }


    /**
     * <p>
     * BI2.0 兑换/支付/预估佣金金额---兑换次数
     * </p>
     *
     *  请求URL：/platformTrend/exchangTimes
     *
     * @author gl
     * @since 2018-08-14
     */
    @GetMapping("/platformTrend/exchangTimes")
    @ApiOperation(value = "兑换/支付/预估佣金金额---兑换次数", notes = "兑换/支付/预估佣金金额---兑换次数")
    public R getTrendExchangTimes() {
        return R.ok().put("data", biServiceTwo.getTrendExchangTimes());
    }


    /**
     * <p>
     * BI2.0 兑换/支付/预估佣金金额---支付比数
     * </p>
     *
     *  请求URL：/platformTrend/payTimes
     *
     * @author gl
     * @since 2018-08-14
     */
    @GetMapping("/platformTrend/payTimes")
    @ApiOperation(value = "兑换/支付/预估佣金金额---支付比数", notes = "兑换/支付/预估佣金金额---支付比数")
    public R getTrendPayTimes() {
        return R.ok().put("data", biServiceTwo.getTrendPayTimes());
    }


    /**
     *
     *  商品支付排行榜-金额
     *
     *  请求URL：/skuTop10/exchangePayment
     */
    @GetMapping("/skuTop10/exchangePayment")
    @ApiOperation(value = "商品支付排行榜-金额", notes = "商品支付排行榜-金额")
    public R getExchangePayment() {
         return R.ok().put("data", biServiceTwo.getExchangePayment());
    }

    /**
     *
     *  商品支付排行榜-数量
     *
     *  请求URL：/skuTop10/exchangePaymentNumber
     */
    @GetMapping("/skuTop10/exchangePaymentNumber")
    @ApiOperation(value = "商品支付排行榜-数量", notes = "商品支付排行榜-数量")
    public R getExchangePaymentNumber() {
        return R.ok().put("data", biServiceTwo.getExchangePaymentNumber());
    }


    /**
     *
     *  商品结算佣金排行榜-金额
     *
     *  请求URL：/skuTop10/SettlementCommission
     */
    @GetMapping("/skuTop10/SettlementCommission")
    @ApiOperation(value = "商品结算佣金排行榜-金额", notes = "商品结算佣金排行榜-金额")
    public R getSettlementCommission() {
        return R.ok().put("data", biServiceTwo.getSettlementCommission());
    }


    /**
     *
     *  商品结算佣金排行榜-数量
     *
     *  请求URL：/skuTop10/SettlementCommissionNumber
     */
    @GetMapping("/skuTop10/SettlementCommissionNumber")
    @ApiOperation(value = "商品结算佣金排行榜-数量", notes = "商品结算佣金排行榜-数量")
    public R getSettlementCommissionNumber() {
        return R.ok().put("data", biServiceTwo.getSettlementCommissionNumber());
    }



    /**
     *
     *  各平台兑换、支付、佣金占比分析
     *
     *  请求URL：/PlatformExgPayRate
     */
    @GetMapping("/PlatformExgPayRate")
    @ApiOperation(value = "各平台兑换、支付、佣金占比分析", notes = "各平台兑换、支付、佣金占比分析")
    public R getPlatformExgPayRate() {
        return R.ok().put("data", biServiceTwo.getPlatformExgPayRate());
    }



}
