package com.authine.cloudpivot.ext.service.impl;

import com.authine.cloudpivot.ext.entity.Department;
import com.authine.cloudpivot.ext.mapper.DepartmentMapper;
import com.authine.cloudpivot.ext.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/17 14:25
 */
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getUserDepartment(List<String> sourceId) {
        return departmentMapper.getUserDepartment(sourceId);
    }

}
