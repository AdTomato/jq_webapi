package com.authine.cloudpivot.ext.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/17 14:13
 */
@Getter
@Setter
public class Department {
    int type = 1;

    /**
     * 部门ID
     */
    String id;

    /**
     * 部门名称
     */
    String name;

    /**
     * 钉钉原始部门ID
     */
    String sourceId;

    /**
     * 负责人表单ID
     */
    String formId;
}
