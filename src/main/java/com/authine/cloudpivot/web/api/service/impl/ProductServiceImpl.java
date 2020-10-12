package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.mapper.ProductMapper;
import com.authine.cloudpivot.web.api.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2020-02-11 13:47
 * @Description: 产品列表service
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    ProductMapper productMapper;

    /**
     * @return : java.util.List<java.lang.String>
     * @Author: wangyong
     * @Date: 2020/2/11 13:47
     * @Description: 获取产品名称列表
     */
    @Override
    public List<String> getProductNameList() {
        return productMapper.getProductNameList();
    }

    @Override
    public List<Map<String, String>> getProductNameAndIdList() {
        return productMapper.getProductNameAndIdList();
    }
}
