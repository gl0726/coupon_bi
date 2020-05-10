package com.sq.modules.rechargeable.service.impl;

import com.sq.common.utils.Constant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.common.utils.PageUtils;
import com.sq.common.utils.Query;

import com.sq.modules.rechargeable.dao.AgentDao;
import com.sq.modules.rechargeable.entity.AgentEntity;
import com.sq.modules.rechargeable.service.AgentService;


@Service("agentService")
public class AgentServiceImpl extends ServiceImpl<AgentDao, AgentEntity> implements AgentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        IPage<AgentEntity> page = this.page(
                new Query<AgentEntity>().getPage(params),
                new QueryWrapper<AgentEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public void removeByIds(Long[] ids) {
        Collection c=new ArrayList();
        for(int i=0;i<ids.length;i++){
            AgentEntity agent=new AgentEntity();
            agent.setUpdateTime(new Date());
            agent.setEnable(Constant.ENABLE_INVALID);
            agent.setId(ids[i]);
            c.add(agent);
        }
        updateBatchById(c);
    }


}