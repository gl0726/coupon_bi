package com.sq.modules.bi.service.impl;

import com.sq.common.utils.DateUtils;
import com.sq.modules.bi.entty2.vo.TrendAnalysisTwo;

import java.text.SimpleDateFormat;
import java.util.*;

public class Test {

    public static void main(String[] args) throws Exception {
        //System.out.println("2019-09-09".substring(5));


        //时间类
        /*Calendar theCa = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();

        Map<String, Integer> hourMap = new TreeMap();

        for(int i = -31; i <= -1; i++) {
            theCa.setTime(currentDate);
            theCa.add(theCa.DATE, i);
            Date end2 = theCa.getTime();
            String endDate2 = sdf.format(end2);
            hourMap.put(endDate2.substring(5), 0);
        }

        System.out.println(hourMap);*/


        /*SimpleDateFormat sdf2 = new SimpleDateFormat(DateUtils.DATETIMEPATTERN);
        SimpleDateFormat sdf3 = new SimpleDateFormat(DateUtils.DATE_PATTERN_MONTH_DAY);

        Date parse = sdf2.parse("20190904");
        Date date = DateUtils.addDateDays(parse, -1);
        String format = sdf3.format(date);
        System.out.println(format);*/

        System.out.println(DateUtils.format(DateUtils.geLastWeekMonday(new Date()), DateUtils.DATE_PATTERN_DAY));

    }
}
