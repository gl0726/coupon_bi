package com.sq.modules.bi.service.impl;

import com.sq.common.utils.DateUtils;
import com.sq.modules.bi.dao.DailyAllMakeCardDao;
import com.sq.modules.bi.entity.*;
import com.sq.modules.bi.entity.vo.BasicInfoVo;
import com.sq.modules.bi.entty2.Ranking.Platform;
import com.sq.modules.bi.entty2.Ranking.Ranking;
import com.sq.modules.bi.entty2.transaction.*;
import com.sq.modules.bi.entty2.transaction.branch.TrendExhangeAmount;
import com.sq.modules.bi.entty2.transaction.branch.TrendExhangeAmountThree;
import com.sq.modules.bi.entty2.transaction.branch.TrendExhangeAmountTwo;
import com.sq.modules.bi.entty2.user.*;
import com.sq.modules.bi.entty2.user.activation.ActExgPayUser;
import com.sq.modules.bi.entty2.user.activation.ActExgPayUserThree;
import com.sq.modules.bi.entty2.user.activation.ActExgPayUserTwo;
import com.sq.modules.bi.entty2.user.platforms.Exchange;
import com.sq.modules.bi.entty2.user.platforms.ExchangeNumberOfPlatforms;
import com.sq.modules.bi.entty2.user.platforms.PlatformsExchangeNumber;
import com.sq.modules.bi.entty2.vo.*;
import com.sq.modules.bi.enums.AmountEnum;
import com.sq.modules.bi.enums.ExchangeAmountEnum;
import com.sq.modules.bi.enums.PlatformEnum;
import com.sq.modules.bi.service.BIServiceTwo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 雀实省钱BI 服务类
 * </p>
 *
 * @author gl * @since 2019-08-08
 */
@Service
public class BiServiceImplTwo implements BIServiceTwo {

    @Autowired
    private DailyAllMakeCardDao dailyAllMakeCardDao;


