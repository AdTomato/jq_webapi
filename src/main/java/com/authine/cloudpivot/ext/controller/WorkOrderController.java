package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.engine.api.model.organization.DepartmentModel;
import com.authine.cloudpivot.ext.entity.Department;
import com.authine.cloudpivot.ext.entity.WorkOrder;
import com.authine.cloudpivot.ext.entity.WorkOrderQueryCondition;
import com.authine.cloudpivot.ext.service.DepartmentService;
import com.authine.cloudpivot.ext.service.WorkOrderService;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private DepartmentService departmentService;

    /**
     * 获取用户创建的工单
     *
     * @param query 查询条件
     * @return List
     */
    public ResponseResult<List<WorkOrder>> getCreatedWorkOrders(WorkOrderQueryCondition query) {
        List<WorkOrder> orders = workOrderService.getWorkOrderByCreator(query);
        return this.getOkResponseResult(orders, "获取用户创建的工单成功");
    }

    /**
     * 获取用户收到的工单
     *
     * @param query 查询条件
     * @return List
     */
    public ResponseResult<List<WorkOrder>> getReceiveWorkOrders(WorkOrderQueryCondition query) {
        List<WorkOrder> orders = workOrderService.getWorkOrderByRecipient(query);
        return this.getOkResponseResult(orders, "获取用户收到的工单成功");
    }

    /**
     * 获取用户的工单
     *
     * @param userId 用户 id
     * @return List
     */
    @GetMapping("/{uid}/{type}")
    public ResponseResult<List<WorkOrder>> getWorkOrders(
            @PathVariable("uid") String userId,
            @PathVariable("type") String type,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "urgency_degree", required = false) String urgencyDegree,
            @RequestParam(name = "overdue", required = false) String overdue,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "page_number", required = false, defaultValue = "1") String pageNumber,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") String pageSize,
            @RequestParam(name = "tree_level", required = false, defaultValue = "3") String treeLevel
    ) {
        WorkOrderQueryCondition query = workOrderService
                .getQuery(userId, title, urgencyDegree, overdue, status, pageNumber, pageSize, treeLevel);
        switch (type) {
            case "create":
                return this.getCreatedWorkOrders(query);
            case "receive":
                return this.getReceiveWorkOrders(query);
            default:
                return null;
        }
    }

    @GetMapping("/departments")
    public ResponseResult<List<Department>> fdf(@RequestParam(name = "level", defaultValue = "1") int level) {
        String userId = this.getUserId();
        List<DepartmentModel> userDepartments = this.getOrganizationFacade().getDepartmentsByUserId(userId);
        List<String> sourceIds = userDepartments.stream().map(departmentModel -> {
            String[] split = departmentModel.getQueryCode().split("#");
            if (level >= 0 && level < split.length) {
                return split[level];
            } else  {
                return "";
            }
        }).filter(StringUtils::hasLength).collect(Collectors.toList());
        List<Department> userDepartment = departmentService.getUserDepartment(sourceIds);
        return this.getOkResponseResult(userDepartment, "获取用户所在部门成功");
    }
}
