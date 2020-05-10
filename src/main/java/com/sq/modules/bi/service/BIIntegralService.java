package com.sq.modules.bi.service;

import com.sq.modules.bi.entity.shop.*;

import java.util.List;

/**
 * @author gl
 * @create 2020-04-01 9:42
 */
public interface BIIntegralService {


    Transaction getYesterdayTrading(Parameter parameter);

    Statistics getStatistics(Parameter parameter);

    List<Goods> getCommoditySalesRanking(Parameter parameter);

    UserStatistics getUserStatistics(Parameter parameter);

    List<SalesTypeStatistics> getSalesTypeStatistics(ParameterTwo parameter);

    List<UserOrderStatistics> getUserOrderStatistics(ParameterTwo parameter);
}
