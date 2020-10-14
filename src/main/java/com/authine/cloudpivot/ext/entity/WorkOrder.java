package com.authine.cloudpivot.ext.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/14 11:12
 */
@Getter
@Setter
public class WorkOrder {

    /**
     * 创建或接收的工单
     */
    private WorkOrderType type;

    /**
     * 工单ID
     */
    private String id;

    /**
     * 父工单ID
     */
    private String parentId;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 工单任务接收人
     */
    private List<String> recipient;

    /**
     * 是否跨部门工单
     */
    private Boolean transDepartment;

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
