package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.entity.WorkOrder;
import com.authine.cloudpivot.ext.service.WorkOrderService;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/14 10:48
 */
@RestController
@RequestMapping("/work-order")
public class WorkOrderController extends BaseController {

    @Resource
    private WorkOrderService workOrderService;

    /**
     * 获取用户相关的工单
     *
     * @param userId 用户 id
     * @return List
     */
    @GetMapping("/{uid}")
    public ResponseResult<List<WorkOrder>> getWorkOrders(@PathVariable("uid") String userId) {
        List<WorkOrder> orders = workOrderService.getRelevantWorkOrderByUserId(userId);
        return this.getOkResponseResult(orders, "获取用户相关的工单成功");
    }

}
