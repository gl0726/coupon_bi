/**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 * <p>
 * www.sqqmall.com
 * <p>
 * 版权所有，侵权必究！
 */

package com.sq.modules.sys.controller;

import com.sq.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 *
 * @author Mark Wenjunchi
 */
public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUserEntity getUser() {
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }

}
