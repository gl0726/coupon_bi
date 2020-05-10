package com.sq.modules.customer.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ConsumeDetailVo implements Serializable {
    private static final long serialVersionUID = 2801839159071337798L;
    private BigDecimal deductionAmount;
    private String deduction;
    private String title;
}
