package com.authine.cloudpivot.ext.mapper;

import com.authine.cloudpivot.ext.entity.WorkOrder;
import com.authine.cloudpivot.ext.entity.WorkOrderQueryCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/14 10:33
 */
public interface WorkOrderMapper {

    /**
     * 获取用户创建的工单
     *
     * @param condition 查询条件
     * @return List<WorkOrder>
     */
    List<WorkOrder> getWorkOrderByCreator(@Param("entity") WorkOrderQueryCondition condition);

    /***
     * 获取用户收到的工单
     * @param condition 查询条件
     * @return List<WorkOrder>
     */
    List<WorkOrder> getWorkOrderByRecipient(@Param("entity") WorkOrderQueryCondition condition);

    /**
     * 获取子工单
     *
     * @param id 工单ID
     * @return List<WorkOrder>
     */
    List<WorkOrder> getChildrenWorkOrderById(String id);
}
