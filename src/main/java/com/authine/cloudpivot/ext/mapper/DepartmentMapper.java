package com.authine.cloudpivot.ext.mapper;

import com.authine.cloudpivot.ext.entity.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/17 14:20
 */
public interface DepartmentMapper {

    /**
     * 获取用所在部门
     *
     * @param sourceId 钉钉原始部门ID
     * @return List<Department>
     */
    List<Department> getUserDepartment(@Param("ids") List<String> sourceId);

}
