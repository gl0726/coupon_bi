package com.sq.modules.rechargeable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.common.utils.PageUtils;
import com.sq.modules.rechargeable.entity.AgentEntity;

import java.util.Map;

/**
 * 代理商表
 *
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-04-17 16:34:11
 */
public interface AgentService extends IService<AgentEntity> {

    PageUtils queryPage(Map<String, Object> params);


    void removeByIds(Long[] ids);
}

