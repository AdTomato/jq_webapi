package com.authine.cloudpivot.web.api.entity;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2020-03-03 23:07
 * @Description: 月历实体类
 */
@Data
public class SysCalendar extends BaseEntity{

    /**
     * 人员
     */
    private String person;

    /**
     * 年
     */
    private Double year;

    /**
     * 月
     */
    private Double month;

    /**
     * 日
     */
    private Double day;

     /**
     * 日类型
     * 有请假，出差，内勤
     */
    private String dayType;

    /**
     * 工作类型
     * 有项目实施，产品研发
     */
    private String workSetting;

    /**
     * 关联名称
     */
    private String relatedName;

    /**
     * 关联id
     * 如果工作类型是项目实施的话这里关联合同id
     * 如果工作类型是产品研发的话这里关联产品id
     */
    private String relatedId;

    /**
     * 工作内容
     */
    private String workContent;

}
