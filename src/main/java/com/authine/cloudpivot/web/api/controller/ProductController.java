package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.ProductService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2020-02-11 13:48
 * @Description: 产品列表controller
 */
@RestController
@RequestMapping("/controller/product")
public class ProductController extends BaseController {

    @Autowired
    ProductService productService;

    @GetMapping("/getProductNameList")
    public ResponseResult<Object> getProductNameList() {
        List<String> productNameList = productService.getProductNameList();
        if (productNameList.size() == 0) {
            // 没有内容，返回空
            return this.getErrResponseResult(null, 404L, "产品名称列表为空");
        } else {
            // 存在内容，返回产品列表
            return this.getOkResponseResult(productNameList, "获取产品名称列表成功");
        }
    }

    @GetMapping("/getProductNameAndIdList")
    public ResponseResult<Object> getProductNameAndIdList() {
        List<Map<String, String>> productNameAndIdList = productService.getProductNameAndIdList();
        if (productNameAndIdList.size() == 0) {
            // 没有内容，返回空
            return this.getErrResponseResult(null, 404L, "产品名称列表为空");
        } else {
            // 存在内容，返回产品列表
            return this.getOkResponseResult(productNameAndIdList, "获取产品名称列表成功");
        }
    }

}
