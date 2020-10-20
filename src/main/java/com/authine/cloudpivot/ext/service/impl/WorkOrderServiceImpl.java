package com.authine.cloudpivot.ext.service.impl;

import com.authine.cloudpivot.engine.api.exceptions.ServiceException;
import com.authine.cloudpivot.ext.entity.UrgencyDegree;
import com.authine.cloudpivot.ext.entity.WorkOrder;
import com.authine.cloudpivot.ext.entity.WorkOrderQueryCondition;
import com.authine.cloudpivot.ext.entity.WorkOrderStatus;
import com.authine.cloudpivot.ext.mapper.WorkOrderMapper;
import com.authine.cloudpivot.ext.service.WorkOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/14 10:41
 */
@Slf4j
@Service
public class WorkOrderServiceImpl implements WorkOrderService {

    @Resource
    private WorkOrderMapper workOrderMapper;


    @Override
    public List<WorkOrder> getWorkOrderByCreator(WorkOrderQueryCondition condition) {
        List<WorkOrder> workOrders = workOrderMapper.getWorkOrderByCreator(condition);
        return this.treeList(workOrders);
    }

    @Override
    public List<WorkOrder> getWorkOrderByRecipient(WorkOrderQueryCondition condition) {
        List<WorkOrder> workOrders = workOrderMapper.getWorkOrderByRecipient(condition);
        return this.treeList(workOrders);
    }

    @Override
    public List<WorkOrder> treeList(List<WorkOrder> list) {
        // TODO: 2020/10/15 17:17 转换树形结构
        return list;
    }

    @Override
    public WorkOrderQueryCondition getQuery(@NonNull String userId, String title, String urgencyDegree,
                                            String overdue, String status) throws ServiceException {
        WorkOrderQueryCondition result = new WorkOrderQueryCondition();
        result.setUserId(userId);
        if (!StringUtils.isEmpty(title)) {
            result.setTitle(title);
        }
        if (!StringUtils.isEmpty(urgencyDegree)) {
            try {
                result.setUrgencyDegree(UrgencyDegree.valueOf(urgencyDegree.toUpperCase()));
            } catch (IllegalArgumentException e) {
                log.error(e.getMessage(), e);
                throw new ServiceException(9999, "紧急度参数错误");
            }
        }

        if (!StringUtils.isEmpty(overdue)) {
            result.setOverdue(Boolean.parseBoolean(overdue));
        }

        if (!StringUtils.isEmpty(status)) {
            try {
                result.setStatus(WorkOrderStatus.valueOf(status.toUpperCase()));
            } catch (IllegalArgumentException e) {
                log.error(e.getMessage(), e);
                throw new ServiceException(9999, "订单状态参数错误");
            }
        }

        return result;
    }

}
