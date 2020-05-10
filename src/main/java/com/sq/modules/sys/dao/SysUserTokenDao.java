/**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 *
 * www.sqqmall.com
 *
 * 版权所有，侵权必究！
 */

package com.sq.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sq.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 *
 * @author Mark Wenjunchi
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserTokenEntity> {

    SysUserTokenEntity queryByToken(String token);
	
}
