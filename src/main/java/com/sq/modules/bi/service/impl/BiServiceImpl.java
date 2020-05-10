package com.sq.modules.bi.service.impl;

import com.sq.common.utils.DateUtils;
import com.sq.modules.bi.dao.DailyAllMakeCardDao;
import com.sq.modules.bi.entity.*;
import com.sq.modules.bi.entity.vo.*;
import com.sq.modules.bi.entity.vo.active.ActiveCardResultVo;
import com.sq.modules.bi.entity.vo.active.ActiveCardTotalVo;
import com.sq.modules.bi.entity.vo.active.ActiveCardVo;
import com.sq.modules.bi.entity.vo.exchange.ExchangeAmountResultVo;
import com.sq.modules.bi.entity.vo.exchange.ExchangeAmountVo;
import com.sq.modules.bi.entity.vo.exchange.ExchangeCardTotalVo;
import com.sq.modules.bi.enums.CardEnum;
import com.sq.modules.bi.enums.ExchangeAmountEnum;
import com.sq.modules.bi.service.BIService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

/**
 * <p>
 * 雀实省钱BI 服务类
 * </p>
 *
 * @author zhongxunan * @since 2019-05-12
 */
@Service
public class BiServiceImpl implements BIService {

    @Autowired
    private DailyAllMakeCardDao dailyAllMakeCardDao;


