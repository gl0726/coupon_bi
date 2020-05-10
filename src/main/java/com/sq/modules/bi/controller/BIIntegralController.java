package com.sq.modules.bi.controller;

import com.sq.common.utils.R;
import com.sq.modules.bi.entity.shop.*;
import com.sq.modules.bi.entity.vo.BasicInfoVo;
import com.sq.modules.bi.service.BIIntegralService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import java.util.List;

/**
 * 店铺积分BI数据统计
 *
 * @author gl
 * @create 2020-04-01 9:40
 */

@RestController
@RequestMapping("/integral")
public class BIIntegralController {

    @Autowired
    private BIIntegralService biIntegralService;

    /**
     * 首页昨日交易概况
     */
    @ApiOperation(value = "首页昨日交易概况", notes = "首页昨日交易概况")
    @ApiImplicitParam(name = "parameter", value = "首页昨日交易概况",
            required = true, dataType = "Parameter")
    @RequestMapping(value = "/yesterdayTrading", method = RequestMethod.POST)
    public R yesterdayTrading(@RequestBody Parameter parameter) {
        Transaction transaction = biIntegralService.getYesterdayTrading(parameter);
        return R.ok().put("transaction", transaction);
    }

    /**
     * 数据统计订单-流水：年，月，日，统计
     */
    @ApiOperation(value = "数据统计订单", notes = "数据统计订单")
    @ApiImplicitParam(name = "parameter", value = "数据统计订单",
            required = true, dataType = "Parameter")
    @RequestMapping(value = "/statistics", method = RequestMethod.POST)
    public R statistics(@RequestBody Parameter parameter) {
        Statistics statistics = biIntegralService.getStatistics(parameter);
        return R.ok().put("statistics", statistics);
    }

    /**
     * 商品销售排行前20：总，年，月，周统计
     */
    @ApiOperation(value = "商品销售排行前20", notes = "商品销售排行前20")
    @ApiImplicitParam(name = "parameter", value = "商品销售排行前20",
            required = true, dataType = "Parameter")
    @RequestMapping(value = "/commoditySalesRanking", method = RequestMethod.POST)
    public R commoditySalesRanking(@RequestBody Parameter parameter) {
        List<Goods> goods = biIntegralService.getCommoditySalesRanking(parameter);
        return R.ok().put("goods", goods);
    }

    /**
     * 用户统计：年，月，日，统计
     */
    @ApiOperation(value = "用户统计", notes = "用户统计")
    @ApiImplicitParam(name = "parameter", value = "用户统计",
            required = true, dataType = "Parameter")
    @RequestMapping(value = "/userStatistics", method = RequestMethod.POST)
    public R userStatistics(@RequestBody Parameter parameter) {
        UserStatistics users = biIntegralService.getUserStatistics(parameter);
        return R.ok().put("users", users);
    }

    /**
     * 销售种类统计，按日期，近7天，月度统计
     */
    @ApiOperation(value = "销售种类统计", notes = "销售种类统计")
    @ApiImplicitParam(name = "parameterTwo", value = "销售种类统计",
            required = true, dataType = "ParameterTwo")
    @RequestMapping(value = "/salesType", method = RequestMethod.POST)
    public R salesType(@RequestBody ParameterTwo parameter) {
        List<SalesTypeStatistics> sales = biIntegralService.getSalesTypeStatistics(parameter);
        return R.ok().put("sales", sales);
    }

    /**
     * 用户统计：下单用户排行
     */
    @ApiOperation(value = "下单用户排行", notes = "下单用户排行")
    @ApiImplicitParam(name = "parameterTwo", value = "下单用户排行",
            required = true, dataType = "ParameterTwo")
    @RequestMapping(value = "/userOrderStatistics", method = RequestMethod.POST)
    public R userOrderStatistics(@RequestBody ParameterTwo parameter) {
        List<UserOrderStatistics> UserOrders = biIntegralService.getUserOrderStatistics(parameter);
        return R.ok().put("UserOrders", UserOrders);
    }


}