    /**
     * <p>
     * 商品兑换排行榜-->数量请求服务类
     * </p>
     *
     * @author gl * @since 2019-08-08
     */
    @Override
    public ExchangeNumber getExchangeNumber() {
        ExchangeNumber result = new ExchangeNumber();
        //获取当前日期(格式为：20190808)，为后面dao层做准备
        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);
        Platform all = getExchangeNumberInfo(PlatformEnum.PlatformAll, date);
        Platform gp = getExchangeNumberInfo(PlatformEnum.PlatformGP, date);
        Platform tb = getExchangeNumberInfo(PlatformEnum.PlatformTB, date);
        Platform tm = getExchangeNumberInfo(PlatformEnum.PlatformTM, date);
        Platform jd = getExchangeNumberInfo(PlatformEnum.PlatformJD, date);
        Platform pdd = getExchangeNumberInfo(PlatformEnum.PlatformPDD, date);
        result.setAll(all);
        result.setGp(gp);
        result.setTb(tb);
        result.setTm(tm);
        result.setJd(jd);
        result.setPdd(pdd);
        return result;
    }

    /**
     *  < 商品兑换排行榜数量-获取各平台的昨天，近七天，近30天，累计(累计用的是之前枚举类的LASTMONTH)数据！
     *
     *  开始
     */
    private Platform getExchangeNumberInfo(PlatformEnum type, String date) {

        Platform pf = new Platform();

        if (PlatformEnum.PlatformAll.equals(type)) {
            pf.setLastDay(getALLExchangeQuantity(ExchangeAmountEnum.LASTDay, date));
            pf.setLast7day(getALLExchangeQuantity(ExchangeAmountEnum.LAST7Day, date));
            pf.setLast30day(getALLExchangeQuantity(ExchangeAmountEnum.LAST30Day, date));
            pf.setLastMonth(getALLExchangeQuantity(ExchangeAmountEnum.LASTMONTH, date));
        } else if (PlatformEnum.PlatformGP.equals(type)) {
            pf.setLastDay(getGPExchangeQuantity(ExchangeAmountEnum.LASTDay, date));
            pf.setLast7day(getGPExchangeQuantity(ExchangeAmountEnum.LAST7Day, date));
            pf.setLast30day(getGPExchangeQuantity(ExchangeAmountEnum.LAST30Day, date));
            pf.setLastMonth(getGPExchangeQuantity(ExchangeAmountEnum.LASTMONTH, date));
        } else if (PlatformEnum.PlatformTB.equals(type)) {
            pf.setLastDay(getTBExchangeQuantity(ExchangeAmountEnum.LASTDay, date));
            pf.setLast7day(getTBExchangeQuantity(ExchangeAmountEnum.LAST7Day, date));
            pf.setLast30day(getTBExchangeQuantity(ExchangeAmountEnum.LAST30Day, date));
            pf.setLastMonth(getTBExchangeQuantity(ExchangeAmountEnum.LASTMONTH, date));
        } else if (PlatformEnum.PlatformTM.equals(type)) {
            pf.setLastDay(getTMExchangeQuantity(ExchangeAmountEnum.LASTDay, date));
            pf.setLast7day(getTMExchangeQuantity(ExchangeAmountEnum.LAST7Day, date));
            pf.setLast30day(getTMExchangeQuantity(ExchangeAmountEnum.LAST30Day, date));
            pf.setLastMonth(getTMExchangeQuantity(ExchangeAmountEnum.LASTMONTH, date));
        } else if (PlatformEnum.PlatformJD.equals(type)) {
            pf.setLastDay(getJDExchangeQuantity(ExchangeAmountEnum.LASTDay, date));
            pf.setLast7day(getJDExchangeQuantity(ExchangeAmountEnum.LAST7Day, date));
            pf.setLast30day(getJDExchangeQuantity(ExchangeAmountEnum.LAST30Day, date));
            pf.setLastMonth(getJDExchangeQuantity(ExchangeAmountEnum.LASTMONTH, date));
        } else if (PlatformEnum.PlatformPDD.equals(type)) {
            pf.setLastDay(getPDDExchangeQuantity(ExchangeAmountEnum.LASTDay, date));
            pf.setLast7day(getPDDExchangeQuantity(ExchangeAmountEnum.LAST7Day, date));
            pf.setLast30day(getPDDExchangeQuantity(ExchangeAmountEnum.LAST30Day, date));
            pf.setLastMonth(getPDDExchangeQuantity(ExchangeAmountEnum.LASTMONTH, date));
        }

        return pf;
    }

    private List<Ranking> getALLExchangeQuantity(ExchangeAmountEnum type, String date) {

        List<Ranking> list = null;

        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityAllYesterday(date);
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityAll7Day(date);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityAll30Day(date);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityAllCumulativeDay(date);
        }

        return list;
    }

    private List<Ranking> getGPExchangeQuantity(ExchangeAmountEnum type, String date) {
        List<Ranking> list = null;

        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityGPYesterday(date);
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityGP7Day(date);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityGP30Day(date);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityGPCumulativeDay(date);
        }

        return list;
    }

    private List<Ranking> getTBExchangeQuantity(ExchangeAmountEnum type, String date) {
        List<Ranking> list = null;

        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityTBYesterday(date);
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityTB7Day(date);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityTB30Day(date);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityTBCumulativeDay(date);
        }

        return list;
    }

    private List<Ranking> getTMExchangeQuantity(ExchangeAmountEnum type, String date) {
        List<Ranking> list = null;

        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityTMYesterday(date);
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityTM7Day(date);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityTM30Day(date);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityTMCumulativeDay(date);
        }

        return list;
    }

    private List<Ranking> getJDExchangeQuantity(ExchangeAmountEnum type, String date) {
        List<Ranking> list = null;

        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityJDYesterday(date);
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityJD7Day(date);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityJD30Day(date);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityJDCumulativeDay(date);
        }

        return list;
    }

    private List<Ranking> getPDDExchangeQuantity(ExchangeAmountEnum type, String date) {
        List<Ranking> list = null;

        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityPDDYesterday(date);
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityPDD7Day(date);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityPDD30Day(date);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            list = dailyAllMakeCardDao.getExchangeQuantityPDDCumulativeDay(date);
        }

        return list;
    }

    /**
     *  商品兑换排行榜数量-获取各平台的昨天，近七天，近30天，累计(累计用的是之前枚举类的LASTMONTH)数据！
     *
     *  结束   >
     */


    /**
     *  商品支付排行榜-金额
     *
     *  开始   >
     */
    public ExchangeAmount getExchangePayment() {
        ExchangeAmount result = new ExchangeAmount();

        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);

        Platform all = getExchangePaymentInfo(PlatformEnum.PlatformAll, date);
        Platform gp = getExchangePaymentInfo(PlatformEnum.PlatformGP, date);
        Platform tb = getExchangePaymentInfo(PlatformEnum.PlatformTB, date);
        Platform tm = getExchangePaymentInfo(PlatformEnum.PlatformTM, date);
        Platform jd = getExchangePaymentInfo(PlatformEnum.PlatformJD, date);
        Platform pdd = getExchangePaymentInfo(PlatformEnum.PlatformPDD, date);

        result.setAll(all);
        result.setGp(gp);
        result.setTb(tb);
        result.setTm(tm);
        result.setJd(jd);
        result.setPdd(pdd);

        return result;
    }


    private Platform getExchangePaymentInfo(PlatformEnum type, String date) {

        Platform result = new Platform();

        //昨日
        List<Ranking> lastDay = null;
        //近七天
        List<Ranking> last7day = null;
        //近30天
        List<Ranking> last30day = null;
        //月
        List<Ranking> lastMonth = null;

        //当需要像dao层传输两个参数时，需要用map传输
        Map map = new HashMap<String, String>();
        map.put("date", date);
        //dedu_name平台字段       100全部  0好品购   3淘宝  4天猫   1京东  11拼多多 (查询条件)
        //the_date_name时间字段   1昨日    7近7天     30近30天     100累计  (查询条件)
        if (PlatformEnum.PlatformAll.equals(type)) {
            map.put("dedu_name", 100);
            map.put("the_date_name", 1);
            lastDay = dailyAllMakeCardDao.getExchangePaymentInfo(map);
            map.put("the_date_name", 7);
            last7day = dailyAllMakeCardDao.getExchangePaymentInfo(map);
            map.put("the_date_name", 30);
            last30day = dailyAllMakeCardDao.getExchangePaymentInfo(map);
            map.put("the_date_name", 100);
            lastMonth = dailyAllMakeCardDao.getExchangePaymentInfo(map);
        } else if (PlatformEnum.PlatformGP.equals(type)) {
            map.put("dedu_name", 0);
            map.put("the_date_name", 1);
            lastDay = dailyAllMakeCardDao.getExchangePaymentInfo(map);
            map.put("the_date_name", 7);
            last7day = dailyAllMakeCardDao.getExchangePaymentInfo(map);
            map.put("the_date_name", 30);
            last30day = dailyAllMakeCardDao.getExchangePaymentInfo(map);
            map.put("the_date_name", 100);
            lastMonth = dailyAllMakeCardDao.getExchangePaymentInfo(map);
        } else if (PlatformEnum.PlatformTB.equals(type)) {
            map.put("dedu_name", 3);
            map.put("the_date_name", 1);
            lastDay = dailyAllMakeCardDao.getExchangePaymentInfo(map);
            map.put("the_date_name", 7);
            last7day = dailyAllMakeCardDao.getExchangePaymentInfo(map);
            map.put("the_date_name", 30);
            last30day = dailyAllMakeCardDao.getExchangePaymentInfo(map);
            map.put("the_date_name", 100);
            lastMonth = dailyAllMakeCardDao.getExchangePaymentInfo(map);
        } else if (PlatformEnum.PlatformTM.equals(type)) {
            map.put("dedu_name", 4);
            map.put("the_date_name", 1);
            lastDay = dailyAllMakeCardDao.getExchangePaymentInfo(map);
            map.put("the_date_name", 7);
            last7day = dailyAllMakeCardDao.getExchangePaymentInfo(map);
            map.put("the_date_name", 30);
            last30day = dailyAllMakeCardDao.getExchangePaymentInfo(map);
            map.put("the_date_name", 100);
            lastMonth = dailyAllMakeCardDao.getExchangePaymentInfo(map);
        } else if (PlatformEnum.PlatformJD.equals(type)) {
            map.put("dedu_name", 1);
            map.put("the_date_name", 1);
            lastDay = dailyAllMakeCardDao.getExchangePaymentInfo(map);
            map.put("the_date_name", 7);
            last7day = dailyAllMakeCardDao.getExchangePaymentInfo(map);
            map.put("the_date_name", 30);
            last30day = dailyAllMakeCardDao.getExchangePaymentInfo(map);
            map.put("the_date_name", 100);
            lastMonth = dailyAllMakeCardDao.getExchangePaymentInfo(map);
        } else if (PlatformEnum.PlatformPDD.equals(type)) {
            map.put("dedu_name", 11);
            map.put("the_date_name", 1);
            lastDay = dailyAllMakeCardDao.getExchangePaymentInfo(map);
            map.put("the_date_name", 7);
            last7day = dailyAllMakeCardDao.getExchangePaymentInfo(map);
            map.put("the_date_name", 30);
            last30day = dailyAllMakeCardDao.getExchangePaymentInfo(map);
            map.put("the_date_name", 100);
            lastMonth = dailyAllMakeCardDao.getExchangePaymentInfo(map);
        }

        if(CollectionUtils.isEmpty(lastDay)) {
            lastDay = new ArrayList<Ranking>();
        }
        if(CollectionUtils.isEmpty(last7day)) {
            lastDay = new ArrayList<Ranking>();
        }
        if(CollectionUtils.isEmpty(last30day)) {
            lastDay = new ArrayList<Ranking>();
        }
        if(CollectionUtils.isEmpty(lastMonth)) {
            lastDay = new ArrayList<Ranking>();
        }

        result.setLastDay(lastDay);
        result.setLast7day(last7day);
        result.setLast30day(last30day);
        result.setLastMonth(lastMonth);

        return result;
    }

    /**
     *
     * 商品支付排行榜-金额
     *
     *  结束   >
     */

    /**
     *
     * 商品支付排行榜-数量
     *
     *  开始   >
     */

    public ExchangeAmount getExchangePaymentNumber() {
        ExchangeAmount result = new ExchangeAmount();

        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);

        Platform all = getExchangePaymentNumberInfo(PlatformEnum.PlatformAll, date);
        Platform gp = getExchangePaymentNumberInfo(PlatformEnum.PlatformGP, date);
        Platform tb = getExchangePaymentNumberInfo(PlatformEnum.PlatformTB, date);
        Platform tm = getExchangePaymentNumberInfo(PlatformEnum.PlatformTM, date);
        Platform jd = getExchangePaymentNumberInfo(PlatformEnum.PlatformJD, date);
        Platform pdd = getExchangePaymentNumberInfo(PlatformEnum.PlatformPDD, date);

        result.setAll(all);
        result.setGp(gp);
        result.setTb(tb);
        result.setTm(tm);
        result.setJd(jd);
        result.setPdd(pdd);

        return result;
    }


    private Platform getExchangePaymentNumberInfo(PlatformEnum type, String date) {

        Platform result = new Platform();

        //昨日
        List<Ranking> lastDay = null;
        //近七天
        List<Ranking> last7day = null;
        //近30天
        List<Ranking> last30day = null;
        //月
        List<Ranking> lastMonth = null;

        //当需要像dao层传输两个参数时，需要用map传输
        Map map = new HashMap<String, String>();
        map.put("date", date);
        //dedu平台字段       100全部  0好品购   3淘宝  4天猫   1京东  11拼多多 (查询条件)
        //the_date时间字段   1昨日    7近7天     30近30天     100累计  (查询条件)
        if (PlatformEnum.PlatformAll.equals(type)) {
            map.put("dedu_name", 100);
            map.put("the_date_name", 1);
            lastDay = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
            map.put("the_date_name", 7);
            last7day = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
            map.put("the_date_name", 30);
            last30day = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
            map.put("the_date_name", 100);
            lastMonth = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
        } else if (PlatformEnum.PlatformGP.equals(type)) {
            map.put("dedu_name", 0);
            map.put("the_date_name", 1);
            lastDay = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
            map.put("the_date_name", 7);
            last7day = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
            map.put("the_date_name", 30);
            last30day = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
            map.put("the_date_name", 100);
            lastMonth = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
        } else if (PlatformEnum.PlatformTB.equals(type)) {
            map.put("dedu_name", 3);
            map.put("the_date_name", 1);
            lastDay = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
            map.put("the_date_name", 7);
            last7day = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
            map.put("the_date_name", 30);
            last30day = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
            map.put("the_date_name", 100);
            lastMonth = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
        } else if (PlatformEnum.PlatformTM.equals(type)) {
            map.put("dedu_name", 4);
            map.put("the_date_name", 1);
            lastDay = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
            map.put("the_date_name", 7);
            last7day = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
            map.put("the_date_name", 30);
            last30day = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
            map.put("the_date_name", 100);
            lastMonth = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
        } else if (PlatformEnum.PlatformJD.equals(type)) {
            map.put("dedu_name", 1);
            map.put("the_date_name", 1);
            lastDay = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
            map.put("the_date_name", 7);
            last7day = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
            map.put("the_date_name", 30);
            last30day = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
            map.put("the_date_name", 100);
            lastMonth = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
        } else if (PlatformEnum.PlatformPDD.equals(type)) {
            map.put("dedu_name", 11);
            map.put("the_date_name", 1);
            lastDay = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
            map.put("the_date_name", 7);
            last7day = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
            map.put("the_date_name", 30);
            last30day = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
            map.put("the_date_name", 100);
            lastMonth = dailyAllMakeCardDao.getExchangePaymentNumberInfo(map);
        }

        if(CollectionUtils.isEmpty(lastDay)) {
            lastDay = new ArrayList<Ranking>();
        }
        if(CollectionUtils.isEmpty(last7day)) {
            lastDay = new ArrayList<Ranking>();
        }
        if(CollectionUtils.isEmpty(last30day)) {
            lastDay = new ArrayList<Ranking>();
        }
        if(CollectionUtils.isEmpty(lastMonth)) {
            lastDay = new ArrayList<Ranking>();
        }

        result.setLastDay(lastDay);
        result.setLast7day(last7day);
        result.setLast30day(last30day);
        result.setLastMonth(lastMonth);

        return result;
    }
    /**
     *
     * 商品支付排行榜-数量
     *
     *  结束   >
     */


    /**
     *  商品结算佣金排行榜-金额
     *
     *  开始   >
     */
    public ExchangeAmountSettlementCommission getSettlementCommission() {

        ExchangeAmountSettlementCommission result = new ExchangeAmountSettlementCommission();

        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);

        PlatformTwo all = getSettlementCommissionInfo(PlatformEnum.PlatformAll, date);
        PlatformTwo gp = getSettlementCommissionInfo(PlatformEnum.PlatformGP, date);
        PlatformTwo tb = getSettlementCommissionInfo(PlatformEnum.PlatformTB, date);
        PlatformTwo tm = getSettlementCommissionInfo(PlatformEnum.PlatformTM, date);
        PlatformTwo jd = getSettlementCommissionInfo(PlatformEnum.PlatformJD, date);
        PlatformTwo pdd = getSettlementCommissionInfo(PlatformEnum.PlatformPDD, date);

        result.setAll(all);
        result.setGp(gp);
        result.setTb(tb);
        result.setTm(tm);
        result.setJd(jd);
        result.setPdd(pdd);

        return result;
    }


    private PlatformTwo getSettlementCommissionInfo(PlatformEnum type, String date) {

        PlatformTwo result = new PlatformTwo();

        //排行
        List<Ranking> list = null;

        //当需要像dao层传输两个参数时，需要用map传输
        Map map = new HashMap<String, String>();
        map.put("date", date);
        //dedu_name平台字段       100全部  0好品购   3淘宝  4天猫   1京东  11拼多多 (查询条件)
        if (PlatformEnum.PlatformAll.equals(type)) {
            map.put("dedu_name", 100);
            list = dailyAllMakeCardDao.getSettlementCommissionInfo(map);
        } else if (PlatformEnum.PlatformGP.equals(type)) {
            map.put("dedu_name", 0);
            list = dailyAllMakeCardDao.getSettlementCommissionInfo(map);
        } else if (PlatformEnum.PlatformTB.equals(type)) {
            map.put("dedu_name", 3);
            list = dailyAllMakeCardDao.getSettlementCommissionInfo(map);
        } else if (PlatformEnum.PlatformTM.equals(type)) {
            map.put("dedu_name", 4);
            list = dailyAllMakeCardDao.getSettlementCommissionInfo(map);
        } else if (PlatformEnum.PlatformJD.equals(type)) {
            map.put("dedu_name", 1);
            list = dailyAllMakeCardDao.getSettlementCommissionInfo(map);
        } else if (PlatformEnum.PlatformPDD.equals(type)) {
            map.put("dedu_name", 11);
            list = dailyAllMakeCardDao.getSettlementCommissionInfo(map);
        }

        if(CollectionUtils.isEmpty(list)) {
            list = new ArrayList<Ranking>();
        }

        result.setList(list);

        return result;
    }

    /**
     *
     * 商品结算佣金排行榜-金额
     *
     *  结束   >
     */


    /**
     *  商品结算佣金排行榜-数量
     *
     *  开始   >
     */
    public ExchangeAmountSettlementCommission getSettlementCommissionNumber() {

        ExchangeAmountSettlementCommission result = new ExchangeAmountSettlementCommission();

        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);

        PlatformTwo all = getSettlementCommissionNumberInfo(PlatformEnum.PlatformAll, date);
        PlatformTwo gp = getSettlementCommissionNumberInfo(PlatformEnum.PlatformGP, date);
        PlatformTwo tb = getSettlementCommissionNumberInfo(PlatformEnum.PlatformTB, date);
        PlatformTwo tm = getSettlementCommissionNumberInfo(PlatformEnum.PlatformTM, date);
        PlatformTwo jd = getSettlementCommissionNumberInfo(PlatformEnum.PlatformJD, date);
        PlatformTwo pdd = getSettlementCommissionNumberInfo(PlatformEnum.PlatformPDD, date);

        result.setAll(all);
        result.setGp(gp);
        result.setTb(tb);
        result.setTm(tm);
        result.setJd(jd);
        result.setPdd(pdd);

        return result;
    }


    private PlatformTwo getSettlementCommissionNumberInfo(PlatformEnum type, String date) {

        PlatformTwo result = new PlatformTwo();

        //排行
        List<Ranking> list = null;

        //当需要像dao层传输两个参数时，需要用map传输
        Map map = new HashMap<String, String>();
        map.put("date", date);
        //dedu_name平台字段       100全部  0好品购   3淘宝  4天猫   1京东  11拼多多 (查询条件)
        if (PlatformEnum.PlatformAll.equals(type)) {
            map.put("dedu_name", 100);
            list = dailyAllMakeCardDao.getSettlementCommissionNumberInfo(map);
        } else if (PlatformEnum.PlatformGP.equals(type)) {
            map.put("dedu_name", 0);
            list = dailyAllMakeCardDao.getSettlementCommissionNumberInfo(map);
        } else if (PlatformEnum.PlatformTB.equals(type)) {
            map.put("dedu_name", 3);
            list = dailyAllMakeCardDao.getSettlementCommissionNumberInfo(map);
        } else if (PlatformEnum.PlatformTM.equals(type)) {
            map.put("dedu_name", 4);
            list = dailyAllMakeCardDao.getSettlementCommissionNumberInfo(map);
        } else if (PlatformEnum.PlatformJD.equals(type)) {
            map.put("dedu_name", 1);
            list = dailyAllMakeCardDao.getSettlementCommissionNumberInfo(map);
        } else if (PlatformEnum.PlatformPDD.equals(type)) {
            map.put("dedu_name", 11);
            list = dailyAllMakeCardDao.getSettlementCommissionNumberInfo(map);
        }

        if(CollectionUtils.isEmpty(list)) {
            list = new ArrayList<Ranking>();
        }

        result.setList(list);

        return result;
    }

    /**
     *
     * 商品结算佣金排行榜-数量
     *
     *  结束   >
     */


    /**
     * <p>
     * 商品兑换排行榜-->金额请求服务类
     * </p>
     *
     * @author gl * @since 2019-08-08
     */
    @Override
    public ExchangeAmount getCommodityExchangeAmount() {
        ExchangeAmount result = new ExchangeAmount();
        //获取当前日期(格式为：20190808)，为后面dao层做准备
        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);
        Platform all = getCommodityExchangeAmountInfo(PlatformEnum.PlatformAll, date);
        Platform gp = getCommodityExchangeAmountInfo(PlatformEnum.PlatformGP, date);
        Platform tb = getCommodityExchangeAmountInfo(PlatformEnum.PlatformTB, date);
        Platform tm = getCommodityExchangeAmountInfo(PlatformEnum.PlatformTM, date);
        Platform jd = getCommodityExchangeAmountInfo(PlatformEnum.PlatformJD, date);
        Platform pdd = getCommodityExchangeAmountInfo(PlatformEnum.PlatformPDD, date);
        result.setAll(all);
        result.setGp(gp);
        result.setTb(tb);
        result.setTm(tm);
        result.setJd(jd);
        result.setPdd(pdd);
        return result;
    }

    /**
     *  < 商品兑换排行榜金额-获取各平台的昨天，近七天，近30天，累计(累计用的是之前枚举类的LASTMONTH)数据！
     *
     *  开始
     */
    private Platform getCommodityExchangeAmountInfo(PlatformEnum type, String date) {

        Platform pf = new Platform();

        if (PlatformEnum.PlatformAll.equals(type)) {//CommodityExchangeAmount
            pf.setLastDay(getALLCommodityExchangeAmount(ExchangeAmountEnum.LASTDay, date));
            pf.setLast7day(getALLCommodityExchangeAmount(ExchangeAmountEnum.LAST7Day, date));
            pf.setLast30day(getALLCommodityExchangeAmount(ExchangeAmountEnum.LAST30Day, date));
            pf.setLastMonth(getALLCommodityExchangeAmount(ExchangeAmountEnum.LASTMONTH, date));
        } else if (PlatformEnum.PlatformGP.equals(type)) {
            pf.setLastDay(getGPCommodityExchangeAmount(ExchangeAmountEnum.LASTDay, date));
            pf.setLast7day(getGPCommodityExchangeAmount(ExchangeAmountEnum.LAST7Day, date));
            pf.setLast30day(getGPCommodityExchangeAmount(ExchangeAmountEnum.LAST30Day, date));
            pf.setLastMonth(getGPCommodityExchangeAmount(ExchangeAmountEnum.LASTMONTH, date));
        } else if (PlatformEnum.PlatformTB.equals(type)) {
            pf.setLastDay(getTBCommodityExchangeAmount(ExchangeAmountEnum.LASTDay, date));
            pf.setLast7day(getTBCommodityExchangeAmount(ExchangeAmountEnum.LAST7Day, date));
            pf.setLast30day(getTBCommodityExchangeAmount(ExchangeAmountEnum.LAST30Day, date));
            pf.setLastMonth(getTBCommodityExchangeAmount(ExchangeAmountEnum.LASTMONTH, date));
        } else if (PlatformEnum.PlatformTM.equals(type)) {
            pf.setLastDay(getTMCommodityExchangeAmount(ExchangeAmountEnum.LASTDay, date));
            pf.setLast7day(getTMCommodityExchangeAmount(ExchangeAmountEnum.LAST7Day, date));
            pf.setLast30day(getTMCommodityExchangeAmount(ExchangeAmountEnum.LAST30Day, date));
            pf.setLastMonth(getTMCommodityExchangeAmount(ExchangeAmountEnum.LASTMONTH, date));
        } else if (PlatformEnum.PlatformJD.equals(type)) {
            pf.setLastDay(getJDCommodityExchangeAmount(ExchangeAmountEnum.LASTDay, date));
            pf.setLast7day(getJDCommodityExchangeAmount(ExchangeAmountEnum.LAST7Day, date));
            pf.setLast30day(getJDCommodityExchangeAmount(ExchangeAmountEnum.LAST30Day, date));
            pf.setLastMonth(getJDCommodityExchangeAmount(ExchangeAmountEnum.LASTMONTH, date));
        } else if (PlatformEnum.PlatformPDD.equals(type)) {
            pf.setLastDay(getPDDCommodityExchangeAmount(ExchangeAmountEnum.LASTDay, date));
            pf.setLast7day(getPDDCommodityExchangeAmount(ExchangeAmountEnum.LAST7Day, date));
            pf.setLast30day(getPDDCommodityExchangeAmount(ExchangeAmountEnum.LAST30Day, date));
            pf.setLastMonth(getPDDCommodityExchangeAmount(ExchangeAmountEnum.LASTMONTH, date));
        }

        return pf;
    }


    private List<Ranking> getALLCommodityExchangeAmount(ExchangeAmountEnum type, String date) {

        List<Ranking> list = null;

        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountAllYesterday(date);
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountAll7Day(date);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountAll30Day(date);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountAllCumulativeDay(date);
        }

        return list;
    }

    private List<Ranking> getGPCommodityExchangeAmount(ExchangeAmountEnum type, String date) {
        List<Ranking> list = null;

        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountGPYesterday(date);
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountGP7Day(date);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountGP30Day(date);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountGPCumulativeDay(date);
        }

        return list;
    }

    private List<Ranking> getTBCommodityExchangeAmount(ExchangeAmountEnum type, String date) {
        List<Ranking> list = null;

        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountTBYesterday(date);
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountTB7Day(date);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountTB30Day(date);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountTBCumulativeDay(date);
        }

        return list;
    }

    private List<Ranking> getTMCommodityExchangeAmount(ExchangeAmountEnum type, String date) {
        List<Ranking> list = null;

        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountTMYesterday(date);
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountTM7Day(date);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountTM30Day(date);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountTMCumulativeDay(date);
        }

        return list;
    }

    private List<Ranking> getJDCommodityExchangeAmount(ExchangeAmountEnum type, String date) {
        List<Ranking> list = null;

        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountJDYesterday(date);
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountJD7Day(date);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountJD30Day(date);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountJDCumulativeDay(date);
        }

        return list;
    }

    private List<Ranking> getPDDCommodityExchangeAmount(ExchangeAmountEnum type, String date) {
        List<Ranking> list = null;

        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountPDDYesterday(date);
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountPDD7Day(date);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountPDD30Day(date);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            list = dailyAllMakeCardDao.getCommodityExchangeAmountPDDCumulativeDay(date);
        }

        return list;
    }

    /**
     *  商品兑换排行榜金额-获取各平台的昨天，近七天，近30天，累计(累计用的是之前枚举类的LASTMONTH)数据！
     *
     *  结束   >
     */



    /**
     *  注册用户数！ 逻辑稍微复杂一点
     *
     *  开始   >
     */

    @Override
    public NumberOfRegisteredUsers getNumberOfRegisteredUsers() {

        NumberOfRegisteredUsers result = new NumberOfRegisteredUsers();

        RegisteredUser lastDay = getActiveNumUserInfo(ExchangeAmountEnum.LASTDay);
        lastDay.getCurrentRound();
        lastDay.getLastRound();
        //昨日和上一日单个小时的平均数
        List<ActiveUserTwo> average = new ArrayList<ActiveUserTwo>();
        for(int i = 0; i <= 23; i++) {
            ActiveUserTwo au = new ActiveUserTwo();
            au.setTheTime(lastDay.getCurrentRound().get(i).getTheTime());
            double avg = (lastDay.getCurrentRound().get(i).getTotalCount() + lastDay.getLastRound().get(i).getTotalCount())/2.0;
            au.setAvg(avg);
            average.add(au);
        }
        RegisteredUser last7Day = getActiveNumUserInfo(ExchangeAmountEnum.LAST7Day);
        RegisteredUser last30Day = getActiveNumUserInfo(ExchangeAmountEnum.LAST30Day);
        RegisteredUser lastMonth = getActiveNumUserInfo(ExchangeAmountEnum.LASTMONTH);

        result.setLastDay(lastDay);
        result.setAvg(average);
        result.setLast7day(last7Day);
        result.setLast30day(last30Day);
        result.setLastMonth(lastMonth);

        return result;
    }

    private RegisteredUser getActiveNumUserInfo(ExchangeAmountEnum type) {

        RegisteredUser lastDay = new RegisteredUser();

        Date today = new Date();

        List<ActiveUser> currentRound = null;
        List<ActiveUser> lastRound = null;  //lastRound变量只有昨日需求和月的临时需求才需要给值
        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            currentRound = dailyAllMakeCardDao.getActiveUsersNum(DateUtils.format(DateUtils.addDateDays(today, -1), DateUtils.DATETIMEPATTERN));
            lastRound = dailyAllMakeCardDao.getActiveUsersNum(DateUtils.format(DateUtils.addDateDays(today, -2), DateUtils.DATETIMEPATTERN));
            currentRound = dealHourActiveUser(currentRound);
            lastRound = dealHourActiveUser(lastRound);
        } else if (ExchangeAmountEnum.LAST7Day.equals(type))
            currentRound = dailyAllMakeCardDao.get7ActiveUsersNum(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
        else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            currentRound = dailyAllMakeCardDao.get30ActiveUsersNum(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            //月度统计完全 用不上create_time，只需要统计the_mouth即可，因为过去月份的总数是固定的
            //下面两条sql主要求的是上个月的总数，和上上个月的注册人数总数
            currentRound = dailyAllMakeCardDao.getMonthActiveUsersNum(DateUtils.formatMonth(DateUtils.addDateMonths(today, -1)));
            lastRound = dailyAllMakeCardDao.getMonthActiveUsersNum(DateUtils.formatMonth(DateUtils.addDateMonths(today, -2)));
        }
        if (CollectionUtils.isEmpty(currentRound)) {
            currentRound = new ArrayList<ActiveUser>();
        }
        if (CollectionUtils.isEmpty(lastRound)) {
            lastRound = new ArrayList<ActiveUser>();
        }
        lastDay.setCurrentRound(currentRound);
        lastDay.setLastRound(lastRound);


        //第二部，将数据添加至临时表
        ActiveSum exchangeCardTotal = null;
        ActiveSum exchangeCardTotalLast = null;
        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            exchangeCardTotal = dailyAllMakeCardDao.getActiveUsersTotalNum(DateUtils.format(DateUtils.addDateDays(today, -1), DateUtils.DATETIMEPATTERN));
            exchangeCardTotalLast = dailyAllMakeCardDao.getActiveUsersTotalNum(DateUtils.format(DateUtils.addDateDays(today, -2), DateUtils.DATETIMEPATTERN));
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            exchangeCardTotal = dailyAllMakeCardDao.get7ActiveUsersTotalNum(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
            exchangeCardTotalLast = dailyAllMakeCardDao.get7ActiveUsersTotalNum(DateUtils.format(DateUtils.addDateDays(today, -7), DateUtils.DATETIMEPATTERN));
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            exchangeCardTotal = dailyAllMakeCardDao.get30ActiveUsersTotalNum(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
            exchangeCardTotalLast = dailyAllMakeCardDao.get30ActiveUsersTotalNum(DateUtils.format(DateUtils.addDateDays(today, -30), DateUtils.DATETIMEPATTERN));
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            //下面是求12个月的平均值，exchangeCardTotalLast只有avgAmount有值
            exchangeCardTotalLast = dailyAllMakeCardDao.get12MonthActiveUsersAvgNum(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
        }

        if (exchangeCardTotal == null) {
            exchangeCardTotal = new ActiveSum();
        }
        if (exchangeCardTotalLast == null) {
            exchangeCardTotalLast = new ActiveSum();
        }
        if (ExchangeAmountEnum.LASTMONTH.equals(type)) { //月度的统计和之前的统计不同，所以分开写
            if (CollectionUtils.isNotEmpty(currentRound)) {
                lastDay.setActivieTotal(currentRound.get(0).getTotalCount());  // 本月总数
            }
            if (CollectionUtils.isNotEmpty(lastRound)) {
                lastDay.setLastActivieTotal(lastRound.get(0).getTotalCount());  // 上月总数
            }
            //平均值
            lastDay.setAvg(exchangeCardTotalLast.getAvgAmount());
            //开始结束时间
            lastDay.setDateBeginEnd(DateUtils.formatMonth(DateUtils.addDateMonths(new Date(), -1))
                    + "--"+ DateUtils.formatMonth(DateUtils.addDateMonths(new Date(), -12)));

            //此方法生成百分比，并且给lastDay完成赋值
            dealRat(lastDay);

            Map map = getMonth();
            List<ActiveUser> monthActiveCardNum = dailyAllMakeCardDao.get12MonthActiveUsersNum(map);
            lastDay.setCurrentRound(monthActiveCardNum);

        } else {
            //除月度之外的统计
            lastDay.setActivieTotal(exchangeCardTotal.getActivieTotal());
            lastDay.setAvg(exchangeCardTotal.getAvgAmount());
            lastDay.setDateBeginEnd(exchangeCardTotal.getCreateTime());
            lastDay.setLastActivieTotal(exchangeCardTotalLast.getActivieTotal());
            dealRat(lastDay);
        }
        if(lastDay.getActivieTotal() == null){
            lastDay.setActivieTotal(0L);
        }
        if(lastDay.getLastActivieTotal() == null){
            lastDay.setLastActivieTotal(0L);
        }
        return lastDay;
    }

    /**
     * 设置 近一年的开始结束月份
     *
     */

    /**
     *  注册用户数！ 逻辑稍微复杂一点
     *
     *  结束   >
     */


    /**
     * 公共代码：填充小时前面的0
     */
    private List<ActiveUser>  dealHourActiveUser(List<ActiveUser> ecs){
        List<ActiveUser> result = new ArrayList<ActiveUser>(24);
        Map<String, Long> hourMap = new TreeMap();
        String hkey = null;
        for(int i=0;i < 24;i++) {
            if(i < 10){
                hkey = "0"+i+":00";
            }else {
                hkey = i+":00";
            }
            hourMap.put(hkey,0L);
        }
        for(ActiveUser ec :ecs){
            if(ec.getTheTime().length() == 4){  // 统一时间格式
                hourMap.put("0"+ec.getTheTime(),ec.getTotalCount());
            }else {
                hourMap.put(ec.getTheTime(),ec.getTotalCount());
            }
        }
        for(String key : hourMap.keySet() ){
            ActiveUser bean = new ActiveUser();
            bean.setTheTime(key);
            bean.setTotalCount(hourMap.get(key));
            result.add(bean);
        }
        return result;
    }

    /**
     * 公共代码：处理环比值
     *
     */
    private void dealRat(RegisteredUser lastDay) {
        if (lastDay.getActivieTotal() != null) {
            if (lastDay.getLastActivieTotal() != null) {
                Double rat = getaDoubleFormat(new Double(lastDay.getActivieTotal() - lastDay.getLastActivieTotal()) / lastDay.getActivieTotal());
                lastDay.setRat(getRate(rat));
            } else {
                lastDay.setRat("100%");
            }
        } else {
            lastDay.setRat("0%");
        }
    }

    /**
     * 公共代码：数字转百分率 0.56966 --》 56.97%
     *
     * @return
     */
    private String getRate(Double num){
        NumberFormat numberFormat  = NumberFormat.getPercentInstance();
        numberFormat.setMinimumFractionDigits(2); //保留到小数点后2位,不设置或者设置为0表示不保留小数
        return numberFormat.format(num);
    }

    /**
     * 公共代码：获取4位小数（四舍五入）
     *
     */
    private Double getaDoubleFormat(Double rate) {
        BigDecimal b = new BigDecimal(rate);
        return b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 公共代码：设置 近一年的开始结束月份
     *
     */
    private Map getMonth() {
        Date date = new Date();
        Map map = new HashMap<String, String>();
        map.put("beginMonth", DateUtils.formatMonth(DateUtils.addDateMonths(date, -12)));
        map.put("endMonth", DateUtils.formatMonth(DateUtils.addDateMonths(date, -1)));
        return map;
    }


    /**
     *  各平台兑换、支付趋势分析--兑换人数
     *
     *  --> 开始
     */
    public ExchangeNumberOfPlatforms getPlatformExchange() {

        ExchangeNumberOfPlatforms result = new ExchangeNumberOfPlatforms();

        PlatformsExchangeNumber lastDay = getPlatformNumber(ExchangeAmountEnum.LASTDay);
        PlatformsExchangeNumber last7Day = getPlatformNumber(ExchangeAmountEnum.LAST7Day);
        PlatformsExchangeNumber last30Day = getPlatformNumber(ExchangeAmountEnum.LAST30Day);
        PlatformsExchangeNumber lastMonth = getPlatformNumber(ExchangeAmountEnum.LASTMONTH);

        result.setLastDay(lastDay);
        result.setLast7day(last7Day);
        result.setLast30day(last30Day);
        result.setLastMonth(lastMonth);

        return result;
    }

    private List<Exchange> dealHourFour(List<Exchange> ecs) {
        List<Exchange> result = new ArrayList<Exchange>(24);
        Map<String, Integer> hourMap = new TreeMap();
        String hkey = null;
        for(int i=0;i < 24;i++) {
            if(i < 10){
                hkey = "0"+i+":00";
            }else {
                hkey = i+":00";
            }
            hourMap.put(hkey,0);
        }
        for(Exchange ec :ecs){
            if(ec.getTheTime().length() == 4){  // 统一时间格式
                hourMap.put("0"+ec.getTheTime(),ec.getTotalCount());
            }else {
                hourMap.put(ec.getTheTime(),ec.getTotalCount());
            }
        }
        for(String key : hourMap.keySet() ){
            Exchange bean = new Exchange();
            bean.setTheTime(key);
            bean.setTotalCount(hourMap.get(key));
            result.add(bean);

        }
        return result;
    }


    public List<Exchange> getDayActExgPayUserInfoTwo(List<Exchange> ecs, ExchangeAmountEnum type) {

        List<Exchange> result = new ArrayList<Exchange>();
        //TreeMap能够把它保存的记录根据键排序  相对于hashMap的无序，TreeMap更能让我们十分方便使用
        Map<String, Integer> hourMap = new TreeMap();

        if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            for(int i = -7; i <= -1; i++) {
                hourMap.put(DateUtils.format(DateUtils.addDateDays(new Date(), i), DateUtils.DATE_PATTERN), 0);
            }
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            for(int i = -30; i <= -1; i++) {
                hourMap.put(DateUtils.format(DateUtils.addDateDays(new Date(), i), DateUtils.DATE_PATTERN), 0);
            }
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            for (int i = -1; i >= -12; i--) {
                hourMap.put(DateUtils.formatMonth(DateUtils.addDateMonths(new Date(), i)), 0);
                if(DateUtils.formatMonth(DateUtils.addDateMonths(new Date(), i)).equals("2019-08"))
                    break;
            }
        }

        for(Exchange ec :ecs){
            hourMap.put(ec.getTheTime(), ec.getTotalCount());
        }

        for(String key : hourMap.keySet() ){
            Exchange bean = new Exchange();
            bean.setTheTime(key);
            bean.setTotalCount(hourMap.get(key));
            result.add(bean);
        }

        return result;
    }



    private PlatformsExchangeNumber getPlatformNumber(ExchangeAmountEnum type) {

        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);

        PlatformsExchangeNumber result = new PlatformsExchangeNumber();

        //好品购集合
        List<Exchange> selfRound = null;
        //天猫集合
        List<Exchange> tmRound = null;
        //淘宝集合
        List<Exchange> tbRound = null;
        //京东集合
        List<Exchange> jdRound = null;
        //拼多多集合
        List<Exchange> pddRound = null;

        //当需要像dao层传输两个参数时，需要用map传输
        Map map = new HashMap<String, String>();
        map.put("date", date);
        //各平台标识ID
        // [{cat_id:0,title:'好品购',},{cat_id:1,title:'京东',},{cat_id:3,title:'淘宝',},{cat_id:11,title:'拼多多',},{cat_id:4,title:'天猫',}]
        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            map.put("deduction", "0");
            selfRound = dealHourFour(dailyAllMakeCardDao.getPlatformNumber(map));
            map.put("deduction", "4");
            tmRound = dealHourFour(dailyAllMakeCardDao.getPlatformNumber(map));
            map.put("deduction", "3");
            tbRound = dealHourFour(dailyAllMakeCardDao.getPlatformNumber(map));
            map.put("deduction", "1");
            jdRound = dealHourFour(dailyAllMakeCardDao.getPlatformNumber(map));
            map.put("deduction", "11");
            pddRound = dealHourFour(dailyAllMakeCardDao.getPlatformNumber(map));
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            map.put("deduction", "0");
            selfRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get7PlatformNumber(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "4");
            tmRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get7PlatformNumber(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "3");
            tbRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get7PlatformNumber(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "1");
            jdRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get7PlatformNumber(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "11");
            pddRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get7PlatformNumber(map), ExchangeAmountEnum.LAST7Day);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            map.put("deduction", "0");
            selfRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get30PlatformNumber(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "4");
            tmRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get30PlatformNumber(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "3");
            tbRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get30PlatformNumber(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "1");
            jdRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get30PlatformNumber(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "11");
            pddRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get30PlatformNumber(map), ExchangeAmountEnum.LAST30Day);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            Date date1 = new Date();
            map.put("deduction", "0");
            map.put("beginMonth", DateUtils.formatMonth(DateUtils.addDateMonths(date1, -12)));
            map.put("endMonth", DateUtils.formatMonth(DateUtils.addDateMonths(date1, -1)));
            selfRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get12PlatformNumber(map), ExchangeAmountEnum.LASTMONTH);
            map.put("deduction", "4");
            tmRound = dailyAllMakeCardDao.get12PlatformNumber(map);
            map.put("deduction", "3");
            tbRound = dailyAllMakeCardDao.get12PlatformNumber(map);
            map.put("deduction", "1");
            jdRound = dailyAllMakeCardDao.get12PlatformNumber(map);
            map.put("deduction", "11");
            pddRound = dailyAllMakeCardDao.get12PlatformNumber(map);
        }

        if(CollectionUtils.isEmpty(selfRound)) {
            selfRound = new ArrayList<Exchange>();
        }
        if(CollectionUtils.isEmpty(tmRound)) {
            tmRound = new ArrayList<Exchange>();
        }
        if(CollectionUtils.isEmpty(tbRound)) {
            tbRound = new ArrayList<Exchange>();
        }
        if(CollectionUtils.isEmpty(jdRound)) {
            jdRound = new ArrayList<Exchange>();
        }
        if(CollectionUtils.isEmpty(pddRound)) {
            pddRound = new ArrayList<Exchange>();
        }

        result.setSelfRound(selfRound);
        result.setTmRound(tmRound);
        result.setTbRound(tbRound);
        result.setJdRound(jdRound);
        result.setPddRound(pddRound);

        result.setSelf(Summary(selfRound));
        result.setTm(Summary(tmRound));
        result.setTb(Summary(tbRound));
        result.setJd(Summary(jdRound));
        result.setPdd(Summary(pddRound));

        return result;
    }

    //统计总兑换人数
    private int Summary(List<Exchange> selfRound) {
        int sum = 0;
        for (Exchange e:selfRound) {
            sum += e.getTotalCount();
        }
        return sum;
    }
    /**
     *  各平台兑换、支付趋势分析--兑换人数
     *
     *  --> 结束
     */




    /**
     *  各平台兑换、支付趋势分析--支付人数
     *
     *  --> 开始
     */
    public ExchangeNumberOfPlatforms getPaymentNumber() {

        ExchangeNumberOfPlatforms result = new ExchangeNumberOfPlatforms();

        PlatformsExchangeNumber lastDay = getPaymentNumberInfo(ExchangeAmountEnum.LASTDay);
        PlatformsExchangeNumber last7Day = getPaymentNumberInfo(ExchangeAmountEnum.LAST7Day);
        PlatformsExchangeNumber last30Day = getPaymentNumberInfo(ExchangeAmountEnum.LAST30Day);
        PlatformsExchangeNumber lastMonth = getPaymentNumberInfo(ExchangeAmountEnum.LASTMONTH);

        result.setLastDay(lastDay);
        result.setLast7day(last7Day);
        result.setLast30day(last30Day);
        result.setLastMonth(lastMonth);

        return result;
    }


    private PlatformsExchangeNumber getPaymentNumberInfo(ExchangeAmountEnum type) {

        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);

        PlatformsExchangeNumber result = new PlatformsExchangeNumber();

        //好品购集合
        List<Exchange> selfRound = null;
        //天猫集合
        List<Exchange> tmRound = null;
        //淘宝集合
        List<Exchange> tbRound = null;
        //京东集合
        List<Exchange> jdRound = null;
        //拼多多集合
        List<Exchange> pddRound = null;

        //当需要像dao层传输两个参数时，需要用map传输
        Map map = new HashMap<String, String>();
        map.put("date", date);
        //各平台标识ID
        // [{cat_id:0,title:'好品购',},{cat_id:1,title:'京东',},{cat_id:3,title:'淘宝',},{cat_id:11,title:'拼多多',},{cat_id:4,title:'天猫',}]
        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            map.put("deduction", "0");
            selfRound = dealHourFour(dailyAllMakeCardDao.getPaymentNumber(map));
            map.put("deduction", "4");
            tmRound = dealHourFour(dailyAllMakeCardDao.getPaymentNumber(map));
            map.put("deduction", "3");
            tbRound = dealHourFour(dailyAllMakeCardDao.getPaymentNumber(map));
            map.put("deduction", "1");
            jdRound = dealHourFour(dailyAllMakeCardDao.getPaymentNumber(map));
            map.put("deduction", "11");
            pddRound = dealHourFour(dailyAllMakeCardDao.getPaymentNumber(map));
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            map.put("deduction", "0");
            selfRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get7PaymentNumber(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "4");
            tmRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get7PaymentNumber(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "3");
            tbRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get7PaymentNumber(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "1");
            jdRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get7PaymentNumber(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "11");
            pddRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get7PaymentNumber(map), ExchangeAmountEnum.LAST7Day);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            map.put("deduction", "0");
            selfRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get30PaymentNumber(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "4");
            tmRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get30PaymentNumber(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "3");
            tbRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get30PaymentNumber(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "1");
            jdRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get30PaymentNumber(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "11");
            pddRound = getDayActExgPayUserInfoTwo(dailyAllMakeCardDao.get30PaymentNumber(map), ExchangeAmountEnum.LAST30Day);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            Date date1 = new Date();
            map.put("deduction", "0");
            map.put("beginMonth", DateUtils.formatMonth(DateUtils.addDateMonths(date1, -12)));
            map.put("endMonth", DateUtils.formatMonth(DateUtils.addDateMonths(date1, -1)));
            selfRound = dailyAllMakeCardDao.get12PaymentNumber(map);
            map.put("deduction", "4");
            tmRound = dailyAllMakeCardDao.get12PaymentNumber(map);
            map.put("deduction", "3");
            tbRound = dailyAllMakeCardDao.get12PaymentNumber(map);
            map.put("deduction", "1");
            jdRound = dailyAllMakeCardDao.get12PaymentNumber(map);
            map.put("deduction", "11");
            pddRound = dailyAllMakeCardDao.get12PaymentNumber(map);
        }

        if(CollectionUtils.isEmpty(selfRound)) {
            selfRound = new ArrayList<Exchange>();
        }
        if(CollectionUtils.isEmpty(tmRound)) {
            tmRound = new ArrayList<Exchange>();
        }
        if(CollectionUtils.isEmpty(tbRound)) {
            tbRound = new ArrayList<Exchange>();
        }
        if(CollectionUtils.isEmpty(jdRound)) {
            jdRound = new ArrayList<Exchange>();
        }
        if(CollectionUtils.isEmpty(pddRound)) {
            pddRound = new ArrayList<Exchange>();
        }

        result.setSelfRound(selfRound);
        result.setTmRound(tmRound);
        result.setTbRound(tbRound);
        result.setJdRound(jdRound);
        result.setPddRound(pddRound);

        result.setSelf(Summary(selfRound));
        result.setTm(Summary(tmRound));
        result.setTb(Summary(tbRound));
        result.setJd(Summary(jdRound));
        result.setPdd(Summary(pddRound));

        return result;
    }

    /**
     *  各平台兑换、支付趋势分析--支付人数
     *
     *  --> 结束
     */


    /**
     *  用户账户余额人数统计
     *
     *  --> 开始
     */

    public UserCount getUserAccount() {

        UserCount result = new UserCount();

        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);

        List<UserCountTwo> list = dailyAllMakeCardDao.getUserAccount(date);

        result.setBalance0(list.get(0).getTotal());
        result.setBalance50(list.get(1).getTotal());
        result.setBalance100(list.get(2).getTotal());
        result.setBalance200(list.get(3).getTotal());
        result.setBalance300(list.get(4).getTotal());

        return result;
    }

    /**
     *  用户账户余额人数统计
     *
     *  --> 结束
     */


    /**
     *  用户账户余额为0元趋势分析
     *
     *  --> 开始
     */
    public TrendAnalysis getbalanceAnalyze() {

        TrendAnalysis result = new TrendAnalysis();

        Map<String, String> map = new HashMap<String, String>();

        map.put("endDate", DateUtils.format(DateUtils.addDateDays(new Date(), 0), DateUtils.DATETIMEPATTERN));
        map.put("startDate", DateUtils.format(DateUtils.addDateDays(new Date(), -30), DateUtils.DATETIMEPATTERN));

        //时间类
        Calendar theCa = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();

        //开始时间
        theCa.setTime(currentDate);
        theCa.add(theCa.DATE, -1);//最后一个数字30可改，30天的意思
        Date end = theCa.getTime();
        String endDate = sdf.format(end);

        //结束时间
        theCa.setTime(currentDate);
        theCa.add(theCa.DATE, -31);//最后一个数字30可改，30天的意思
        Date start = theCa.getTime();
        String startDate = sdf.format(start);

        result.setBeginDate(startDate);
        result.setEndDate(endDate);

        //有些数据不足30天，需要添加日期和0数据
        List<TrendAnalysisTwo> balance = dailyAllMakeCardDao.getbalanceAnalyze(map);

        Map<String, Integer> hourMap = new TreeMap();
        //这里比较麻烦，因为数据是t+1的格式，假设数据库表展现的是9月4号的数据，实际是9月3号的
        //此处循环是将30天获得数据往前移动一日
        for(int i = -30; i <= 0; i++) {
            hourMap.put(DateUtils.format(DateUtils.addDateDays(new Date(), i), DateUtils.DATETIMEPATTERN), 0);
        }

        for(TrendAnalysisTwo ec :balance){
            hourMap.put(ec.getCreateTime(), ec.getTotal());
        }

        List<TrendAnalysisTwo> list = new ArrayList<TrendAnalysisTwo>();

        SimpleDateFormat sdf2 = new SimpleDateFormat(DateUtils.DATETIMEPATTERN);
        SimpleDateFormat sdf3 = new SimpleDateFormat(DateUtils.DATE_PATTERN_MONTH_DAY);

        try {
            for(String key : hourMap.keySet()){
                TrendAnalysisTwo bean = new TrendAnalysisTwo();
                bean.setTotal(hourMap.get(key));
                //将日期往前递增一天
                Date parse = sdf2.parse(key);
                Date date = DateUtils.addDateDays(parse, -1);
                String format = sdf3.format(date);
                bean.setDay(format);
                list.add(bean);
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }

        result.setBalance(list);

        return result;
    }
    /**
     *  用户账户余额为0元趋势分析
     *
     *  --> 结束
     */


    /**
     *  用户关键流程漏斗分析
     *
     *  --> 开始
     */
    public UserKeyFlow getUserKeyFlow() {

        UserKeyFlow result = new UserKeyFlow();

        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);

        UserKeyFlowTwo ukfw = dailyAllMakeCardDao.getUserKeyFlow(date);

        if(ukfw == null) {
            ukfw = new UserKeyFlowTwo();
        }

        //赋值人数
        result.setRegUser(ukfw.getRegUser());
        result.setActiveUser(ukfw.getActiveUser());
        result.setExchangeUser(ukfw.getExchangeUser());
        result.setPaymentUser(ukfw.getPaymentUser());

        //赋值百分比
        result.setTotalRate(getPercentage(result.getPaymentUser(), result.getRegUser()));
        result.setActiveRate(getPercentage(result.getActiveUser(), result.getRegUser()));
        result.setExchangeRate(getPercentage(result.getExchangeUser(), result.getActiveUser()));
        result.setPaymentRate(getPercentage(result.getPaymentUser(), result.getExchangeUser()));

        //时间类
        Calendar theCa = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();

        //开始时间
        theCa.setTime(currentDate);
        theCa.add(theCa.DATE, -1);//最后一个数字30可改，30天的意思
        Date end = theCa.getTime();
        String endDate = sdf.format(end);

        //结束时间
        theCa.setTime(currentDate);
        theCa.add(theCa.DATE, -31);//最后一个数字30可改，30天的意思
        Date start = theCa.getTime();
        String startDate = sdf.format(start);

        //赋值时间
        result.setBeginDate(startDate);
        result.setEndDate(endDate);

        return result;
    }


    //获取计算百分比例如：80%  100%
    private String getPercentage(int dividend, int divisor) {
        double merchant = new Double(dividend)/divisor;
        NumberFormat numberFormat  = NumberFormat.getPercentInstance();
        numberFormat.setMinimumFractionDigits(2); //保留到小数点后2位,不设置或者设置为0表示不保留小数
        return numberFormat.format(merchant);
    }


    /**
     *  用户关键流程漏斗分析
     *
     *  --> 结束
     */


    /**
     *  兑换、支付频次人数分布
     *
     *  --> 开始
     */
    public FrequencyAnalyze getFrequencyAnalyze() {

        FrequencyAnalyze result = new FrequencyAnalyze();

        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);

        result.setPay(dailyAllMakeCardDao.getFrequencyAnalyzePay(date));

        result.setExchange(dailyAllMakeCardDao.getFrequencyAnalyzeExchange(date));

        return result;
    }

    /**
     *  兑换、支付频次人数分布
     *
     *  --> 结束
     */



    /**
     *  激活/兑换/支付人数
     *
     *  --> 开始
     */
    public ActExgPayUser getActExgPayUser() {

        ActExgPayUser result = new ActExgPayUser();

        ActExgPayUserTwo lastDay = getActExgPayUserInfo(ExchangeAmountEnum.LASTDay);
        ActExgPayUserTwo last7Day = getActExgPayUserInfo(ExchangeAmountEnum.LAST7Day);
        ActExgPayUserTwo last30Day = getActExgPayUserInfo(ExchangeAmountEnum.LAST30Day);
        ActExgPayUserTwo lastMonth = getActExgPayUserInfo(ExchangeAmountEnum.LASTMONTH);

        result.setLastDay(lastDay);
        result.setLast7day(last7Day);
        result.setLast30day(last30Day);
        result.setLastMonth(lastMonth);

        return result;
    }

    private List<ActExgPayUserThree> dealHourThree(List<ActExgPayUserThree> ecs){
        List<ActExgPayUserThree> result = new ArrayList<ActExgPayUserThree>(24);
        Map<String, Integer> hourMap = new TreeMap();
        String hkey = null;
        for(int i=0;i < 24;i++) {
            if(i < 10){
                hkey = "0"+i+":00";
            }else {
                hkey = i+":00";
            }
            hourMap.put(hkey,0);
        }
        for(ActExgPayUserThree ec :ecs){
            if(ec.getTheTime().length() == 4){  // 统一时间格式
                hourMap.put("0"+ec.getTheTime(),ec.getTotalCount());
            }else {
                hourMap.put(ec.getTheTime(),ec.getTotalCount());
            }
        }
        for(String key : hourMap.keySet() ){
            ActExgPayUserThree bean = new ActExgPayUserThree();
            bean.setTheTime(key);
            bean.setTotalCount(hourMap.get(key));
            result.add(bean);
        }
        return result;
    }

    public List<ActExgPayUserThree> getDayActExgPayUserInfo(List<ActExgPayUserThree> ecs, ExchangeAmountEnum type) {

        List<ActExgPayUserThree> result = new ArrayList<ActExgPayUserThree>();

        Map<String, Integer> hourMap = new TreeMap();

        if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            for(int i = -7; i <= -1; i++) {
                hourMap.put(DateUtils.format(DateUtils.addDateDays(new Date(), i), DateUtils.DATE_PATTERN), 0);
            }
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            for(int i = -30; i <= -1; i++) {
                hourMap.put(DateUtils.format(DateUtils.addDateDays(new Date(), i), DateUtils.DATE_PATTERN), 0);
            }
        }

        for(ActExgPayUserThree ec :ecs){
            hourMap.put(ec.getTheTime(), ec.getTotalCount());
        }

        for(String key : hourMap.keySet() ){
            ActExgPayUserThree bean = new ActExgPayUserThree();
            bean.setTheTime(key);
            bean.setTotalCount(hourMap.get(key));
            result.add(bean);
        }

        return result;
    }


    private ActExgPayUserTwo getActExgPayUserInfo(ExchangeAmountEnum type) {

        ActExgPayUserTwo result = new ActExgPayUserTwo();

        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);

        //昨日各个小时激活人数
        List<ActExgPayUserThree> activieRound = null;
        //昨日各个小时兑换人数
        List<ActExgPayUserThree> exchangeRound = null;
        //昨日各个小时支付人数
        List<ActExgPayUserThree> payRound = null;

        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            activieRound = dealHourThree(dailyAllMakeCardDao.getActExgPayUserInfoAct(date));
            exchangeRound = dealHourThree(dailyAllMakeCardDao.getActExgPayUserInfoExg(date));
            payRound = dealHourThree(dailyAllMakeCardDao.getActExgPayUserInfoPay(date));
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            activieRound = getDayActExgPayUserInfo(dailyAllMakeCardDao.get7ActExgPayUserInfoAct(date), ExchangeAmountEnum.LASTDay);
            exchangeRound = getDayActExgPayUserInfo(dailyAllMakeCardDao.get7ActExgPayUserInfoExg(date), ExchangeAmountEnum.LASTDay);
            payRound = getDayActExgPayUserInfo(dailyAllMakeCardDao.get7ActExgPayUserInfoPay(date), ExchangeAmountEnum.LASTDay);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            activieRound = getDayActExgPayUserInfo(dailyAllMakeCardDao.get30ActExgPayUserInfoAct(date), ExchangeAmountEnum.LAST30Day);
            exchangeRound = getDayActExgPayUserInfo(dailyAllMakeCardDao.get30ActExgPayUserInfoExg(date), ExchangeAmountEnum.LAST30Day);
            payRound = getDayActExgPayUserInfo(dailyAllMakeCardDao.get30ActExgPayUserInfoPay(date), ExchangeAmountEnum.LAST30Day);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            Map<String,String> map = getMonth();
            activieRound = dailyAllMakeCardDao.get12ActExgPayUserInfoAct(map);
            exchangeRound = dailyAllMakeCardDao.get12ActExgPayUserInfoExg(map);
            payRound = dailyAllMakeCardDao.get12ActExgPayUserInfoPay(map);
        }

        if(CollectionUtils.isEmpty(activieRound)) {
            activieRound = new ArrayList<ActExgPayUserThree>();
        }
        if(CollectionUtils.isEmpty(exchangeRound)) {
            exchangeRound = new ArrayList<ActExgPayUserThree>();
        }
        if(CollectionUtils.isEmpty(payRound)) {
            payRound = new ArrayList<ActExgPayUserThree>();
        }

        //赋值
        result.setActivieRound(activieRound);
        result.setExchangeRound(exchangeRound);
        result.setPayRound(payRound);

        result.setActivieTotal(getSummary(activieRound));
        result.setLastActivieTotal(getSummary(exchangeRound));
        result.setPayUser(getSummary(payRound));

        return result;
    }


    //统计总 激活/兑换/支付人数
    private int getSummary(List<ActExgPayUserThree> selfRound) {
        int sum = 0;
        for (ActExgPayUserThree e:selfRound) {
            sum += e.getTotalCount();
        }
        return sum;
    }
    /**
     *  激活/兑换/支付人数
     *
     *  --> 结束
     */


    /**
     *  兑换/支付次数
     *
     *  --> 开始
     */
    public ExgPayTimes getExgPayTimes() {

        ExgPayTimes result = new ExgPayTimes();

        ExgPayTimesTwo lastDay = getExgPayTimesInfo(ExchangeAmountEnum.LASTDay);
        ExgPayTimesTwo last7Day = getExgPayTimesInfo(ExchangeAmountEnum.LAST7Day);
        ExgPayTimesTwo last30Day = getExgPayTimesInfo(ExchangeAmountEnum.LAST30Day);
        ExgPayTimesTwo lastMonth = getExgPayTimesInfo(ExchangeAmountEnum.LASTMONTH);

        result.setLastDay(lastDay);
        result.setLast7day(last7Day);
        result.setLast30day(last30Day);
        result.setLastMonth(lastMonth);

        return result;
    }

    private List<ExgPayTimesThree> dealHourSix(List<ExgPayTimesThree> ecs){
        List<ExgPayTimesThree> result = new ArrayList<ExgPayTimesThree>(24);
        Map<String, Integer> hourMap = new TreeMap();
        String hkey = null;
        for(int i=0;i < 24;i++) {
            if(i < 10){
                hkey = "0"+i+":00";
            }else {
                hkey = i+":00";
            }
            hourMap.put(hkey,0);
        }
        for(ExgPayTimesThree ec :ecs){
            if(ec.getTheTime().length() == 4){  // 统一时间格式
                hourMap.put("0"+ec.getTheTime(),ec.getTotalCount());
            }else {
                hourMap.put(ec.getTheTime(),ec.getTotalCount());
            }
        }
        for(String key : hourMap.keySet() ){
            ExgPayTimesThree bean = new ExgPayTimesThree();
            bean.setTheTime(key);
            bean.setTotalCount(hourMap.get(key));
            result.add(bean);
        }
        return result;
    }

    public List<ExgPayTimesThree> getDayActExgPayUserInfoFour(List<ExgPayTimesThree> ecs, ExchangeAmountEnum type) {

        List<ExgPayTimesThree> result = new ArrayList<ExgPayTimesThree>();

        Map<String, Integer> hourMap = new TreeMap();

        if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            for(int i = -7; i <= -1; i++) {
                hourMap.put(DateUtils.format(DateUtils.addDateDays(new Date(), i), DateUtils.DATE_PATTERN), 0);
            }
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            for(int i = -30; i <= -1; i++) {
                hourMap.put(DateUtils.format(DateUtils.addDateDays(new Date(), i), DateUtils.DATE_PATTERN), 0);
            }
        }

        for(ExgPayTimesThree ec :ecs){
            hourMap.put(ec.getTheTime(), ec.getTotalCount());
        }

        for(String key : hourMap.keySet() ){
            ExgPayTimesThree bean = new ExgPayTimesThree();
            bean.setTheTime(key);
            bean.setTotalCount(hourMap.get(key));
            result.add(bean);
        }

        return result;
    }


    private ExgPayTimesTwo getExgPayTimesInfo(ExchangeAmountEnum type) {

        ExgPayTimesTwo result = new ExgPayTimesTwo();

        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);

        //各时间段兑换次数
        List<ExgPayTimesThree> exchangeRound = null;
        //各时间段支付比数
        List<ExgPayTimesThree> payRound = null;

        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            exchangeRound = dealHourSix(dailyAllMakeCardDao.getExgTimesInfoDao(date));
            payRound = dealHourSix(dailyAllMakeCardDao.getPayTimesInfoDao(date));
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            exchangeRound = getDayActExgPayUserInfoFour(dailyAllMakeCardDao.get7ExgTimesInfoDao(date), ExchangeAmountEnum.LAST7Day);
            payRound = getDayActExgPayUserInfoFour(dailyAllMakeCardDao.get7PayTimesInfoDao(date), ExchangeAmountEnum.LAST7Day);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            exchangeRound = getDayActExgPayUserInfoFour(dailyAllMakeCardDao.get30ExgTimesInfoDao(date), ExchangeAmountEnum.LAST30Day);
            payRound = getDayActExgPayUserInfoFour(dailyAllMakeCardDao.get30PayTimesInfoDao(date), ExchangeAmountEnum.LAST30Day);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            Map<String,String> map = getMonth();
            exchangeRound = dailyAllMakeCardDao.get12ExgTimesInfoDao(map);
            payRound = dailyAllMakeCardDao.get12PayTimesInfoDao(map);
        }

        if(CollectionUtils.isEmpty(exchangeRound)) {
            exchangeRound = new ArrayList<ExgPayTimesThree>();
        }
        if(CollectionUtils.isEmpty(payRound)) {
            payRound = new ArrayList<ExgPayTimesThree>();
        }

        //赋值
        result.setExchangeRound(exchangeRound);
        result.setPayRound(payRound);

        result.setExchangeTotal(getSummaryTwo(exchangeRound));
        result.setPayTotal(getSummaryTwo(payRound));

        return result;

    }

    //统计总 /兑换/支付
    private int getSummaryTwo(List<ExgPayTimesThree> selfRound) {
        int sum = 0;
        for (ExgPayTimesThree e:selfRound) {
            sum += e.getTotalCount();
        }
        return sum;
    }

    /**
     *  兑换/支付次数
     *
     *  --> 结束
     */




    /**
     *  兑换/支付/预估佣金金额
     *
     *  --> 开始
     */
    public ExgPayEstAmount getExgPayEstAmount() {
        ExgPayEstAmount result = new ExgPayEstAmount();

        ExgPayEstAmountTwo lastDay = getExgPayEstAmountInfo(ExchangeAmountEnum.LASTDay);
        ExgPayEstAmountTwo last7Day = getExgPayEstAmountInfo(ExchangeAmountEnum.LAST7Day);
        ExgPayEstAmountTwo last30Day = getExgPayEstAmountInfo(ExchangeAmountEnum.LAST30Day);
        ExgPayEstAmountTwo lastMonth = getExgPayEstAmountInfo(ExchangeAmountEnum.LASTMONTH);

        result.setLastDay(lastDay);
        result.setLast7day(last7Day);
        result.setLast30day(last30Day);
        result.setLastMonth(lastMonth);

        return result;
    }

    private List<ExgPayEstAmountThree> dealHourFive(List<ExgPayEstAmountThree> ecs){
        List<ExgPayEstAmountThree> result = new ArrayList<ExgPayEstAmountThree>(24);
        Map<String, Integer> hourMap = new TreeMap();
        String hkey = null;
        for(int i=0;i < 24;i++) {
            if(i < 10){
                hkey = "0"+i+":00";
            }else {
                hkey = i+":00";
            }
            hourMap.put(hkey,0);
        }
        for(ExgPayEstAmountThree ec :ecs){
            if(ec.getTheTime().length() == 4){  // 统一时间格式
                hourMap.put("0"+ec.getTheTime(),ec.getTotalCount());
            }else {
                hourMap.put(ec.getTheTime(),ec.getTotalCount());
            }
        }
        for(String key : hourMap.keySet() ){
            ExgPayEstAmountThree bean = new ExgPayEstAmountThree();
            bean.setTheTime(key);
            bean.setTotalCount(hourMap.get(key));
            result.add(bean);
        }
        return result;
    }


    public List<ExgPayEstAmountThree> getDayActExgPayUserInfoThree(List<ExgPayEstAmountThree> ecs, ExchangeAmountEnum type) {

        List<ExgPayEstAmountThree> result = new ArrayList<ExgPayEstAmountThree>();

        Map<String, Integer> hourMap = new TreeMap();

        if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            for(int i = -7; i <= -1; i++) {
                hourMap.put(DateUtils.format(DateUtils.addDateDays(new Date(), i), DateUtils.DATE_PATTERN), 0);
            }
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            for(int i = -30; i <= -1; i++) {
                hourMap.put(DateUtils.format(DateUtils.addDateDays(new Date(), i), DateUtils.DATE_PATTERN), 0);
            }
        }

        for(ExgPayEstAmountThree ec :ecs){
            hourMap.put(ec.getTheTime(), ec.getTotalCount());
        }

        for(String key : hourMap.keySet() ){
            ExgPayEstAmountThree bean = new ExgPayEstAmountThree();
            bean.setTheTime(key);
            bean.setTotalCount(hourMap.get(key));
            result.add(bean);
        }

        return result;
    }


    private ExgPayEstAmountTwo getExgPayEstAmountInfo(ExchangeAmountEnum type) {

        ExgPayEstAmountTwo result = new ExgPayEstAmountTwo();

        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);

        //各时间段兑换金额
        List<ExgPayEstAmountThree> exchangeRound = null;
        //各时间段支付金额
        List<ExgPayEstAmountThree> payRound = null;
        //各时间段预计佣金
        List<ExgPayEstAmountThree> estRound = null;

        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            exchangeRound = dealHourFive(dailyAllMakeCardDao.getExgPayEstAmountInfoExg(date));
            payRound = dealHourFive(dailyAllMakeCardDao.getExgPayEstAmountInfoPay(date));
            estRound = dealHourFive(dailyAllMakeCardDao.getExgPayEstAmountInfoEst(date));
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            exchangeRound = getDayActExgPayUserInfoThree(dailyAllMakeCardDao.get7ExgPayEstAmountInfoExg(date), ExchangeAmountEnum.LAST7Day);
            payRound = getDayActExgPayUserInfoThree(dailyAllMakeCardDao.get7ExgPayEstAmountInfoPay(date), ExchangeAmountEnum.LAST7Day);
            estRound = getDayActExgPayUserInfoThree(dailyAllMakeCardDao.get7ExgPayEstAmountInfoEst(date), ExchangeAmountEnum.LAST7Day);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            exchangeRound = getDayActExgPayUserInfoThree(dailyAllMakeCardDao.get30ExgPayEstAmountInfoExg(date), ExchangeAmountEnum.LAST30Day);
            payRound = getDayActExgPayUserInfoThree(dailyAllMakeCardDao.get30ExgPayEstAmountInfoPay(date), ExchangeAmountEnum.LAST30Day);
            estRound = getDayActExgPayUserInfoThree(dailyAllMakeCardDao.get30ExgPayEstAmountInfoEst(date), ExchangeAmountEnum.LAST30Day);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            Map<String,String> map = getMonth();
            exchangeRound = dailyAllMakeCardDao.get12ExgPayEstAmountInfoExg(map);
            payRound = dailyAllMakeCardDao.get12ExgPayEstAmountInfoPay(map);
            estRound = dailyAllMakeCardDao.get12ExgPayEstAmountInfoEst(map);
        }

        if(CollectionUtils.isEmpty(exchangeRound)) {
            exchangeRound = new ArrayList<ExgPayEstAmountThree>();
        }
        if(CollectionUtils.isEmpty(payRound)) {
            payRound = new ArrayList<ExgPayEstAmountThree>();
        }
        if(CollectionUtils.isEmpty(estRound)) {
            payRound = new ArrayList<ExgPayEstAmountThree>();
        }

        //赋值
        result.setExchangeRound(exchangeRound);
        result.setPayRound(payRound);
        result.setEstRound(estRound);

        result.setExchangeTotal(getSummaryThree(exchangeRound));
        result.setPayUser(getSummaryThree(payRound));
        result.setEstAmount(getSummaryThree(estRound));

        return result;
    }

    //统计总 兑换/支付/预估佣金金额
    private int getSummaryThree(List<ExgPayEstAmountThree> selfRound) {
        int sum = 0;
        for (ExgPayEstAmountThree e:selfRound) {
            sum += e.getTotalCount();
        }
        return sum;
    }


    /**
     *  兑换/支付/预估佣金金额
     *
     *  --> 结束
     */



    /**
     *  兑换/支付/预估佣金金额---兑换金额
     *
     *  --> 开始
     */
    public TrendExhangeAmount getTrendExhangeAmount() {

        TrendExhangeAmount result = new TrendExhangeAmount();

        TrendExhangeAmountTwo lastDay = getTrendExhangeAmountInfo(ExchangeAmountEnum.LASTDay);
        TrendExhangeAmountTwo last7Day = getTrendExhangeAmountInfo(ExchangeAmountEnum.LAST7Day);
        TrendExhangeAmountTwo last30Day = getTrendExhangeAmountInfo(ExchangeAmountEnum.LAST30Day);
        TrendExhangeAmountTwo lastMonth = getTrendExhangeAmountInfo(ExchangeAmountEnum.LASTMONTH);

        result.setLastDay(lastDay);
        result.setLast7day(last7Day);
        result.setLast30day(last30Day);
        result.setLastMonth(lastMonth);

        return result;
    }

    private TrendExhangeAmountTwo getTrendExhangeAmountInfo(ExchangeAmountEnum type) {

        TrendExhangeAmountTwo result = new TrendExhangeAmountTwo();

        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);

        //好品购集合
        List<TrendExhangeAmountThree> selfRound = null;
        //天猫集合
        List<TrendExhangeAmountThree> tmRound = null;
        //淘宝集合
        List<TrendExhangeAmountThree> tbRound = null;
        //京东集合
        List<TrendExhangeAmountThree> jdRound = null;
        //拼多多集合
        List<TrendExhangeAmountThree> pddRound = null;

        //当需要像dao层传输两个参数时，需要用map传输
        Map map = new HashMap<String, String>();
        map.put("date", date);
        //各平台标识ID
        // [{cat_id:0,title:'好品购',},{cat_id:1,title:'京东',},{cat_id:3,title:'淘宝',},{cat_id:11,title:'拼多多',},{cat_id:4,title:'天猫',}]
        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            map.put("deduction", "0");
            selfRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendExhangeAmount(map)));
            map.put("deduction", "4");
            tmRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendExhangeAmount(map)));
            map.put("deduction", "3");
            tbRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendExhangeAmount(map)));
            map.put("deduction", "1");
            jdRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendExhangeAmount(map)));
            map.put("deduction", "11");
            pddRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendExhangeAmount(map)));
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            map.put("deduction", "0");
            selfRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendExhangeAmount(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "4");
            tmRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendExhangeAmount(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "3");
            tbRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendExhangeAmount(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "1");
            jdRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendExhangeAmount(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "11");
            pddRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendExhangeAmount(map), ExchangeAmountEnum.LAST7Day);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            map.put("deduction", "0");
            selfRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendExhangeAmount(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "4");
            tmRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendExhangeAmount(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "3");
            tbRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendExhangeAmount(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "1");
            jdRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendExhangeAmount(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "11");
            pddRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendExhangeAmount(map), ExchangeAmountEnum.LAST30Day);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            Date date1 = new Date();
            map.put("deduction", "0");
            map.put("beginMonth", DateUtils.formatMonth(DateUtils.addDateMonths(date1, -12)));
            map.put("endMonth", DateUtils.formatMonth(DateUtils.addDateMonths(date1, -1)));
            selfRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get12TrendExhangeAmount(map), ExchangeAmountEnum.LASTMONTH);
            map.put("deduction", "4");
            tmRound = dailyAllMakeCardDao.get12TrendExhangeAmount(map);
            map.put("deduction", "3");
            tbRound = dailyAllMakeCardDao.get12TrendExhangeAmount(map);
            map.put("deduction", "1");
            jdRound = dailyAllMakeCardDao.get12TrendExhangeAmount(map);
            map.put("deduction", "11");
            pddRound = dailyAllMakeCardDao.get12TrendExhangeAmount(map);
        }

        if(CollectionUtils.isEmpty(selfRound)) {
            selfRound = new ArrayList<TrendExhangeAmountThree>();
        }
        if(CollectionUtils.isEmpty(tmRound)) {
            tmRound = new ArrayList<TrendExhangeAmountThree>();
        }
        if(CollectionUtils.isEmpty(tbRound)) {
            tbRound = new ArrayList<TrendExhangeAmountThree>();
        }
        if(CollectionUtils.isEmpty(jdRound)) {
            jdRound = new ArrayList<TrendExhangeAmountThree>();
        }
        if(CollectionUtils.isEmpty(pddRound)) {
            pddRound = new ArrayList<TrendExhangeAmountThree>();
        }

        result.setSelfRound(selfRound);
        result.setTmRound(tmRound);
        result.setTbRound(tbRound);
        result.setJdRound(jdRound);
        result.setPddRound(pddRound);

        result.setSelf(getSummaryFour(selfRound));
        result.setTm(getSummaryFour(tmRound));
        result.setTb(getSummaryFour(tbRound));
        result.setJd(getSummaryFour(jdRound));
        result.setPdd(getSummaryFour(pddRound));

        return  result;
    }

    private int getSummaryFour(List<TrendExhangeAmountThree> selfRound) {
        int sum = 0;
        for (TrendExhangeAmountThree e:selfRound) {
            sum += e.getTotalCount();
        }
        return sum;
    }
    /**
     *  兑换/支付/预估佣金金额---兑换金额
     *
     *  --> 结束
     */




    /**
     *  兑换/支付/预估佣金金额---支付金额
     *
     *  --> 开始
     */
    public TrendExhangeAmount getTrendPayAmount() {

        TrendExhangeAmount result = new TrendExhangeAmount();

        TrendExhangeAmountTwo lastDay = getTrendPayAmountInfo(ExchangeAmountEnum.LASTDay);
        TrendExhangeAmountTwo last7Day = getTrendPayAmountInfo(ExchangeAmountEnum.LAST7Day);
        TrendExhangeAmountTwo last30Day = getTrendPayAmountInfo(ExchangeAmountEnum.LAST30Day);
        TrendExhangeAmountTwo lastMonth = getTrendPayAmountInfo(ExchangeAmountEnum.LASTMONTH);

        result.setLastDay(lastDay);
        result.setLast7day(last7Day);
        result.setLast30day(last30Day);
        result.setLastMonth(lastMonth);

        return result;
    }

    private TrendExhangeAmountTwo getTrendPayAmountInfo(ExchangeAmountEnum type) {

        TrendExhangeAmountTwo result = new TrendExhangeAmountTwo();

        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);

        //好品购集合
        List<TrendExhangeAmountThree> selfRound = null;
        //天猫集合
        List<TrendExhangeAmountThree> tmRound = null;
        //淘宝集合
        List<TrendExhangeAmountThree> tbRound = null;
        //京东集合
        List<TrendExhangeAmountThree> jdRound = null;
        //拼多多集合
        List<TrendExhangeAmountThree> pddRound = null;

        //当需要像dao层传输两个参数时，需要用map传输
        Map map = new HashMap<String, String>();
        map.put("date", date);
        //各平台标识ID
        // [{cat_id:0,title:'好品购',},{cat_id:1,title:'京东',},{cat_id:3,title:'淘宝',},{cat_id:11,title:'拼多多',},{cat_id:4,title:'天猫',}]
        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            map.put("deduction", "0");
            selfRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendPayAmount(map)));
            map.put("deduction", "4");
            tmRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendPayAmount(map)));
            map.put("deduction", "3");
            tbRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendPayAmount(map)));
            map.put("deduction", "1");
            jdRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendPayAmount(map)));
            map.put("deduction", "11");
            pddRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendPayAmount(map)));
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            map.put("deduction", "0");
            selfRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendPayAmount(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "4");
            tmRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendPayAmount(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "3");
            tbRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendPayAmount(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "1");
            jdRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendPayAmount(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "11");
            pddRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendPayAmount(map), ExchangeAmountEnum.LAST7Day);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            map.put("deduction", "0");
            selfRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendPayAmount(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "4");
            tmRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendPayAmount(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "3");
            tbRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendPayAmount(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "1");
            jdRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendPayAmount(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "11");
            pddRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendPayAmount(map), ExchangeAmountEnum.LAST30Day);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            Date date1 = new Date();
            map.put("deduction", "0");
            map.put("beginMonth", DateUtils.formatMonth(DateUtils.addDateMonths(date1, -12)));
            map.put("endMonth", DateUtils.formatMonth(DateUtils.addDateMonths(date1, -1)));
            selfRound = dailyAllMakeCardDao.get12TrendPayAmount(map);
            map.put("deduction", "4");
            tmRound = dailyAllMakeCardDao.get12TrendPayAmount(map);
            map.put("deduction", "3");
            tbRound = dailyAllMakeCardDao.get12TrendPayAmount(map);
            map.put("deduction", "1");
            jdRound = dailyAllMakeCardDao.get12TrendPayAmount(map);
            map.put("deduction", "11");
            pddRound = dailyAllMakeCardDao.get12TrendPayAmount(map);
        }

        if(CollectionUtils.isEmpty(selfRound)) {
            selfRound = new ArrayList<TrendExhangeAmountThree>();
        }
        if(CollectionUtils.isEmpty(tmRound)) {
            tmRound = new ArrayList<TrendExhangeAmountThree>();
        }
        if(CollectionUtils.isEmpty(tbRound)) {
            tbRound = new ArrayList<TrendExhangeAmountThree>();
        }
        if(CollectionUtils.isEmpty(jdRound)) {
            jdRound = new ArrayList<TrendExhangeAmountThree>();
        }
        if(CollectionUtils.isEmpty(pddRound)) {
            pddRound = new ArrayList<TrendExhangeAmountThree>();
        }

        result.setSelfRound(selfRound);
        result.setTmRound(tmRound);
        result.setTbRound(tbRound);
        result.setJdRound(jdRound);
        result.setPddRound(pddRound);

        result.setSelf(getSummaryFour(selfRound));
        result.setTm(getSummaryFour(tmRound));
        result.setTb(getSummaryFour(tbRound));
        result.setJd(getSummaryFour(jdRound));
        result.setPdd(getSummaryFour(pddRound));

        return  result;
    }

    /**
     *  兑换/支付/预估佣金金额---支付金额
     *
     *  --> 结束
     */



    /**
     *  兑换/支付/预估佣金金额---预估佣金
     *
     *  --> 开始
     */
    public TrendExhangeAmount getTrendEstAmount() {

        TrendExhangeAmount result = new TrendExhangeAmount();

        TrendExhangeAmountTwo lastDay = getTrendEstAmountInfo(ExchangeAmountEnum.LASTDay);
        TrendExhangeAmountTwo last7Day = getTrendEstAmountInfo(ExchangeAmountEnum.LAST7Day);
        TrendExhangeAmountTwo last30Day = getTrendEstAmountInfo(ExchangeAmountEnum.LAST30Day);
        TrendExhangeAmountTwo lastMonth = getTrendEstAmountInfo(ExchangeAmountEnum.LASTMONTH);

        result.setLastDay(lastDay);
        result.setLast7day(last7Day);
        result.setLast30day(last30Day);
        result.setLastMonth(lastMonth);

        return result;
    }

    private TrendExhangeAmountTwo getTrendEstAmountInfo(ExchangeAmountEnum type) {

        TrendExhangeAmountTwo result = new TrendExhangeAmountTwo();

        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);

        //好品购集合
        List<TrendExhangeAmountThree> selfRound = null;
        //天猫集合
        List<TrendExhangeAmountThree> tmRound = null;
        //淘宝集合
        List<TrendExhangeAmountThree> tbRound = null;
        //京东集合
        List<TrendExhangeAmountThree> jdRound = null;
        //拼多多集合
        List<TrendExhangeAmountThree> pddRound = null;

        //当需要像dao层传输两个参数时，需要用map传输
        Map map = new HashMap<String, String>();
        map.put("date", date);
        //各平台标识ID
        // [{cat_id:0,title:'好品购',},{cat_id:1,title:'京东',},{cat_id:3,title:'淘宝',},{cat_id:11,title:'拼多多',},{cat_id:4,title:'天猫',}]
        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            map.put("deduction", "0");
            selfRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendEstAmountInfo(map)));
            map.put("deduction", "4");
            tmRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendEstAmountInfo(map)));
            map.put("deduction", "3");
            tbRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendEstAmountInfo(map)));
            map.put("deduction", "1");
            jdRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendEstAmountInfo(map)));
            map.put("deduction", "11");
            pddRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendEstAmountInfo(map)));
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            map.put("deduction", "0");
            selfRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendEstAmountInfo(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "4");
            tmRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendEstAmountInfo(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "3");
            tbRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendEstAmountInfo(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "1");
            jdRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendEstAmountInfo(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "11");
            pddRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendEstAmountInfo(map), ExchangeAmountEnum.LAST7Day);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            map.put("deduction", "0");
            selfRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendEstAmountInfo(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "4");
            tmRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendEstAmountInfo(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "3");
            tbRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendEstAmountInfo(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "1");
            jdRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendEstAmountInfo(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "11");
            pddRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendEstAmountInfo(map), ExchangeAmountEnum.LAST30Day);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            Date date1 = new Date();
            map.put("deduction", "0");
            map.put("beginMonth", DateUtils.formatMonth(DateUtils.addDateMonths(date1, -12)));
            map.put("endMonth", DateUtils.formatMonth(DateUtils.addDateMonths(date1, -1)));
            selfRound = dailyAllMakeCardDao.get12TrendEstAmountInfo(map);
            map.put("deduction", "4");
            tmRound = dailyAllMakeCardDao.get12TrendEstAmountInfo(map);
            map.put("deduction", "3");
            tbRound = dailyAllMakeCardDao.get12TrendEstAmountInfo(map);
            map.put("deduction", "1");
            jdRound = dailyAllMakeCardDao.get12TrendEstAmountInfo(map);
            map.put("deduction", "11");
            pddRound = dailyAllMakeCardDao.get12TrendEstAmountInfo(map);
        }

        if(CollectionUtils.isEmpty(selfRound)) {
            selfRound = new ArrayList<TrendExhangeAmountThree>();
        }
        if(CollectionUtils.isEmpty(tmRound)) {
            tmRound = new ArrayList<TrendExhangeAmountThree>();
        }
        if(CollectionUtils.isEmpty(tbRound)) {
            tbRound = new ArrayList<TrendExhangeAmountThree>();
        }
        if(CollectionUtils.isEmpty(jdRound)) {
            jdRound = new ArrayList<TrendExhangeAmountThree>();
        }
        if(CollectionUtils.isEmpty(pddRound)) {
            pddRound = new ArrayList<TrendExhangeAmountThree>();
        }

        result.setSelfRound(selfRound);
        result.setTmRound(tmRound);
        result.setTbRound(tbRound);
        result.setJdRound(jdRound);
        result.setPddRound(pddRound);

        result.setSelf(getSummaryFour(selfRound));
        result.setTm(getSummaryFour(tmRound));
        result.setTb(getSummaryFour(tbRound));
        result.setJd(getSummaryFour(jdRound));
        result.setPdd(getSummaryFour(pddRound));

        return  result;
    }

    private List<TrendExhangeAmountThree> dealHourActiveUserTwo(List<TrendExhangeAmountThree> ecs){
        List<TrendExhangeAmountThree> result = new ArrayList<TrendExhangeAmountThree>(24);
        Map<String, Integer> hourMap = new TreeMap();
        String hkey = null;
        for(int i=0;i < 24;i++) {
            if(i < 10){
                hkey = "0"+i+":00";
            }else {
                hkey = i+":00";
            }
            hourMap.put(hkey,0);
        }
        for(TrendExhangeAmountThree ec :ecs){
            if(ec.getTheTime().length() == 4){  // 统一时间格式
                hourMap.put("0"+ec.getTheTime(),ec.getTotalCount());
            }else {
                hourMap.put(ec.getTheTime(),ec.getTotalCount());
            }
        }
        for(String key : hourMap.keySet() ){
            TrendExhangeAmountThree bean = new TrendExhangeAmountThree();
            bean.setTheTime(key);
            bean.setTotalCount(hourMap.get(key));
            result.add(bean);
        }
        return result;
    }

    public List<TrendExhangeAmountThree> getTheHR(List<TrendExhangeAmountThree> list) {

        List<TrendExhangeAmountThree> result = new ArrayList<TrendExhangeAmountThree>(24);

        Map<String, Integer> hourMap = new TreeMap();
        String hkey = null;
        for(int i=0;i < 24;i++) {
            if(i < 10){
                hkey = "0"+i+":00";
            }else {
                hkey = i+":00";
            }
            hourMap.put(hkey,0);
        }

        for (TrendExhangeAmountThree ta : list) {
            hourMap.put(ta.getTheTime(), ta.getTotalCount());
        }

        Set<String> set = hourMap.keySet();

        for (String str : set) {
            TrendExhangeAmountThree three = new TrendExhangeAmountThree();
            three.setTheTime(str);
            three.setTotalCount(hourMap.get(str));
            result.add(three);
        }

        return result;
    }

    public List<TrendExhangeAmountThree> getDayActExgPayUserInfoFive(List<TrendExhangeAmountThree> ecs, ExchangeAmountEnum type) {

        List<TrendExhangeAmountThree> result = new ArrayList<TrendExhangeAmountThree>();

        Map<String, Integer> hourMap = new TreeMap();

        if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            for(int i = -7; i <= -1; i++) {
                hourMap.put(DateUtils.format(DateUtils.addDateDays(new Date(), i), DateUtils.DATE_PATTERN), 0);
            }
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            for(int i = -30; i <= -1; i++) {
                hourMap.put(DateUtils.format(DateUtils.addDateDays(new Date(), i), DateUtils.DATE_PATTERN), 0);
            }
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            for (int i = -1; i >= -12; i--) {
                hourMap.put(DateUtils.formatMonth(DateUtils.addDateMonths(new Date(), i)), 0);
                if(DateUtils.formatMonth(DateUtils.addDateMonths(new Date(), i)).equals("2019-08"))
                    break;
            }
        }

        for(TrendExhangeAmountThree ec :ecs){
            hourMap.put(ec.getTheTime(), ec.getTotalCount());
        }

        for(String key : hourMap.keySet() ){
            TrendExhangeAmountThree bean = new TrendExhangeAmountThree();
            bean.setTheTime(key);
            bean.setTotalCount(hourMap.get(key));
            result.add(bean);
        }

        return result;
    }



    /**
     *  兑换/支付/预估佣金金额---预估佣金
     *
     *  --> 结束
     */



    /**
     *  兑换/支付/预估佣金金额---兑换次数
     *
     *  --> 开始
     */
    public TrendExhangeAmount getTrendExchangTimes() {

        TrendExhangeAmount result = new TrendExhangeAmount();

        TrendExhangeAmountTwo lastDay = getTrendExchangTimesInfo(ExchangeAmountEnum.LASTDay);
        TrendExhangeAmountTwo last7Day = getTrendExchangTimesInfo(ExchangeAmountEnum.LAST7Day);
        TrendExhangeAmountTwo last30Day = getTrendExchangTimesInfo(ExchangeAmountEnum.LAST30Day);
        TrendExhangeAmountTwo lastMonth = getTrendExchangTimesInfo(ExchangeAmountEnum.LASTMONTH);

        result.setLastDay(lastDay);
        result.setLast7day(last7Day);
        result.setLast30day(last30Day);
        result.setLastMonth(lastMonth);

        return result;
    }

    private TrendExhangeAmountTwo getTrendExchangTimesInfo(ExchangeAmountEnum type) {

        TrendExhangeAmountTwo result = new TrendExhangeAmountTwo();

        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);

        //好品购集合
        List<TrendExhangeAmountThree> selfRound = null;
        //天猫集合
        List<TrendExhangeAmountThree> tmRound = null;
        //淘宝集合
        List<TrendExhangeAmountThree> tbRound = null;
        //京东集合
        List<TrendExhangeAmountThree> jdRound = null;
        //拼多多集合
        List<TrendExhangeAmountThree> pddRound = null;

        //当需要像dao层传输两个参数时，需要用map传输
        Map map = new HashMap<String, String>();
        map.put("date", date);
        //各平台标识ID
        // [{cat_id:0,title:'好品购',},{cat_id:1,title:'京东',},{cat_id:3,title:'淘宝',},{cat_id:11,title:'拼多多',},{cat_id:4,title:'天猫',}]
        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            map.put("deduction", "0");
            selfRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendExchangTimes(map)));
            map.put("deduction", "4");
            tmRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendExchangTimes(map)));
            map.put("deduction", "3");
            tbRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendExchangTimes(map)));
            map.put("deduction", "1");
            jdRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendExchangTimes(map)));
            map.put("deduction", "11");
            pddRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendExchangTimes(map)));
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            map.put("deduction", "0");
            selfRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendExchangTimes(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "4");
            tmRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendExchangTimes(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "3");
            tbRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendExchangTimes(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "1");
            jdRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendExchangTimes(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "11");
            pddRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendExchangTimes(map), ExchangeAmountEnum.LAST7Day);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            map.put("deduction", "0");
            selfRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendExchangTimes(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "4");
            tmRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendExchangTimes(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "3");
            tbRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendExchangTimes(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "1");
            jdRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendExchangTimes(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "11");
            pddRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendExchangTimes(map), ExchangeAmountEnum.LAST30Day);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            Date date1 = new Date();
            map.put("deduction", "0");
            map.put("beginMonth", DateUtils.formatMonth(DateUtils.addDateMonths(date1, -12)));
            map.put("endMonth", DateUtils.formatMonth(DateUtils.addDateMonths(date1, -1)));
            selfRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get12TrendExchangTimes(map), ExchangeAmountEnum.LASTMONTH);
            map.put("deduction", "4");
            tmRound = dailyAllMakeCardDao.get12TrendExchangTimes(map);
            map.put("deduction", "3");
            tbRound = dailyAllMakeCardDao.get12TrendExchangTimes(map);
            map.put("deduction", "1");
            jdRound = dailyAllMakeCardDao.get12TrendExchangTimes(map);
            map.put("deduction", "11");
            pddRound = dailyAllMakeCardDao.get12TrendExchangTimes(map);
        }

        if(CollectionUtils.isEmpty(selfRound)) {
            selfRound = new ArrayList<TrendExhangeAmountThree>();
        }
        if(CollectionUtils.isEmpty(tmRound)) {
            tmRound = new ArrayList<TrendExhangeAmountThree>();
        }
        if(CollectionUtils.isEmpty(tbRound)) {
            tbRound = new ArrayList<TrendExhangeAmountThree>();
        }
        if(CollectionUtils.isEmpty(jdRound)) {
            jdRound = new ArrayList<TrendExhangeAmountThree>();
        }
        if(CollectionUtils.isEmpty(pddRound)) {
            pddRound = new ArrayList<TrendExhangeAmountThree>();
        }

        result.setSelfRound(selfRound);
        result.setTmRound(tmRound);
        result.setTbRound(tbRound);
        result.setJdRound(jdRound);
        result.setPddRound(pddRound);

        result.setSelf(getSummaryFour(selfRound));
        result.setTm(getSummaryFour(tmRound));
        result.setTb(getSummaryFour(tbRound));
        result.setJd(getSummaryFour(jdRound));
        result.setPdd(getSummaryFour(pddRound));

        return  result;
    }

    /**
     *  兑换/支付/预估佣金金额---兑换次数
     *
     *  --> 结束
     */


    /**
     *  兑换/支付/预估佣金金额---支付比数
     *
     *  --> 开始
     */
    public TrendExhangeAmount getTrendPayTimes() {
        TrendExhangeAmount result = new TrendExhangeAmount();

        TrendExhangeAmountTwo lastDay = getTrendPayTimesInfo(ExchangeAmountEnum.LASTDay);
        TrendExhangeAmountTwo last7Day = getTrendPayTimesInfo(ExchangeAmountEnum.LAST7Day);
        TrendExhangeAmountTwo last30Day = getTrendPayTimesInfo(ExchangeAmountEnum.LAST30Day);
        TrendExhangeAmountTwo lastMonth = getTrendPayTimesInfo(ExchangeAmountEnum.LASTMONTH);

        result.setLastDay(lastDay);
        result.setLast7day(last7Day);
        result.setLast30day(last30Day);
        result.setLastMonth(lastMonth);

        return result;
    }

    private TrendExhangeAmountTwo getTrendPayTimesInfo(ExchangeAmountEnum type) {

        TrendExhangeAmountTwo result = new TrendExhangeAmountTwo();

        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);

        //好品购集合
        List<TrendExhangeAmountThree> selfRound = null;
        //天猫集合
        List<TrendExhangeAmountThree> tmRound = null;
        //淘宝集合
        List<TrendExhangeAmountThree> tbRound = null;
        //京东集合
        List<TrendExhangeAmountThree> jdRound = null;
        //拼多多集合
        List<TrendExhangeAmountThree> pddRound = null;

        //当需要像dao层传输两个参数时，需要用map传输
        Map map = new HashMap<String, String>();
        map.put("date", date);
        //各平台标识ID
        // [{cat_id:0,title:'好品购',},{cat_id:1,title:'京东',},{cat_id:3,title:'淘宝',},{cat_id:11,title:'拼多多',},{cat_id:4,title:'天猫',}]
        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            map.put("deduction", "0");
            selfRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendPayTimesInfo(map)));
            map.put("deduction", "4");
            tmRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendPayTimesInfo(map)));
            map.put("deduction", "3");
            tbRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendPayTimesInfo(map)));
            map.put("deduction", "1");
            jdRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendPayTimesInfo(map)));
            map.put("deduction", "11");
            pddRound = getTheHR(dealHourActiveUserTwo(dailyAllMakeCardDao.getTrendPayTimesInfo(map)));
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            map.put("deduction", "0");
            selfRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendPayTimesInfo(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "4");
            tmRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendPayTimesInfo(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "3");
            tbRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendPayTimesInfo(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "1");
            jdRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendPayTimesInfo(map), ExchangeAmountEnum.LAST7Day);
            map.put("deduction", "11");
            pddRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get7TrendPayTimesInfo(map), ExchangeAmountEnum.LAST7Day);
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            map.put("deduction", "0");
            selfRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendPayTimesInfo(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "4");
            tmRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendPayTimesInfo(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "3");
            tbRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendPayTimesInfo(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "1");
            jdRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendPayTimesInfo(map), ExchangeAmountEnum.LAST30Day);
            map.put("deduction", "11");
            pddRound = getDayActExgPayUserInfoFive(dailyAllMakeCardDao.get30TrendPayTimesInfo(map), ExchangeAmountEnum.LAST30Day);
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            Date date1 = new Date();
            map.put("deduction", "0");
            map.put("beginMonth", DateUtils.formatMonth(DateUtils.addDateMonths(date1, -12)));
            map.put("endMonth", DateUtils.formatMonth(DateUtils.addDateMonths(date1, -1)));
            selfRound = dailyAllMakeCardDao.get12TrendPayTimesInfo(map);
            map.put("deduction", "4");
            tmRound = dailyAllMakeCardDao.get12TrendPayTimesInfo(map);
            map.put("deduction", "3");
            tbRound = dailyAllMakeCardDao.get12TrendPayTimesInfo(map);
            map.put("deduction", "1");
            jdRound = dailyAllMakeCardDao.get12TrendPayTimesInfo(map);
            map.put("deduction", "11");
            pddRound = dailyAllMakeCardDao.get12TrendPayTimesInfo(map);
        }

        if(CollectionUtils.isEmpty(selfRound)) {
            selfRound = new ArrayList<TrendExhangeAmountThree>();
        }
        if(CollectionUtils.isEmpty(tmRound)) {
            tmRound = new ArrayList<TrendExhangeAmountThree>();
        }
        if(CollectionUtils.isEmpty(tbRound)) {
            tbRound = new ArrayList<TrendExhangeAmountThree>();
        }
        if(CollectionUtils.isEmpty(jdRound)) {
            jdRound = new ArrayList<TrendExhangeAmountThree>();
        }
        if(CollectionUtils.isEmpty(pddRound)) {
            pddRound = new ArrayList<TrendExhangeAmountThree>();
        }

        result.setSelfRound(selfRound);
        result.setTmRound(tmRound);
        result.setTbRound(tbRound);
        result.setJdRound(jdRound);
        result.setPddRound(pddRound);

        result.setSelf(getSummaryFour(selfRound));
        result.setTm(getSummaryFour(tmRound));
        result.setTb(getSummaryFour(tbRound));
        result.setJd(getSummaryFour(jdRound));
        result.setPdd(getSummaryFour(pddRound));

        return  result;
    }

    /**
     *  兑换/支付/预估佣金金额---支付比数
     *
     *  --> 结束
     */


    /**
     *各平台兑换、支付、佣金占比分析
     *
     *  --> 开始
     */
    public PlatformExgPayRate getPlatformExgPayRate() {

        PlatformExgPayRate result = new PlatformExgPayRate();

        //访问兑换，支付，佣金，结算佣金表，分别获取金额和数量并封装进List中

        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);

        List<PlatformExgPayRateFour> resultDH = dailyAllMakeCardDao.getPlatformExgPayRateInfoDH(date);
        List<PlatformExgPayRateFour> resultZF = dailyAllMakeCardDao.getPlatformExgPayRateInfoZF(date);
        List<PlatformExgPayRateFour> resultYJYJ = dailyAllMakeCardDao.getPlatformExgPayRateInfoYJYJ(date);
        List<PlatformExgPayRateFour> resultYJ = dailyAllMakeCardDao.getPlatformExgPayRateInfoYJ(date);

        if(CollectionUtils.isEmpty(resultDH)) {
            resultDH = new ArrayList<PlatformExgPayRateFour>();
        }
        if(CollectionUtils.isEmpty(resultZF)) {
            resultZF = new ArrayList<PlatformExgPayRateFour>();
        }
        if(CollectionUtils.isEmpty(resultYJYJ)) {
            resultYJYJ = new ArrayList<PlatformExgPayRateFour>();
        }
        if(CollectionUtils.isEmpty(resultYJ)) {
            resultYJ = new ArrayList<PlatformExgPayRateFour>();
        }

        List<List<PlatformExgPayRateFour>> list = new ArrayList<List<PlatformExgPayRateFour>>();
        list.add(resultDH);
        list.add(resultZF);
        list.add(resultYJYJ);
        list.add(resultYJ);

        //判断好品购是否没有数据，无数据在首部增加数据0
        insertHPGIsNull(list);

        PlatformExgPayRateTwo amount = getPlatformExgPayRateInfo(AmountEnum.money, list);
        PlatformExgPayRateTwo num = getPlatformExgPayRateInfo(AmountEnum.number, list);

        result.setAmount(amount);
        result.setNum(num);

        return result;
    }

        private List<List<PlatformExgPayRateFour>> insertHPGIsNull (List<List<PlatformExgPayRateFour>> list) {
        for (List<PlatformExgPayRateFour> listi : list) {
            if(listi.size() < 5) {
                PlatformExgPayRateFour platformExgPayRateFour = new PlatformExgPayRateFour();
                platformExgPayRateFour.setNumbers(0);
                platformExgPayRateFour.setPrice(0);
                listi.add(0, platformExgPayRateFour);
            }
        }
        return list;
    }


    private PlatformExgPayRateTwo getPlatformExgPayRateInfo(AmountEnum type, List<List<PlatformExgPayRateFour>> list) {

        PlatformExgPayRateTwo result = new PlatformExgPayRateTwo();

        //兑换/次数
        PlatformExgPayRateThree exchange = new PlatformExgPayRateThree();
        //支付/笔数
        PlatformExgPayRateThree pay = new PlatformExgPayRateThree();
        //预估佣金/订单数
        PlatformExgPayRateThree estAmount = new PlatformExgPayRateThree();
        //结算佣金/结算佣金订单
        PlatformExgPayRateThree settleAmount = new PlatformExgPayRateThree();

        //各平台标识ID [{cat_id:0,title:'好品购',},{cat_id:1,title:'京东',},{cat_id:3,title:'淘宝',},
        //               {cat_id:11,title:'拼多多',},{cat_id:4,title:'天猫',}]
        if (AmountEnum.money.equals(type)) {

            exchange.setSelf(list.get(0).get(0).getPrice());
            exchange.setJd(list.get(0).get(1).getPrice());
            exchange.setTb(list.get(0).get(2).getPrice());
            exchange.setTm(list.get(0).get(3).getPrice());
            exchange.setPdd(list.get(0).get(4).getPrice());

            pay.setSelf(list.get(1).get(0).getPrice());
            pay.setJd(list.get(1).get(1).getPrice());
            pay.setTb(list.get(1).get(2).getPrice());
            pay.setTm(list.get(1).get(3).getPrice());
            pay.setPdd(list.get(1).get(4).getPrice());

            estAmount.setSelf(list.get(2).get(0).getPrice());
            estAmount.setJd(list.get(2).get(1).getPrice());
            estAmount.setTb(list.get(2).get(2).getPrice());
            estAmount.setTm(list.get(2).get(3).getPrice());
            estAmount.setPdd(list.get(2).get(4).getPrice());

            settleAmount.setSelf(list.get(3).get(0).getPrice());
            settleAmount.setJd(list.get(3).get(1).getPrice());
            settleAmount.setTb(list.get(3).get(2).getPrice());
            settleAmount.setTm(list.get(3).get(3).getPrice());
            settleAmount.setPdd(list.get(3).get(4).getPrice());

        } else if (AmountEnum.number.equals(type)) {

            exchange.setSelf(list.get(0).get(0).getNumbers());
            exchange.setJd(list.get(0).get(1).getNumbers());
            exchange.setTb(list.get(0).get(2).getNumbers());
            exchange.setTm(list.get(0).get(3).getNumbers());
            exchange.setPdd(list.get(0).get(4).getNumbers());

            pay.setSelf(list.get(1).get(0).getNumbers());
            pay.setJd(list.get(1).get(1).getNumbers());
            pay.setTb(list.get(1).get(2).getNumbers());
            pay.setTm(list.get(1).get(3).getNumbers());
            pay.setPdd(list.get(1).get(4).getNumbers());

            estAmount.setSelf(list.get(2).get(0).getNumbers());
            estAmount.setJd(list.get(2).get(1).getNumbers());
            estAmount.setTb(list.get(2).get(2).getNumbers());
            estAmount.setTm(list.get(2).get(3).getNumbers());
            estAmount.setPdd(list.get(2).get(4).getNumbers());

            settleAmount.setSelf(list.get(3).get(0).getNumbers());
            settleAmount.setJd(list.get(3).get(1).getNumbers());
            settleAmount.setTb(list.get(3).get(2).getNumbers());
            settleAmount.setTm(list.get(3).get(3).getNumbers());
            settleAmount.setPdd(list.get(3).get(4).getNumbers());
        }


        if(exchange == null){
            exchange = new PlatformExgPayRateThree();
        }
        if(pay == null){
            exchange = new PlatformExgPayRateThree();
        }
        if(estAmount == null){
            exchange = new PlatformExgPayRateThree();
        }
        if(settleAmount == null){
            exchange = new PlatformExgPayRateThree();
        }

        result.setExchange(exchange);
        result.setPay(pay);
        result.setEstAmount(estAmount);
        result.setSettleAmount(settleAmount);

        return result;
    }


    /**
     *各平台兑换、支付、佣金占比分析
     *
     *  --> 结束
     */



    /**
     *  首页基本信息
     *
     *  --> 开始
     */
    @Override
    public BasicInfoVo getBasicInfo() {
        DailyAllMakeCard maxMakeCard = dailyAllMakeCardDao.getMaxMakeCard();
        DailyAllActivateCard maxActivateCard = dailyAllMakeCardDao.getMaxActivateCard();
        DailyExchangeInformation maxExchangeInfor = dailyAllMakeCardDao.getMaxExchangeInfor();
        DailyRegisteredUser totalUser = dailyAllMakeCardDao.getTotalUser();
        DailyRegisteredUserBalance totalUserBalance = dailyAllMakeCardDao.getTotalUserBalance();

        //BI2.0 获取10个新字段数据
        ActivateExchange ae = dailyAllMakeCardDao.getAE();
        UserPayment up = dailyAllMakeCardDao.getUP();
        EstimatedCommission ec = dailyAllMakeCardDao.getEC();
        UserSettlement us = dailyAllMakeCardDao.getUS();

        BasicInfoVo basicInfoVo = new BasicInfoVo();
        if (maxExchangeInfor != null) {
            basicInfoVo.setExchangeAmount(maxExchangeInfor.getTotalAmount());
            basicInfoVo.setExchangePerAvg(maxExchangeInfor.getPerExchangeAmount());
        }
        if (maxActivateCard != null) {
            basicInfoVo.setActiveAmount(maxActivateCard.getTotalValue());
            basicInfoVo.setActiveTotal(maxActivateCard.getTotalCount());
        }
        if (maxMakeCard != null) {
            basicInfoVo.setCardAmount(maxMakeCard.getTotalValue());
            basicInfoVo.setCardNum(maxMakeCard.getTotalCount());
        }
        if (totalUser != null) {
            basicInfoVo.setRegUser(totalUser.getTotalCustomer());
        }
        if (totalUserBalance != null) {
            basicInfoVo.setTotalRemaining(totalUserBalance.getTotalAmount());
        }
        //BI2.0 获取10个新字段数据
        if (ae != null) {
            basicInfoVo.setRegUserRate(ae.getActivationTotalCount());
            basicInfoVo.setExchangePer(ae.getExchangeTotalCount());
        }
        if (up != null) {
            basicInfoVo.setPayAmount(up.getPaymentAmount());
            basicInfoVo.setPayUser(up.getPaymentCount());
            basicInfoVo.setPayUnitPrice(up.getUnitPrice());
            basicInfoVo.setPayNum(up.getPaymentTimes());
        }
        if (ec != null) {
            basicInfoVo.setEstAmount(ec.getEstimatePrice());
        }
        if (us != null) {
            basicInfoVo.setSettleUserAmount(us.getPerCommission());
            basicInfoVo.setSettleAmount(us.getPaymentAmount());
            basicInfoVo.setSettleOrder(us.getPaymentTimes());
        }

        return basicInfoVo;
    }

    /**
     *  首页基本信息
     *
     *  --> 结束
     */

}
