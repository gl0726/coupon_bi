package com.sq.modules.bi.entty2.user;


import com.sq.modules.bi.entty2.user.RegisteredUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * BI2.0 用户-注册用户数模型类
 * </p>
 *
 * @author gl
 * @since 2018-08-09
 */
@Data
public class NumberOfRegisteredUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    //昨日
    private RegisteredUser lastDay;
    //近七天
    private RegisteredUser last7day;
    //近30天
    private RegisteredUser last30day;
    //月
    private RegisteredUser lastMonth;
    //昨日和上一日的平均数
    private List<ActiveUserTwo> avg;

}
