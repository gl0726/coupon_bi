/**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 *
 * https://www.sq.com
 *
 * 版权所有，侵权必究！
 */

package com.sq.modules.app.annotation;

import java.lang.annotation.*;

/**
 * app登录效验
 *
 * @author Mark Wenjunchi
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
