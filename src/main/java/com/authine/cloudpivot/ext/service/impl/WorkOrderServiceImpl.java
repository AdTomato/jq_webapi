package com.authine.cloudpivot.ext.service.impl;

import com.authine.cloudpivot.ext.entity.WorkOrder;
import com.authine.cloudpivot.ext.mapper.WorkOrderMapper;
import com.authine.cloudpivot.ext.service.WorkOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
        Set<WorkOrder> result = new LinkedHashSet<>();
        result.addAll(internalWorkOrderByCreator);
        result.addAll(internalWorkOrderByRecipient);
        return this.treeList(new ArrayList<>(result));
    }

    @Override
    public List<WorkOrder> treeList(List<WorkOrder> list) {
        // TODO: 2020/10/15 17:17 转换树形结构
        return list;
    }

}
