package com.sq.modules.bi.entty2.Ranking;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 各大平台父类
 * @author gl
 * @since 2018-08-08
 */
@Data
public class Platform implements Serializable {
    private static final long serialVersionUID = 1L;
    //昨日
    private List<Ranking> lastDay;
    //近七天
    private List<Ranking> last7day;
    //近30天
    private List<Ranking> last30day;
    //月
    private List<Ranking> lastMonth;
}
