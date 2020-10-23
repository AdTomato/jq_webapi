package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.entity.WorkOrder;
import com.authine.cloudpivot.ext.entity.WorkOrderQueryCondition;
import com.authine.cloudpivot.ext.service.WorkOrderService;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/14 10:48
 */
@RestController
@RequestMapping("/api/work_order")
public class WorkOrderController extends BaseController {

    @Resource
    private WorkOrderService workOrderService;

    /**
     * 获取用户创建的工单
     *
     * @param userId 用户 id
     * @return List
     */
    @GetMapping("/{uid}/create")
    public ResponseResult<List<WorkOrder>> getCreatedWorkOrders(
            @PathVariable("uid") String userId,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "urgency_degree", required = false) String urgencyDegree,
            @RequestParam(name = "overdue", required = false) String overdue,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "page_number", required = false, defaultValue = "1") String pageNumber,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") String pageSize
    ) {
        WorkOrderQueryCondition query = workOrderService
                .getQuery(userId, title, urgencyDegree, overdue, status, pageNumber, pageSize);
        List<WorkOrder> orders = workOrderService.getWorkOrderByCreator(query);
        return this.getOkResponseResult(orders, "获取用户创建的工单成功");
    }

    /**
     * 获取用户收到的工单
     *
     * @param userId 用户 id
     * @return List
     */
    @GetMapping("/{uid}/receive")
    public ResponseResult<List<WorkOrder>> getReceiveWorkOrders(
            @PathVariable("uid") String userId,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "urgency_degree", required = false) String urgencyDegree,
            @RequestParam(name = "overdue", required = false) String overdue,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "page_number", required = false, defaultValue = "1") String pageNumber,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") String pageSize
    ) {
        WorkOrderQueryCondition query = workOrderService
                .getQuery(userId, title, urgencyDegree, overdue, status, pageNumber, pageSize);
        List<WorkOrder> orders = workOrderService.getWorkOrderByRecipient(query);
        return this.getOkResponseResult(orders, "获取用户收到的工单成功");
    }

}
