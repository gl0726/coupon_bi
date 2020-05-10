package com.sq.modules.rechargeable.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.common.utils.PageUtils;
import com.sq.common.utils.Query;

import com.sq.modules.rechargeable.dao.CompanyDao;
import com.sq.modules.rechargeable.entity.CompanyEntity;
import com.sq.modules.rechargeable.service.CompanyService;


@Service("companyService")
public class CompanyServiceImpl extends ServiceImpl<CompanyDao, CompanyEntity> implements CompanyService {
    @Autowired
    private CompanyDao companyDao;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CompanyEntity> page = this.page(
                new Query<CompanyEntity>().getPage(params),
                new QueryWrapper<CompanyEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageCondition(Map<String, Object> params) {
        IPage<CompanyEntity> page = new Query<CompanyEntity>().getPage(params);
        String companyName = (String) params.get("companyName");
        return new PageUtils(companyDao.selectPageCondition(page, companyName));

    }

}