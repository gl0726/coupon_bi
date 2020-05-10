/**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 *
 * https://www.sq.com
 *
 * 版权所有，侵权必究！
 */

package com.sq.modules.oss.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sq.modules.oss.entity.SysOssEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件上传
 *
 * @author Mark Wenjunchi
 */
@Mapper
public interface SysOssDao extends BaseMapper<SysOssEntity> {
	
}
