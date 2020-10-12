package com.authine.cloudpivot.web.api.service;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2020-02-11 13:46
 * @Description: 产品列表service接口
 */
public interface ProductService {

    public List<String> getProductNameList();

    public List<Map<String, String>> getProductNameAndIdList();

}
