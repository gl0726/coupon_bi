package com.sq.service;

import com.sq.common.utils.DateUtils;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Test {

    public static void main(String[] args){
        /*Date date1 = new Date();
        System.out.println(DateUtils.formatMonth(DateUtils.addDateMonths(date1, -12)));
        System.out.println(DateUtils.formatMonth(DateUtils.addDateMonths(date1, -1)));
        String date = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN);
        System.out.println(date+"-----");
        System.out.println(DateUtils.format(DateUtils.addDateDays(new Date(), 1), DateUtils.DATE_PATTERN));*/
        //TreeMap能够把它保存的记录根据键排序, 这点可以好好利用
        Map<String, Integer> hourMap = new TreeMap();
        hourMap.put("2020-01",0);
        hourMap.put("2019-11",0);
        hourMap.put("2019-12",0);
        System.out.println("----------------------------"+ hourMap+"========================"+hourMap.keySet());

    }
}
