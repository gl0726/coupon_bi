package com.sq.modules.bi.service.impl;

import com.sq.common.utils.DateUtils;
import com.sq.common.utils.R;
import com.sq.modules.bi.dao.BIIntegralDao;
import com.sq.modules.bi.entity.shop.*;
import com.sq.modules.bi.service.BIIntegralService;
import com.sq.modules.oss.entity.SysOssEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author gl
 * @create 2020-04-01 9:42
 */
@Service
public class BIIntegralServiceImpl implements BIIntegralService {

    @Autowired
    private BIIntegralDao bIIntegralDao;

    @Override
    public Transaction getYesterdayTrading(Parameter parameter) {
        return bIIntegralDao.getYesterdayTrading(parameter);
    }

    @Override
    public Statistics getStatistics(Parameter parameter) {
        Statistics statistics = new Statistics();
        if("year".equals(parameter.getDate())) {
            List<OrderStatistics> order = bIIntegralDao.getOrderDataYear(parameter);
            List<OrderStatistics> pipeline = bIIntegralDao.getPipelineDataYear(parameter);
            statistics.setOrder(order);
            statistics.setRunningWater(pipeline);
        } else if ("month".equals(parameter.getDate())) {
            String startMonth = DateUtils.formatMonth(DateUtils.addDateMonths(new Date(), -11));
            String endMonth = DateUtils.format(new Date(), DateUtils.DATE_PATTERN_MONTH);
            System.out.println("startMonth = "+startMonth);
            System.out.println("endMonth = "+endMonth);
            parameter.setStartMohth(startMonth);
            parameter.setEndMohth(endMonth);
            List<OrderStatistics> order = bIIntegralDao.getOrderDataMonth(parameter);
            List<OrderStatistics> pipeline = bIIntegralDao.getPipelineDataMonth(parameter);
            statistics.setOrder(order);
            statistics.setRunningWater(pipeline);
        } else if ("day".equals(parameter.getDate())) {
            String startDay = DateUtils.format(DateUtils.addDateDays(new Date(), -30));
            String endDay = DateUtils.format(DateUtils.addDateDays(new Date(), -1));
            System.out.println("startDay = "+startDay);
            System.out.println("endDay = "+endDay);
            parameter.setStartDay(startDay);
            parameter.setEndDay(endDay);
            List<OrderStatistics> order = bIIntegralDao.getOrderDataDay(parameter);
            List<OrderStatistics> pipeline = bIIntegralDao.getPipelineDataDay(parameter);
            statistics.setOrder(order);
            statistics.setRunningWater(pipeline);
        }
        return statistics;
    }

    @Override
    public List<Goods> getCommoditySalesRanking(Parameter parameter) {
        if("all".equals(parameter.getDate())) {
            return bIIntegralDao.getCommoditySalesRankingSum(parameter);
        } else if("year".equals(parameter.getDate())) {
            parameter.setYear(DateUtils.format(new Date(), DateUtils.DATE_PATTERN_YEAR));
            System.out.println(parameter.getYear());
            return bIIntegralDao.getCommoditySalesRankingYear(parameter);
        } else if("month".equals(parameter.getDate())) {
            parameter.setMonth(DateUtils.format(new Date(), DateUtils.DATE_PATTERN_MONTH));
            System.out.println(parameter.getMonth());
            return bIIntegralDao.getCommoditySalesRankingMonth(parameter);
        } else if("week".equals(parameter.getDate())) {
            //获取上周周一日期
            parameter.setWeek(DateUtils.format(DateUtils.geLastWeekMonday(new Date()), DateUtils.DATE_PATTERN_DAY));
            System.out.println(parameter.getWeek());
            return bIIntegralDao.getCommoditySalesRankingWeek(parameter);
        } else {
            return new ArrayList<Goods>();
        }
    }

