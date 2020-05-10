/**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 *
 * https://www.sq.com
 *
 * 版权所有，侵权必究！
 */

package com.sq.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.modules.sys.entity.SysRoleEntity;
import com.sq.common.utils.PageUtils;

import java.util.List;
import java.util.Map;


/**
 * 角色
 *
 * @author Mark Wenjunchi
 */
public interface SysRoleService extends IService<SysRoleEntity> {

	PageUtils queryPage(Map<String, Object> params);

	void saveRole(SysRoleEntity role);

	void update(SysRoleEntity role);

	void deleteBatch(Long[] roleIds);

	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
