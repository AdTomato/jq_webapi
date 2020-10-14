package com.authine.cloudpivot.ext.service.impl;

import com.authine.cloudpivot.ext.entity.WorkOrder;
import com.authine.cloudpivot.ext.mapper.WorkOrderMapper;
import com.authine.cloudpivot.ext.service.WorkOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/14 10:41
 */
@Service
public class WorkOrderServiceImpl implements WorkOrderService {

    @Resource
    private WorkOrderMapper workOrderMapper;

    @Override
    public List<WorkOrder> getRelevantWorkOrderByUserId(String userId) {
        List<WorkOrder> internalWorkOrderByCreator = workOrderMapper.getInternalWorkOrderByCreator(userId);
        List<WorkOrder> internalWorkOrderByRecipient = workOrderMapper.getInternalWorkOrderByRecipient(userId);
//        List<WorkOrder> workOrderByCreator = workOrderMapper.getWorkOrderByCreator(userId);
        return Collections.emptyList();
    }

}
