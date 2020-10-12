package com.authine.cloudpivot.web.api.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: weiyao
 * @Date: 2020-03-05
 * @Description: 合同管理和合同变更表
 */
@Data
public class XsConstractAndChanged {
    private String id;
    private String name;                     //(200) DEFAULT '' COMMENT '数据标题',
    private String creater;                   //   (200) DEFAULT '' COMMENT '创建人',
    private String createdDeptId;            //         (200) DEFAULT '' COMMENT '创建人部门',
    private String owner;               // (200) DEFAULT '' COMMENT '拥有者',
    private String ownerDeptId;               //       (200) DEFAULT '' COMMENT '拥有者部门',
    private Date createdTime;               //        DEFAULT NULL COMMENT '创建时间',
    private String modifier;                 //    (200) DEFAULT '' COMMENT '修改人',
    private Date modifiedTime;              //         DEFAULT NULL COMMENT '修改时间',
    private String workflowInstanceId;        //              (200) DEFAULT '' COMMENT '流程实例ID',
    private String sequenceNo;               //      (200) DEFAULT '' COMMENT '单据号',
    private String sequenceStatus;            //          (200) DEFAULT '' COMMENT '单据状态',
    private String ownerDeptQueryCode;         //              (200) DEFAULT '' COMMENT '部门查询编码'
    private String contractType;             //         (200) DEFAULT '' COMMENT '合同类型',
    private String pId;                       //200) DEFAULT '' COMMENT '关联主合同',
    private String projectNameShort;        //              (200) DEFAULT '' COMMENT '项目简称',
    private String projectName;            //        (200) DEFAULT '' COMMENT '项目全称',
    private String custId;                //   (200) DEFAULT '' COMMENT '客户',
    private String userName;             //     (200) DEFAULT '' COMMENT '使用方(用户)',
    private String projectType;              //        (200) DEFAULT '' COMMENT '项目类别',
    private String productNames;             //         (200) DEFAULT '' COMMENT '软件产品',
    private String productIds;             //       (200) DEFAULT '' COMMENT '关联软件产品id',
    private String salerId;                 //     COMMENT '商务代表',
    private String salerDepart;             //         COMMENT '销售部门',
    private String salerDepartLeader;       //                COMMENT '销售主管',
    private Float allMoney;              //     (19,6) DEFAULT NULL COMMENT '合同总金额',
    private Double softMoney;             //      (19,6) DEFAULT NULL COMMENT '软件金额',
    private Double hardwareMoney;           //          (19,6) DEFAULT NULL COMMENT '硬件金额',
    private Float otherMoney;           //          (19,6) DEFAULT NULL COMMENT '其他金额',
    private Date operationStartDate;       //                 DEFAULT NULL COMMENT '运维开始日期',
    private Date operationEndDate;        //               DEFAULT NULL COMMENT '运维结束日期',
    private Double operationPrice;          //           (19,6) DEFAULT NULL COMMENT '运维金额(元年
    private Date carryBeginDate;         //             DEFAULT NULL COMMENT '实际开工日期',
    private Date carryEndDate;          //           DEFAULT NULL COMMENT '实际完工日期',
    private Integer accept;                 //int(1) DEFAULT '0' COMMENT '是否验收',
    private String managerUserId;           //            COMMENT '项目经理',
    private Date planBeginDate;           //            DEFAULT NULL COMMENT '约定开工日期',
    private Date planEndDate;           //          DEFAULT NULL COMMENT '约定完工日期',
    private String contractName;           //         (200) DEFAULT '' COMMENT '合同名称',
    private String contractNo;             //       (200) DEFAULT '' COMMENT '合同编号',
    private String comes;                // (200) DEFAULT '' COMMENT '项目来源',
    private Date signDate;               //      DEFAULT NULL COMMENT '签订日期',
    private String payWay;                //   (200) DEFAULT '' COMMENT '付款方式',
    private String accountEntity;           //          (200) DEFAULT '' COMMENT '会计主体',
    private String archivesPlace;           //          (200) DEFAULT '' COMMENT '合同档案存放',
    private String projectNo;              //      (200) DEFAULT '' COMMENT '项目编号',
    private String industry;               //    (200) DEFAULT '' COMMENT '所属行业',
    private Integer changed;                //int(1) DEFAULT '0' COMMENT '是否变更',
    private String changedPid;             //       (200) DEFAULT '' COMMENT '上一版本',
    private String changeRemark;           //          COMMENT '变更备注',
    private String acceptExecutive;          //             COMMENT '验收执行人',
    private String acceptName;           //       (200) DEFAULT '' COMMENT '验收报告名称',
    private Date acceptDate;           //        DEFAULT NULL COMMENT '验收日期',
    private String acceptOpinion;           //           COMMENT '验收意见',
    private String acceptClientSatisfaction; //                       COMMENT '客户满意度',
    private String acceptRemark;         //          COMMENT '验收备注',
    private Integer version;               //  版本号
    private String changedContractId;      //  (200) DEFAULT '' COMMENT '变更合同',
    private Integer states;   //						int(1) DEFAULT '0'否、1是 COMMENT '审批状态',
    private Integer deleted;   //						int(1) DEFAULT '0'否、1是  COMMENT '删除标志',
    private String projectLevel; //项目规模
    private String moneyRemark;  //合同金额描述
    private String payRemark;   //结算方式描述
    private String operationRemark;  //售后服务描述
    private String remark1;  //预留字段123
    private String remark2;   //
    private String remark3;  //
    private String qualityRemark;//质保服务描述
    private Date qualityBeginDate;//质保开始日期
    private Date qualityEndDate;//质保结束日期
    private Float qualityMoney; //质保金额
    private Date qualityMoneyBack; //质保金退回时间


}
