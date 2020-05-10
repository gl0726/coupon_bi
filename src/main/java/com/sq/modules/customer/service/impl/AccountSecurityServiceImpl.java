package com.sq.modules.customer.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.common.utils.PageUtils;
import com.sq.common.utils.Query;

import com.sq.modules.customer.dao.AccountSecurityDao;
import com.sq.modules.customer.entity.AccountSecurityEntity;
import com.sq.modules.customer.service.AccountSecurityService;


@Service("accountSecurityService")
public class AccountSecurityServiceImpl extends ServiceImpl<AccountSecurityDao, AccountSecurityEntity> implements AccountSecurityService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AccountSecurityEntity> page = this.page(
                new Query<AccountSecurityEntity>().getPage(params),
                new QueryWrapper<AccountSecurityEntity>()
        );

        return new PageUtils(page);
    }

}