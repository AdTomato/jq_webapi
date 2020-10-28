package com.authine.cloudpivot.ext.service;

import com.authine.cloudpivot.engine.api.exceptions.ServiceException;
import com.authine.cloudpivot.ext.entity.WorkOrder;
import com.authine.cloudpivot.ext.entity.WorkOrderQueryCondition;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/14 10:40
 */
public interface WorkOrderService {
    /**
     * 根节点
     */
    String ROOT_NODE = "ROOT";

    /**
     * 获取用户创建的工单
     *
     * @param condition 查询条件
     * @return List<WorkOrder>
     */
    List<WorkOrder> getWorkOrderByCreator(WorkOrderQueryCondition condition);

    /***
     * 获取用户收到的工单
     * @param condition 查询条件
     * @return List<WorkOrder>
     */
    List<WorkOrder> getWorkOrderByRecipient(WorkOrderQueryCondition condition);

    /**
     * 构建树形结构
     *
     * @param list List<WorkOrder>
     * @return List<WorkOrder>
     */
    List<WorkOrder> treeList(List<WorkOrder> list);

    /**
     * 获取查询参数
     *
     * @param userId        用户ID
     * @param title         标题摘要
     * @param urgencyDegree 紧急程度
     * @param overdue       是否过期
     * @param status        工单状态
     * @param pageNumber    页码
     * @param pageSize      数据条数
     * @param treeLevel     子工单显示层级
     * @return WorkOrderQueryCondition
     * @throws ServiceException 服务异常
     */
    WorkOrderQueryCondition getQuery(@NonNull String userId,
                                     @Nullable String title,
                                     @Nullable String urgencyDegree,
                                     @Nullable String overdue,
                                     @Nullable String status,
                                     @Nullable String pageNumber,
                                     @Nullable String pageSize,
                                     @Nullable String treeLevel
    ) throws ServiceException;

}
