package com.authine.cloudpivot.web.api.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: wangyong
 * @Date: 2020-03-03 23:08
 * @Description: 所有表单的基类
 */
@Data
public class BaseEntity {

    /**
     * id
     */
    private String id;

    /**
     * 数据标题
     */
    private String name;

    /**
     * 创建人
     */
    private String creater;

    /**
     * 创建人部门id
     */
    private String createdDeptId;

    /**
     * 拥有者
     */
    private String owner;

    /**
     * 拥有者部门id
     */
    private String ownerDeptId;

    /**
     * 创建爱你时间
     */
    private Date createdTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Date modifiedTime;

    /**
     * 流程实例id
     */
    private String workflowInstanceId;

    /**
     * 单据号
     */
    private String sequenceNo;

    /**
     * 流程状态
     */
    private String sequenceStatus;

    /**
     * 部门查询编码
     */
    private String ownerDeptQueryCode;

}
