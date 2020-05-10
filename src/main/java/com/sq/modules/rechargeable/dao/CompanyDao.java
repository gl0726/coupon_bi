package com.sq.modules.rechargeable.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sq.modules.rechargeable.entity.CompanyEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 企业表
 * 
 * @author jianghy
 * @email jianghy@sqqmall.com
 * @date 2019-04-17 11:04:18
 */
@Mapper
public interface CompanyDao extends BaseMapper<CompanyEntity> {
    IPage<CompanyEntity> selectPageCondition(IPage page, @Param("companyName") String companyName);
}
