/**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 *
 * https://www.sq.com
 *
 * 版权所有，侵权必究！
 */

package com.sq.modules.app.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.modules.app.dao.UserDao;
import com.sq.modules.app.entity.UserEntity;
import com.sq.modules.app.form.LoginForm;
import com.sq.modules.app.service.UserService;
import com.sq.common.exception.RRException;
import com.sq.common.validator.Assert;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

	@Override
	public UserEntity queryByuserName(String username) {
		return baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("username", username));
	}

	@Override
	public long login(LoginForm form) {
		UserEntity user = queryByuserName(form.getUsername());
		Assert.isNull(user, "用户名或者密码错误");

		//密码错误
		if(!user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword()))){
			throw new RRException("用户名或者密码错误");
		}

		return user.getUserId();
	}
}
