package com.sq.modules.bi.entty2.Ranking;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品排行返回结果的父类
 * @author gl
 * @since 2018-08-08
 */
@Data
public class Ranking implements Serializable {
    private static final long serialVersionUID = 1L;
    //排名
    private String rowNum;
    //商品名称
    private String commodityName;
    //平台
    private String deduction;
}
