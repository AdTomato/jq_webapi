package com.authine.cloudpivot.ext.service;

import com.authine.cloudpivot.ext.entity.Department;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/17 14:24
 */
public interface DepartmentService {

    /**
     * 获取用所在部门
     *
     * @param sourceId 钉钉原始部门ID
     * @return List<Department>
     */
    List<Department> getUserDepartment(List<String> sourceId);

}
