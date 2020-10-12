package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.ContractService;
import com.authine.cloudpivot.web.api.service.I1uycXsContractListPayService;
import com.authine.cloudpivot.web.api.service.SwConstractAndChangedService;
import com.authine.cloudpivot.web.api.service.XsConstractAndChangedService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: wangyong
 * @Date: 2020-02-11 13:29
 * @Description: 合同controller
 */
@RestController
@RequestMapping("/controller/contract")
public class ContractController extends BaseController {

    @Autowired
    ContractService contractService;

    @Autowired
    I1uycXsContractListPayService i1uycXsContractListPayService;
    @Autowired
    XsConstractAndChangedService xsConstractAndChangedService;
    @Autowired
    SwConstractAndChangedService swConstractAndChangedService;

    @GetMapping("/getContractNameList")
    public ResponseResult<Object> getContractNameList() {

        List<String> contractNameList = contractService.getContractNameList();
        if (0 == contractNameList.size()) {
            return this.getErrResponseResult(null, 404L, "合同名称列表为空");
        } else {
            return this.getOkResponseResult(contractNameList, "获取合同名称列表成功");
        }
    }

    @GetMapping("/getContractNameAndIdList")
    public ResponseResult<Object> getContractNameAndIdList() {
        List<Map<String, String>> contractNameAndIdList = contractService.getContractNameAndIdList();
        if (0 == contractNameAndIdList.size()) {
            return this.getErrResponseResult(null, 404L, "合同名称列表为空");
        } else {
            return this.getOkResponseResult(contractNameAndIdList, "获取合同名称列表成功");
        }
    }

    //weiyao 批量插入合同变更子表
    @GetMapping("/insertContractList")
    public ResponseResult<String> insertAssessmentDetail( String id) {

        if(StringUtils.isNotEmpty(id)){
            String res=xsConstractAndChangedService.insertXsConstract(id);
            return this.getOkResponseResult("success", res);
        }else{
            return this.getErrResponseResult(null, 404L, "没有Id");
        }
    }

    //weiyao 批量插入采购合同变更子表
    @GetMapping("/insertSwBuyContractChangeDetail")
    public ResponseResult<String> insertSwBuyContractChangeDetail( String id) {

        if(StringUtils.isNotEmpty(id)){
            String res=swConstractAndChangedService.insertSwConstract(id);
            return this.getOkResponseResult("success", res);
        }else{
            return this.getErrResponseResult(null, 404L, "没有Id");
        }
    }

}
