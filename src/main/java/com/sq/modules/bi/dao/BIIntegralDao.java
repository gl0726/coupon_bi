package com.sq.modules.bi.dao;

import com.sq.modules.bi.entity.shop.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author gl
 * @create 2020-04-01 9:43
 */
@Mapper
public interface BIIntegralDao {

    Transaction getYesterdayTrading(Parameter parameter);

    List<OrderStatistics> getOrderDataYear(Parameter parameter);

    List<OrderStatistics> getPipelineDataYear(Parameter parameter);

    List<OrderStatistics> getOrderDataMonth(Parameter parameter);

    List<OrderStatistics> getPipelineDataMonth(Parameter parameter);

    List<OrderStatistics> getOrderDataDay(Parameter parameter);

    List<OrderStatistics> getPipelineDataDay(Parameter parameter);

    List<Goods> getCommoditySalesRankingSum(Parameter parameter);

    List<Goods> getCommoditySalesRankingYear(Parameter parameter);

    List<Goods> getCommoditySalesRankingMonth(Parameter parameter);

    List<Goods> getCommoditySalesRankingWeek(Parameter parameter);

    List<UserCollect> getUserStatisticsYear(Parameter parameter);

    List<UserCollect> getUserStatisticsMonth(Parameter parameter);

    List<UserCollect> getUserStatisticsDay(Parameter parameter);

    List<SalesTypeStatistics> getSalesTypeStatistics(ParameterTwo parameter);

    List<UserOrderStatistics> getUserOrderStatistics(ParameterTwo parameter);
}
