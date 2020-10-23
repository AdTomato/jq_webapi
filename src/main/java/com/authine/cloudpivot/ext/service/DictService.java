package com.authine.cloudpivot.ext.service;

import com.authine.cloudpivot.ext.entity.Dict;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/23 14:11
 */
public interface DictService {

    /**
     * 获取字段字段项数据
     *
     * @param dictItem 字典名称
     * @return List<Dict>
     */
    List<Dict> getDict(String dictItem);

    /**
     * 获取用户字典
     *
     * @return List<DictData>
     */
    List<Dict> getUserDict();


    /**
     * 获取部门字典
     *
     * @return List<DictData>
     */
    List<Dict> getDepartmentDict();

    /**
     * 获取紧急程度字典
     *
     * @return List<DictData>
     */
    List<Dict> getUrgencyDict();

    /**
     * 获取紧工单状态字段
     *
     * @return List<DictData>
     */
    List<Dict> getWorkOrderStatusDict();
}
