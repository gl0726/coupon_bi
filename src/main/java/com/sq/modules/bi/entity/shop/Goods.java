package com.sq.modules.bi.entity.shop;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gl
 * @create 2020-04-28 15:19
 * 商品排行模型
 */
@Data
public class Goods implements Serializable {
    //排行
    private int rowNumbers;
    //商品图片
    private String pic;
    //货号
    private String mpCode;
    //商品名称
    private String proName;
    //产品金额
    private String price;
    //下单量
    private String counts;


}
