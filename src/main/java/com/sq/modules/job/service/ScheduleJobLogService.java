/**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 *
 * https://www.sq.com
 *
 * 版权所有，侵权必究！
 */

package com.sq.modules.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.modules.job.entity.ScheduleJobLogEntity;
import com.sq.common.utils.PageUtils;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author Mark Wenjunchi
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

	PageUtils queryPage(Map<String, Object> params);
	
}
