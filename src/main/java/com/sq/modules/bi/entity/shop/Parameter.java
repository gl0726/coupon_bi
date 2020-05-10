package com.sq.modules.bi.entity.shop;

import java.io.Serializable;

/**
 * @author gl
 * @create 2020-04-28 10:11
 */
public class Parameter implements Serializable {
    //店铺唯一编码
    private String code;
    //店铺唯一ID
    private int id;
    //年year，月month，日day，总all，周week
    private String date;
    //月开始时间 格式： yyyy-MM
    private String startMohth;
    //月结束时间
    private String endMohth;
    //日开始时间 格式： yyyy-MM-dd
    private String startDay;
    //日结束时间
    private String endDay;
    //年
    private String year;
    //月
    private String month;
    //周
    private String week;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public void setId(int id) {
        if (id == 0)
            this.id = -1;
        else
            this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if(code == null || "".equals(code))
            this.code = "-1";
        else
            this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartMohth() {
        return startMohth;
    }

    public void setStartMohth(String startMohth) {
        this.startMohth = startMohth;
    }

    public String getEndMohth() {
        return endMohth;
    }

    public void setEndMohth(String endMohth) {
        this.endMohth = endMohth;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }
}
