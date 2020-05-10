package com.sq.modules.customer.cache;

import com.sq.common.utils.R;
import com.sq.modules.customer.entity.CustomerEntity;

import java.util.List;

/**
 * @Classname CustomerCacheService
 * @Description 数据缓存
 * @Date 2019\4\3 0003 8:50
 * @Created by WenJunChi
 */
public interface CustomerCacheService {
    CustomerEntity save(CustomerEntity customerEntity);

    CustomerEntity update(CustomerEntity customerEntity);

    CustomerEntity delete(CustomerEntity customerEntity);

    CustomerEntity selectOneById(Long id);

    List<CustomerEntity> selectList();

    CustomerEntity getBySourceId(Integer partyId, String sourceId);

    CustomerEntity getByMobile(String mobile);

    /**
     * 重置密码
     *
     * @param customer
     * @return
     */
    CustomerEntity resetPassword(CustomerEntity customer);
}
