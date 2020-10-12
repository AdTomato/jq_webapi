package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.constant.Constants;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.BuyApplyService;
import com.authine.cloudpivot.web.api.service.SaleApplyService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wangyong
 * @Date: 2020-03-06 16:53
 * @Description: 关于流程的controller
 */
@RestController
@RequestMapping("/controller/workflow")
public class MyWorkflowController extends BaseController {

    @Autowired
    BuyApplyService buyApplyService;

    @Autowired
    SaleApplyService saleApplyService;

    public ResponseResult<Object> changeStates(@RequestParam String id, @RequestParam String states, @RequestParam String schemaCode) {

        if (!"已通过".equals(states) && !"审批中".equals(states) && !"未通过".equals(states)) {
            return this.getErrResponseResult(null, 400L, "不存在状态");
        }

        change(id, states, schemaCode);
        return this.getErrResponseResult(null, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    /**
     * @param id         : 变更数据id
     * @param states     : 变更数据状态
     * @param schemaCode : 变更表单
     * @return : void
     * @Author: wangyong
     * @Date: 2020/3/6 17:03
     * @Description: 更改流程数据的状态
     */
    private void change(String id, String states, String schemaCode) {


        switch (schemaCode) {
            case Constants.XS_BUY_APPLY_SCHEMA:  // 采购申购申请
                buyApplyService.changeStates(id, states);
                break;

            case Constants.XS_SALE_APPLY_SCHEMA:  // 销售申请
                saleApplyService.changeStates(id, states);
                break;
        }
    }

}
