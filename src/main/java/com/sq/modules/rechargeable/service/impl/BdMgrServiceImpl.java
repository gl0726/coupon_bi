package com.sq.modules.rechargeable.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.sq.common.exception.RRException;
import com.sq.common.utils.Constant;
import com.sq.common.utils.ServiceCode;
import com.sq.modules.sys.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.common.utils.PageUtils;
import com.sq.common.utils.Query;

import com.sq.modules.rechargeable.dao.BdMgrDao;
import com.sq.modules.rechargeable.entity.BdMgrEntity;
import com.sq.modules.rechargeable.service.BdMgrService;

import javax.xml.crypto.Data;


@Service("bdMgrService")
public class BdMgrServiceImpl extends ServiceImpl<BdMgrDao, BdMgrEntity> implements BdMgrService {

    @Autowired
    private SysConfigService sysConfigService;
    private final static String COMPANY_COUPON_DICT = "COMPANY_COUPON_DICT";

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BdMgrEntity> page = this.page(
                new Query<BdMgrEntity>().getPage(params),
                new QueryWrapper<BdMgrEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public BdMgrEntity getById(Serializable id) {
        BdMgrEntity bdMgrEntity = this.baseMapper.selectOne(new QueryWrapper<BdMgrEntity>().eq(Constant.ENABLE, Constant.ENABLE_VALID).eq("id", id));

        if (bdMgrEntity == null) {
            throw new RRException(ServiceCode.DB_SERVICE_SPEAKER_NOT_EXISTED);
        }
        /**
         * 设置公司Value值
         */
        String companyValue = this.sysConfigService.getValue(COMPANY_COUPON_DICT);
        List<Map<String, Object>> parse = (List<Map<String, Object>>) JSONUtils.parse(companyValue);
        for (Map<String, Object> map : parse) {
            if (Integer.parseInt(map.get(Constant.DATA_KEY).toString()) == (bdMgrEntity.getCompany())) {
                bdMgrEntity.setCompanyName(String.valueOf(map.get(Constant.DATA_NAME)));
                break;
            }

        }


        return bdMgrEntity;
    }
}