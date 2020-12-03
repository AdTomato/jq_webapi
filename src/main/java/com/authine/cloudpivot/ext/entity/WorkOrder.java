package com.authine.cloudpivot.ext.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/14 11:12
 */
@Getter
@Setter
public class WorkOrder {

    /**
     * 是否跨部门工单
     */
    private Boolean transDepartment;

    /**
     * 工单ID
     */
    private String id;

    /**
     * 父工单ID
     */
    private String parentId;

    /**
     * 标题摘要
     */
    private String title;

    /**
     * 工作内容描述
     */
    private String description;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 要求完成日期
     */
    private Date deadline;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 审批人
     */
    private Set<String> approver;

    /**
     * 关联部门
     */
    private Set<String> department;

    /**
     * 执行人
     */
    private Set<String> executor;

    /**
     * 工单任务接收人（审批人，执行人）
     */
    private Set<String> recipient;

    /**
     * 工单状态
     */
    private WorkflowStatus status;

    /**
     * 子工单
     */
    private List<WorkOrder> children;

    /**
     * 紧急程度
     */
    private String urgencyDegree;

    /**
     * 流程实例ID
     */
    private String workflowId;

    /**
     * 任务ID
     */
    private String workItemId;

    /**
     * 树形节点等级
     */
    private int level;

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WorkOrder) {
            return id.equals(((WorkOrder) obj).id);
        }
        return false;
    }
}