    //将获取集合转换成返回类型，这样只需访问一次数据库即可获得三个集合的数值
    public UserStatistics getUserStatisticsCollect(List<UserCollect> users){
        UserStatistics userStatistics = new UserStatistics();
        ArrayList<OrderStatistics> regUserNum = new ArrayList<>();
        ArrayList<OrderStatistics> orderNserNum = new ArrayList<>();
        ArrayList<OrderStatistics> allUserNum = new ArrayList<>();
        for (UserCollect user : users) {
            regUserNum.add(new User(user.getTheDate(), user.getRegUserNum()));
            orderNserNum.add(new User(user.getTheDate(), user.getOrderUserNum()));
            allUserNum.add(new User(user.getTheDate(), user.getAllUserNum()));
        }
        userStatistics.setRegUserNum(regUserNum);
        userStatistics.setOrderNserNum(orderNserNum);
        userStatistics.setAllUserNum(allUserNum);
        return userStatistics;
    }


    @Override
    public UserStatistics getUserStatistics(Parameter parameter) {

        if("year".equals(parameter.getDate())) {
            parameter.setDate("年");
            List<UserCollect> users = bIIntegralDao.getUserStatisticsYear(parameter);
            return getUserStatisticsCollect(users);
        } else if ("month".equals(parameter.getDate())) {
            parameter.setDate("月");
            String startMonth = DateUtils.formatMonth(DateUtils.addDateMonths(new Date(), -11));
            String endMonth = DateUtils.format(new Date(), DateUtils.DATE_PATTERN_DAY);
            parameter.setStartMohth(startMonth+"-01");
            parameter.setEndMohth(endMonth);
            System.out.println("startMonth = "+parameter.getStartMohth());
            System.out.println("endMonth = "+parameter.getEndMohth());
            List<UserCollect> users = bIIntegralDao.getUserStatisticsMonth(parameter);
            return getUserStatisticsCollect(users);
        } else if ("day".equals(parameter.getDate())) {
            parameter.setDate("日");
            String startDay = DateUtils.format(DateUtils.addDateDays(new Date(), -30));
            String endDay = DateUtils.format(DateUtils.addDateDays(new Date(), -1));
            System.out.println("startDay = "+startDay);
            System.out.println("endDay = "+endDay);
            parameter.setStartDay(startDay);
            parameter.setEndDay(endDay);
            List<UserCollect> users = bIIntegralDao.getUserStatisticsDay(parameter);
            return getUserStatisticsCollect(users);
        }
        return new UserStatistics();
    }

    //销售种类占比
    @Override
    public List<SalesTypeStatistics> getSalesTypeStatistics(ParameterTwo parameter) {
        //当前时间
        String endDay = DateUtils.format(new Date());
        if("day".equals(parameter.getDate())) {
            String startDay = DateUtils.format(DateUtils.addDateDays(new Date(), -7));
            System.out.println("startDay : " + startDay);
            System.out.println("endDay : " + endDay);
            parameter.setStart(startDay);
            parameter.setEnd(endDay);
            return setRanking(bIIntegralDao.getSalesTypeStatistics(parameter));
        } else if("month".equals(parameter.getDate())) {
            String startDay = DateUtils.format(DateUtils.addDateDays(new Date(), -30));
            System.out.println("startDay : " + startDay);
            System.out.println("endDay : " + endDay);
            parameter.setStart(startDay);
            parameter.setEnd(endDay);
            return setRanking(bIIntegralDao.getSalesTypeStatistics(parameter));
        } else if (StringUtils.isNotBlank(parameter.getStart())) {//如果结束时间为空则按照当前时间作为结束时间
            if(!StringUtils.isNotBlank(parameter.getEnd())) {
                //结束时间为空，将当前时间注入
                parameter.setEnd(endDay);
            }
            System.out.println("startDay : " + parameter.getStart());
            System.out.println("endDay : " + parameter.getEnd());
            return setRanking(bIIntegralDao.getSalesTypeStatistics(parameter));
        }
        return new ArrayList<SalesTypeStatistics>();
    }

    @Override
    //用户下单排行
    public List<UserOrderStatistics> getUserOrderStatistics(ParameterTwo parameter) {
        return setRankingTwo(bIIntegralDao.getUserOrderStatistics(parameter));
    }

    //公共方法，植入排行列
    public List<SalesTypeStatistics> setRanking(List<SalesTypeStatistics> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setRanking(i+1);
        }
        return  list;
    }

    public List<UserOrderStatistics> setRankingTwo(List<UserOrderStatistics> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setRanking(i+1);
        }
        return  list;
    }



}
