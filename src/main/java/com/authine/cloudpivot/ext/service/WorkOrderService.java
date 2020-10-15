package com.authine.cloudpivot.ext.service;

import com.authine.cloudpivot.ext.entity.WorkOrder;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/14 10:40
 */
public interface WorkOrderService {

    /**
     * 获取用户相关的工单
     *
     * @param userId 用户 ID
     * @return List<WorkOrder>
     */
    List<WorkOrder> getRelevantWorkOrderByUserId(String userId);

    /**
     * 构建树形结构
     *
     * @param list List<WorkOrder>
     * @return List<WorkOrder>
     */
    List<WorkOrder> treeList(List<WorkOrder> list);

}
