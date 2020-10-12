package com.authine.cloudpivot.web.api.mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2020-02-11 13:43
 * @Description: 产品列表mapper
 */
public interface ProductMapper {

    public List<String> getProductNameList();

    List<Map<String, String>> getProductNameAndIdList();
}
