/**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 *
 * www.sqqmall.com
 *
 * 版权所有，侵权必究！
 */

package com.sq.modules.oss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.modules.oss.entity.SysOssEntity;
import com.sq.common.utils.PageUtils;

import java.util.Map;

/**
 * 文件上传
 *
 * @author Mark Wenjunchi
 */
public interface SysOssService extends IService<SysOssEntity> {

	PageUtils queryPage(Map<String, Object> params);
}
