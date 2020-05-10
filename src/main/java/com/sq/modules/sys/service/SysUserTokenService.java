/**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 *
 * https://www.sq.com
 *
 * 版权所有，侵权必究！
 */

package com.sq.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.modules.sys.entity.SysUserTokenEntity;
import com.sq.common.utils.R;

/**
 * 用户Token
 *
 * @author Mark Wenjunchi
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);

}
