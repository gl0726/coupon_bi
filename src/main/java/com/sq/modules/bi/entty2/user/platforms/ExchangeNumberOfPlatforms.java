package com.sq.modules.bi.entty2.user.platforms;

import com.sq.modules.bi.entty2.user.platforms.PlatformsExchangeNumber;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * BI2.0 用户-各平台兑换、支付趋势分析-兑换人数/支付人数
 * </p>
 *
 * @author gl
 * @since 2018-08-12
 */
@Data
public class ExchangeNumberOfPlatforms implements Serializable {
    private static final long serialVersionUID = 1L;

    //昨日
    private PlatformsExchangeNumber lastDay;
    //近七天
    private PlatformsExchangeNumber last7day;
    //近30天
    private PlatformsExchangeNumber last30day;
    //月
    private PlatformsExchangeNumber lastMonth;


}
