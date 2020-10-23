package com.authine.cloudpivot.ext.mapper;

import com.authine.cloudpivot.ext.entity.Dict;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/23 14:16
 */
public interface DictMapper {

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

}
