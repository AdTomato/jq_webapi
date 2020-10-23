package com.authine.cloudpivot.ext.service.impl;

import com.authine.cloudpivot.ext.entity.Dict;
import com.authine.cloudpivot.ext.entity.UrgencyDegree;
import com.authine.cloudpivot.ext.entity.WorkOrderStatus;
import com.authine.cloudpivot.ext.mapper.DictMapper;
import com.authine.cloudpivot.ext.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/23 14:12
 */
@Slf4j
@Service
public class DictServiceImpl implements DictService {

    @Resource
    private DictMapper dictMapper;

    @Override
    public List<Dict> getDict(String dictItem) {
        if ("user".equals(dictItem)) {
            return dictMapper.getUserDict();
        } else if ("department".equals(dictItem)) {
            return dictMapper.getDepartmentDict();
        } else if ("urgency_degree".equals(dictItem)) {
            return this.getUrgencyDict();
        } else if ("work_order_status".equals(dictItem)) {
            return this.getWorkOrderStatusDict();
        }
        return null;
    }

    @Override
    public List<Dict> getUserDict() {
        return dictMapper.getUserDict();
    }

    @Override
    public List<Dict> getDepartmentDict() {
        return dictMapper.getDepartmentDict();
    }

    @Override
    public List<Dict> getUrgencyDict() {
        return Arrays.stream(UrgencyDegree.values())
                .map(urgencyDegree -> {
                    Dict dict = new Dict();
                    dict.setCode(urgencyDegree.name());
                    dict.setName(urgencyDegree.getText());
                    dict.setColor(urgencyDegree.getColor());
                    return dict;
                }).collect(Collectors.toList());
    }

    @Override
    public List<Dict> getWorkOrderStatusDict() {
        return Arrays.stream(WorkOrderStatus.values())
                .map(urgencyDegree -> {
                    Dict dict = new Dict();
                    dict.setCode(urgencyDegree.name());
                    dict.setName(urgencyDegree.getText());
                    dict.setColor(urgencyDegree.getColor());
                    return dict;
                }).collect(Collectors.toList());
    }
}
