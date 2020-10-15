package com.authine.cloudpivot.ext.mapper;

import com.authine.cloudpivot.ext.entity.WorkOrder;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/14 10:33
 */
public interface WorkOrderMapper {

    /**
     * 根据创建人获取内部工单
     *
     * @param userid 用户ID
     * @return List<WorkOrder>
     */
    List<WorkOrder> getInternalWorkOrderByCreator(String userid);

    /**
     * 根据接收人获取内部工单
     *
     * @param userId 用户ID
     * @return List<WorkOrder>
     */
    List<WorkOrder> getInternalWorkOrderByRecipient(String userId);

    /**
     * 根据创建人获取协助（跨部门）工单
     *
     * @param userId 用户ID
     * @return List<WorkOrder>
     */
    List<WorkOrder> getAssistWorkOrderByCreator(String userId);

    /**
     * 根据接收人获取协助（跨部门）工单
     *
     * @param userId 用户ID
     * @return List<WorkOrder>
     */
    List<WorkOrder> getAssistWorkOrderByRecipient(String userId);

}
