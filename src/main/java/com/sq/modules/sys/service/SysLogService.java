/**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 *
 * www.sqqmall.com
 *
 * 版权所有，侵权必究！
 */

package com.sq.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.modules.sys.entity.SysLogEntity;
import com.sq.common.utils.PageUtils;

import java.util.Map;


/**
 * 系统日志
 *
 * @author Mark Wenjunchi
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
