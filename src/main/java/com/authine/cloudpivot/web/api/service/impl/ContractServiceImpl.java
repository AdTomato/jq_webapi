package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.mapper.ContractMapper;
import com.authine.cloudpivot.web.api.service.ContractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2020-02-11 13:28
 * @Description: 合同service
 */
@Service
public class ContractServiceImpl implements ContractService {

    @Resource
    ContractMapper contractMapper;

    /**
     * @return : java.util.List<java.lang.String>
     * @Author: wangyong
     * @Date: 2020/2/11 13:29
     * @Description: 获取合同名称列表
     */
    @Override
    public List<String> getContractNameList() {
        return contractMapper.getContractNameList();
    }

    /**
     * @return : java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     * @Author: wangyong
     * @Date: 2020/3/6 0:46
     * @Description: 获取合同，id列表
     */
    @Override
    public List<Map<String, String>> getContractNameAndIdList() {
        return contractMapper.getContractNameAndIdList();
    }

}
