package com.sq.modules.customer.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AccountConsumeVo implements Serializable {
    private static final long serialVersionUID = 3568211203498085114L;
    private String dissipateDate;
    private List<ConsumeDetailVo> consumeDetailVos;
}
