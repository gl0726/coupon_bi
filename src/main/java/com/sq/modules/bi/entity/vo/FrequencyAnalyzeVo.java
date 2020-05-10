package com.sq.modules.bi.entity.vo;

import com.sq.modules.bi.entity.ExchangeFrequency;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class FrequencyAnalyzeVo implements Serializable {

    private static final long serialVersionUID = 3568211203498085115L;

    private List<ExchangeFrequency> exchange;


}
