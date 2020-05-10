/**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 * <p>
 * https://www.sq.com
 * <p>
 * 版权所有，侵权必究！
 */

package com.sq.modules.app.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.modules.app.form.LoginForm;
import com.sq.modules.app.entity.UserEntity;

/**
 * 用户
 *
 * @author Mark Wenjunchi
 */
public interface UserService extends IService<UserEntity> {
    /**
     * @Author WenJunChi
     * @Description //查询用户
     * @Date 15:53 2019\3\8 0008
     * @Param
     * @return
     **/
    UserEntity queryByuserName(String mobile);

    /**
     * 用户登录
     *
     * @param form 登录表单
     * @return 返回用户ID
     */
    long login(LoginForm form);
}
