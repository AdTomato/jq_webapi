package com.authine.cloudpivot.web.api.mapper;


import com.authine.cloudpivot.web.api.entity.SwContractAndChanged;

/**
 * @Author: weiyao
 * @Date: 2020-03-06
 * @Description: 采购合同变更和采购合同
 */
public interface SwContractAndChangedMapper {

    //查询合同变更数据
    public SwContractAndChanged selectById(String id);

    //插入合同管理
    void insertSwConstract(SwContractAndChanged swContractAndChanged);

}
