package com.authine.cloudpivot.web.api.service;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2020-02-11 13:27
 * @Description: 合同service接口
 */
public interface ContractService {

    /**
     * 获取合同名称列表
     *
     * @return: 获取合同名称列表
     */
    public List<String> getContractNameList();

    /**
     * 获取合同，id列表
     * @return 合同，id列表
     */
    public List<Map<String, String>> getContractNameAndIdList();

}