    /**
     * 生成卡各面值金额占比分析
     */
    @Override
    public CardFaceAnalyzeVo getCardFaceAnalyze() {
        List<OrdinaryCard> ordinaryCardRate = dailyAllMakeCardDao.getOrdinaryCardRate(DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN));
        List<CustomCard> customCardRate = dailyAllMakeCardDao.getCustomCardRate(DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN));
        Long normalTotal = 0L;  //普通卡总数
        if (CollectionUtils.isNotEmpty(ordinaryCardRate)) {
            for (OrdinaryCard ocard : ordinaryCardRate) {
                normalTotal += ocard.getTotalCount();
            }
        }

        Long customerTotal = 0L;  //定制卡总数
        if (CollectionUtils.isNotEmpty(customCardRate)) {
            for (CustomCard cCard : customCardRate) {
                customerTotal += cCard.getTotalCount();
            }
        }

        CardFaceAnalyzeVo cardFaceAnalyzeVo = new CardFaceAnalyzeVo();
        cardFaceAnalyzeVo.setNormalCard(ordinaryCardRate);
        cardFaceAnalyzeVo.setNormalTotal(normalTotal);
        cardFaceAnalyzeVo.setCustomCard(customCardRate);
        cardFaceAnalyzeVo.setCustomerTotal(customerTotal);
        return cardFaceAnalyzeVo;
    }

    /**
     * 各平台优惠券兑换情况分析
     */
    @Override
    public PlatformAnalyzeVo getPlatformAnalyze() {
        List<PlatformCouponExchange> platformAnalyze = dailyAllMakeCardDao.getPlatformAnalyze(DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN));
        PlatformAnalyzeVo result = new PlatformAnalyzeVo();
        Long persons = 0L;      // 总人数
        BigDecimal amounts = new BigDecimal(0);         // 总金额
        if (CollectionUtils.isNotEmpty(platformAnalyze)) {
            for (PlatformCouponExchange pcx : platformAnalyze) {
                persons += pcx.getTotalCustomer();
                amounts = amounts.add(pcx.getTotalAmount());
            }
            //  占比
//            for (PlatformCouponExchange pcx : platformAnalyze) {
//                Double rate = pcx.getTotalCustomer().doubleValue() / persons.doubleValue();
//                rate = getaDoubleFormat(rate);
//                pcx.setPersonRat(rate);
//                pcx.setRat(getaDoubleFormat(pcx.getTotalAmount().doubleValue() / amounts.doubleValue()));
//            }
            result.setPerson(platformAnalyze);
            result.setAmount(platformAnalyze);
            result.setTotalAmount(amounts);
            result.setTotalPerson(persons);
        }
        return result;
    }

    /**
     * 优惠券兑换频次人数分布情况
     */
    @Override
    public FrequencyAnalyzeVo getFrequencyAnalyze() {
        List<ExchangeFrequency> frequencyAnalyze = dailyAllMakeCardDao.getFrequencyAnalyze(DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN));
        FrequencyAnalyzeVo result = new FrequencyAnalyzeVo();
        result.setExchange(frequencyAnalyze);
        return result;
    }

    /**
     * 优惠券兑换次数商品排行榜
     */
    @Override
    public SkuAnalyzeVo getSkuAnalyze() {
        List<ExchangedGoodsRank> skuAnalyze = dailyAllMakeCardDao.getSkuAnalyze(DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN));
        SkuAnalyzeVo result = new SkuAnalyzeVo();
        result.setTop10(skuAnalyze);
        return result;
    }

    /**
     * 兑换金额
     */
    @Override
    public ExchangeAmountResultVo getExchangeAmount() {
        ExchangeAmountResultVo result = new ExchangeAmountResultVo();
        ExchangeAmountVo lastDay = getExchangAmountInfo(ExchangeAmountEnum.LASTDay);
        ExchangeAmountVo last7Day = getExchangAmountInfo(ExchangeAmountEnum.LAST7Day);
        ExchangeAmountVo last30Day = getExchangAmountInfo(ExchangeAmountEnum.LAST30Day);
        ExchangeAmountVo lastMonth = getExchangAmountInfo(ExchangeAmountEnum.LASTMONTH);
        result.setLastDay(lastDay);
        result.setLast7day(last7Day);
        result.setLast30day(last30Day);
        result.setLastMonth(lastMonth);
        return result;
    }

    /**
     * 激活金额
     */
    @Override
    public ExchangeAmountResultVo getActiveAmount() {
        ExchangeAmountResultVo result = new ExchangeAmountResultVo();
        ExchangeAmountVo lastDay = getActiveAmountInfo(ExchangeAmountEnum.LASTDay);
        ExchangeAmountVo last7Day = getActiveAmountInfo(ExchangeAmountEnum.LAST7Day);
        ExchangeAmountVo last30Day = getActiveAmountInfo(ExchangeAmountEnum.LAST30Day);
        ExchangeAmountVo lastMonth = getActiveAmountInfo(ExchangeAmountEnum.LASTMONTH);
        result.setLastDay(lastDay);
        result.setLast7day(last7Day);
        result.setLast30day(last30Day);
        result.setLastMonth(lastMonth);
        return result;
    }

    /**
     * 激活卡数
     */
    @Override
    public ActiveCardResultVo getActiveCard() {
        ActiveCardResultVo result = new ActiveCardResultVo();
        ActiveCardVo lastDay = getActiveNumInfo(ExchangeAmountEnum.LASTDay);
        ActiveCardVo last7Day = getActiveNumInfo(ExchangeAmountEnum.LAST7Day);
        ActiveCardVo last30Day = getActiveNumInfo(ExchangeAmountEnum.LAST30Day);
        ActiveCardVo lastMonth = getActiveNumInfo(ExchangeAmountEnum.LASTMONTH);
        result.setLastDay(lastDay);
        result.setLast7day(last7Day);
        result.setLast30day(last30Day);
        result.setLastMonth(lastMonth);
        return result;
    }

    /**
     * 定制卡排行
     */
    @Override
    public CustomCardTop10ResultVo getCustomCardTop10() {
        Date date = new Date();
        List<BusinessMakeAmountRank> createAmountTop10 = dailyAllMakeCardDao.getCreateAmountTop10(DateUtils.format(date, DateUtils.DATETIMEPATTERN));
        List<BusinessMakeAmountRank> acticeAmountTop10 = dailyAllMakeCardDao.getActiceAmountTop10(DateUtils.format(date, DateUtils.DATETIMEPATTERN));
        List<BusinessMakeAmountRank> excahngeAmountTop10 = dailyAllMakeCardDao.getExcahngeAmountTop10(DateUtils.format(date, DateUtils.DATETIMEPATTERN));
        List<BusinessMakeCardRank> createCardTop10 = dailyAllMakeCardDao.getCreateCardTop10(DateUtils.format(date, DateUtils.DATETIMEPATTERN));
        List<BusinessMakeCardRank> exchangeCardTop10 = dailyAllMakeCardDao.getExchangeCardTop10(DateUtils.format(date, DateUtils.DATETIMEPATTERN));
        CustomCardTop10ResultVo result = new CustomCardTop10ResultVo();
        result.setCreateAmountTop10(createAmountTop10);
        result.setActiveAmountTop10(acticeAmountTop10);
        result.setExchangeAmountTop10(excahngeAmountTop10);
        result.setCreateCardTop10(createCardTop10);
        result.setActiveCardTop10(exchangeCardTop10);
        return result;
    }

    /**
     * 获取某天的数据
     */
    private ExchangeAmountVo getExchangAmountInfo(ExchangeAmountEnum type) {
        Date today = new Date();
        ExchangeAmountVo lastDay = new ExchangeAmountVo();
        List<ExchangeCard> currentRound = null;
        List<ExchangeCard> lastRound = null;
        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            currentRound = dailyAllMakeCardDao.getExchangeCard(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
            lastRound = dailyAllMakeCardDao.getExchangeCard(DateUtils.format(DateUtils.addDateDays(today, -1), DateUtils.DATETIMEPATTERN));
            currentRound = dealHour(currentRound);
            lastRound = dealHour(lastRound);
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            currentRound = dailyAllMakeCardDao.get7ExchangeCard(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            currentRound = dailyAllMakeCardDao.get30ExchangeCard(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            currentRound = dailyAllMakeCardDao.getMonthExchangeCard(DateUtils.formatMonth(DateUtils.addDateMonths(today, -1)));
            lastRound = dailyAllMakeCardDao.getMonthExchangeCard(DateUtils.formatMonth(DateUtils.addDateMonths(today, -2)));
        }
        if (CollectionUtils.isEmpty(currentRound)) {
            currentRound = new ArrayList<ExchangeCard>();
        }
        if (CollectionUtils.isEmpty(lastRound)) {
            lastRound = new ArrayList<ExchangeCard>();
        }
        lastDay.setCurrentRound(currentRound);
        lastDay.setLastRound(lastRound);

        ExchangeCardTotalVo exchangeCardTotal = null;
        ExchangeCardTotalVo exchangeCardTotalLast = null;
        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            exchangeCardTotal = dailyAllMakeCardDao.getExchangeCardTotal(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
            exchangeCardTotalLast = dailyAllMakeCardDao.getExchangeCardTotal(DateUtils.format(DateUtils.addDateDays(today, -1), DateUtils.DATETIMEPATTERN));
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            exchangeCardTotal = dailyAllMakeCardDao.get7ExchangeCardTotal(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
            exchangeCardTotalLast = dailyAllMakeCardDao.getNext7ExchangeCardTotal(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            exchangeCardTotal = dailyAllMakeCardDao.get30ExchangeCardTotal(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
            exchangeCardTotalLast = dailyAllMakeCardDao.getNext30ExchangeCardTotal(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            Map map = getMonth();
            exchangeCardTotalLast = dailyAllMakeCardDao.get12MonthExchangeCardAvg(map);
        }

        setLastDay(CardEnum.EXCHANGE,type, lastDay, currentRound, lastRound, exchangeCardTotal, exchangeCardTotalLast);
        return lastDay;
    }

    /**
     * 填充没有数据的小时
     */
    private List<ExchangeCard>  dealHour(List<ExchangeCard> ecs){
        List<ExchangeCard> result = new ArrayList<ExchangeCard>(24);
        Map<String,BigDecimal> hourMap = new TreeMap();
        String hkey = null;
        for(int i=0;i < 24;i++){
            if(i < 10){
                hkey = "0"+i+":00";
            }else {
                hkey = i+":00";
            }
            hourMap.put(hkey,new BigDecimal(0));
        }
        for(ExchangeCard ec :ecs){
            if(ec.getTheTime().length() == 4){  // 统一时间格式
                hourMap.put("0"+ec.getTheTime(),ec.getTotalCount());
            }else {
                hourMap.put(ec.getTheTime(),ec.getTotalCount());
            }
        }
        for(String key : hourMap.keySet() ){
            ExchangeCard bean = new ExchangeCard();
            bean.setTheTime(key);
            bean.setTotalCount(hourMap.get(key));
            result.add(bean);
        }
        return result;
    }

    /**
     * 激活卡-填充没有数据的小时
     */
    private List<ActiveCard>  dealHourActiveCard(List<ActiveCard> ecs){
        List<ActiveCard> result = new ArrayList<ActiveCard>(24);
        Map<String,Long> hourMap = new TreeMap();
        String hkey = null;
        for(int i=0;i < 24;i++){
            if(i < 10){
                hkey = "0"+i+":00";
            }else {
                hkey = i+":00";
            }
            hourMap.put(hkey,0L);
        }
        for(ActiveCard ec :ecs){
            if(ec.getTheTime().length() == 4){  // 统一时间格式
                hourMap.put("0"+ec.getTheTime(),ec.getTotalCount());
            }else {
                hourMap.put(ec.getTheTime(),ec.getTotalCount());
            }
        }
        for(String key : hourMap.keySet() ){
            ActiveCard bean = new ActiveCard();
            bean.setTheTime(key);
            bean.setTotalCount(hourMap.get(key));
            result.add(bean);
        }
        return result;
    }

    /**
     * 激活金额
     */
    private ExchangeAmountVo getActiveAmountInfo(ExchangeAmountEnum type) {
        Date today = new Date();
        ExchangeAmountVo lastDay = new ExchangeAmountVo();
        List<ExchangeCard> currentRound = null;
        List<ExchangeCard> lastRound = null;
        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            currentRound = dailyAllMakeCardDao.getActiveCard(DateUtils.format(DateUtils.addDateDays(today, -1), DateUtils.DATETIMEPATTERN));
            lastRound = dailyAllMakeCardDao.getActiveCard(DateUtils.format(DateUtils.addDateDays(today, -2), DateUtils.DATETIMEPATTERN));
            currentRound = dealHour(currentRound);
            lastRound = dealHour(lastRound);
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            currentRound = dailyAllMakeCardDao.get7ActiveCard(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            currentRound = dailyAllMakeCardDao.get30ActiveCard(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            currentRound = dailyAllMakeCardDao.getMonthActiveCard(DateUtils.formatMonth(DateUtils.addDateMonths(today, -1)));
            lastRound = dailyAllMakeCardDao.getMonthActiveCard(DateUtils.formatMonth(DateUtils.addDateMonths(today, -2)));
        }
        if (CollectionUtils.isEmpty(currentRound)) {
            currentRound = new ArrayList<ExchangeCard>();
        }
        if (CollectionUtils.isEmpty(lastRound)) {
            lastRound = new ArrayList<ExchangeCard>();
        }
        lastDay.setCurrentRound(currentRound);
        lastDay.setLastRound(lastRound);

        ExchangeCardTotalVo exchangeCardTotal = null;
        ExchangeCardTotalVo exchangeCardTotalLast = null;
        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            exchangeCardTotal = dailyAllMakeCardDao.getActiveCardTotal(DateUtils.format(DateUtils.addDateDays(today, -1), DateUtils.DATETIMEPATTERN));
            exchangeCardTotalLast = dailyAllMakeCardDao.getActiveCardTotal(DateUtils.format(DateUtils.addDateDays(today, -2), DateUtils.DATETIMEPATTERN));
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            exchangeCardTotal = dailyAllMakeCardDao.get7ActiveCardTotal(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
            exchangeCardTotalLast = dailyAllMakeCardDao.getNext7ActiveCardTotal(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            exchangeCardTotal = dailyAllMakeCardDao.get30ActiveCardTotal(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
            exchangeCardTotalLast = dailyAllMakeCardDao.getNext30ActiveCardTotal(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            Map map = getMonth();
            exchangeCardTotalLast = dailyAllMakeCardDao.get12MonthActiveCardAvg(map);
        }

        setLastDay(CardEnum.ACTIVE,type, lastDay, currentRound, lastRound, exchangeCardTotal, exchangeCardTotalLast);
        return lastDay;
    }


    /**
     * 激活卡数
     */
    private ActiveCardVo getActiveNumInfo(ExchangeAmountEnum type) {
        Date today = new Date();
        ActiveCardVo lastDay = new ActiveCardVo();
        List<ActiveCard> currentRound = null;
        List<ActiveCard> lastRound = null;
        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            currentRound = dailyAllMakeCardDao.getActiveCardNum(DateUtils.format(DateUtils.addDateDays(today, -1), DateUtils.DATETIMEPATTERN));
            lastRound = dailyAllMakeCardDao.getActiveCardNum(DateUtils.format(DateUtils.addDateDays(today, -2), DateUtils.DATETIMEPATTERN));
            currentRound = dealHourActiveCard(currentRound);
            lastRound = dealHourActiveCard(lastRound);
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            currentRound = dailyAllMakeCardDao.get7ActiveCardNum(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            currentRound = dailyAllMakeCardDao.get30ActiveCardNum(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            currentRound = dailyAllMakeCardDao.getMonthActiveCardNum(DateUtils.formatMonth(DateUtils.addDateMonths(today, -1)));
            lastRound = dailyAllMakeCardDao.getMonthActiveCardNum(DateUtils.formatMonth(DateUtils.addDateMonths(today, -2)));
        }
        if (CollectionUtils.isEmpty(currentRound)) {
            currentRound = new ArrayList<ActiveCard>();
        }
        if (CollectionUtils.isEmpty(lastRound)) {
            lastRound = new ArrayList<ActiveCard>();
        }
        lastDay.setCurrentRound(currentRound);
        lastDay.setLastRound(lastRound);

        ActiveCardTotalVo exchangeCardTotal = null;
        ActiveCardTotalVo exchangeCardTotalLast = null;
        if (ExchangeAmountEnum.LASTDay.equals(type)) {
            exchangeCardTotal = dailyAllMakeCardDao.getActiveCardTotalNum(DateUtils.format(DateUtils.addDateDays(today, -1), DateUtils.DATETIMEPATTERN));
            exchangeCardTotalLast = dailyAllMakeCardDao.getActiveCardTotalNum(DateUtils.format(DateUtils.addDateDays(today, -2), DateUtils.DATETIMEPATTERN));
        } else if (ExchangeAmountEnum.LAST7Day.equals(type)) {
            exchangeCardTotal = dailyAllMakeCardDao.get7ActiveCardTotalNum(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
            exchangeCardTotalLast = dailyAllMakeCardDao.getNext7ActiveCardTotalNum(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
        } else if (ExchangeAmountEnum.LAST30Day.equals(type)) {
            exchangeCardTotal = dailyAllMakeCardDao.get30ActiveCardTotalNum(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
            exchangeCardTotalLast = dailyAllMakeCardDao.getNext30ActiveCardTotalNum(DateUtils.format(today, DateUtils.DATETIMEPATTERN));
        } else if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            Map map = getMonth();
            exchangeCardTotalLast = dailyAllMakeCardDao.get12MonthActiveCardAvgNum(map);
        }

        if (exchangeCardTotal == null) {
            exchangeCardTotal = new ActiveCardTotalVo();
        }
        if (exchangeCardTotalLast == null) {
            exchangeCardTotalLast = new ActiveCardTotalVo();
        }
        if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            if (CollectionUtils.isNotEmpty(currentRound)) {
                lastDay.setActivieTotal(currentRound.get(0).getTotalCount());  // 本月总数
            }
            if (CollectionUtils.isNotEmpty(lastRound)) {
                lastDay.setLastActivieTotal(lastRound.get(0).getTotalCount());  // 上月总数
            }
            lastDay.setAvg(exchangeCardTotalLast.getAvgAmount());
            lastDay.setDateBeginEnd(DateUtils.formatMonth(DateUtils.addDateMonths(new Date(), -1))
                    + "--"+ DateUtils.formatMonth(DateUtils.addDateMonths(new Date(), -12)));
            dealRat(lastDay);

            Map map = getMonth();
            List<ActiveCard> monthActiveCardNum = dailyAllMakeCardDao.get12MonthActiveCardNum(map);
            lastDay.setCurrentRound(monthActiveCardNum);
        } else {
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
     * 处理环比值
     *
     */
    private void dealRat(ActiveCardVo lastDay) {
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
     * 设置 近一年的开始结束月份
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
     * 设置对比信息
     *
     * @param type
     * @param lastDay
     * @param currentRound
     * @param lastRound
     * @param exchangeCardTotal
     * @param exchangeCardTotalLast
     */
    private void setLastDay(CardEnum cardType,ExchangeAmountEnum type, ExchangeAmountVo lastDay, List<ExchangeCard> currentRound, List<ExchangeCard> lastRound, ExchangeCardTotalVo exchangeCardTotal, ExchangeCardTotalVo exchangeCardTotalLast) {
        if (exchangeCardTotal == null) {
            exchangeCardTotal = new ExchangeCardTotalVo();
        }
        if (exchangeCardTotalLast == null) {
            exchangeCardTotalLast = new ExchangeCardTotalVo();
        }
        if (ExchangeAmountEnum.LASTMONTH.equals(type)) {
            if (CollectionUtils.isNotEmpty(currentRound)) {
                lastDay.setActivieTotal(currentRound.get(0).getTotalCount());  // 本月总数
            }
            if (CollectionUtils.isNotEmpty(lastRound)) {
                lastDay.setLastActivieTotal(lastRound.get(0).getTotalCount());  // 上月总数
            }
            lastDay.setAvg(exchangeCardTotalLast.getAvgAmount());
            lastDay.setDateBeginEnd(DateUtils.formatMonth(DateUtils.addDateMonths(new Date(), -1))
                    + "--"+ DateUtils.formatMonth(DateUtils.addDateMonths(new Date(), -12)));
            dealAmountRat(lastDay);
            Map map = getMonth();
            if (CardEnum.ACTIVE.equals(cardType)) {
                lastDay.setCurrentRound(dailyAllMakeCardDao.get12MonthActiveCard(map));
            }else {
                lastDay.setCurrentRound(dailyAllMakeCardDao.get12MonthExchangeCard(map));
            }
        } else {
            lastDay.setActivieTotal(exchangeCardTotal.getActivieTotal());
            lastDay.setAvg(exchangeCardTotal.getAvgAmount());
            lastDay.setDateBeginEnd(exchangeCardTotal.getCreateTime());
            lastDay.setLastActivieTotal(exchangeCardTotalLast.getActivieTotal());
            dealAmountRat(lastDay);
        }
        // 处理0值
        if(lastDay.getActivieTotal() == null){
            lastDay.setActivieTotal(new BigDecimal(0));
        }
        if(lastDay.getLastActivieTotal() == null){
            lastDay.setLastActivieTotal(new BigDecimal(0));
        }
    }

    /**
     *  金额-处理 环比
     *
     */
    private void dealAmountRat(ExchangeAmountVo lastDay) {
        if (lastDay.getActivieTotal() != null) {
            if (lastDay.getLastActivieTotal() != null) {
                Double rat = getaDoubleFormat(lastDay.getActivieTotal().subtract(lastDay.getLastActivieTotal()).divide(lastDay.getActivieTotal(), 4).doubleValue());
                lastDay.setRat(getRate(rat));
            } else {
                lastDay.setRat("100%");
            }
        } else {
            lastDay.setRat("0%");
        }
    }

    /**
     * 获取4位小数（四舍五入）
     *
     */
    private Double getaDoubleFormat(Double rate) {
        BigDecimal b = new BigDecimal(rate);
        return b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 数字转百分率 0.56966 --》 56.97%
     *
     * @return
     */
    private String getRate(Double num){
        NumberFormat numberFormat  = NumberFormat.getPercentInstance();
        numberFormat.setMinimumFractionDigits(2); //保留到小数点后2位,不设置或者设置为0表示不保留小数
        return numberFormat.format(num);
    }
}
