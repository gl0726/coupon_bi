/**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 *
 * www.sqqmall.com
 *
 * 版权所有，侵权必究！
 */

package com.sq.modules.sys.service;

import com.sq.modules.sys.entity.SysUserEntity;
import com.sq.modules.sys.entity.SysUserTokenEntity;

import java.util.Set;

/**
 * shiro相关接口
 *
 * @author Mark Wenjunchi
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUserEntity queryUser(Long userId);
}
