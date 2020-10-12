package com.authine.cloudpivot.web.api.entity;

import lombok.Data;

import java.util.Date;


/**
 * @Author: weiyao
 * @Date: 2020-03-06
 * @Description: 采购合同和采购合同变更表
 */
@Data
public class SwContractAndChanged {

    private String id;   //			                   varchar(36) NOT NULL,
    private String name;   //			               varchar(200) DEFAULT '' COMMENT '数据标题',
    private String creater;   //		              varchar(200) DEFAULT '' COMMENT '创建人',
    private String createdDeptId;   //	                   varchar(200) DEFAULT '' COMMENT '创建人部门',
    private String owner;   //                 varchar(200) DEFAULT '' COMMENT '拥有者',
    private String ownerDeptId;   //	              	varchar(200) DEFAULT '' COMMENT '拥有者部门',
    private Date createdTime;   //	               datetime DEFAULT NULL COMMENT '创建时间',
    private String modifier;   //	               varchar(200) DEFAULT '' COMMENT '修改人',
    private Date modifiedTime;   //	                  	datetime DEFAULT NULL COMMENT '修改时间',
    private String workflowInstanceId;   //         varchar(200) DEFAULT '' COMMENT '流程实例ID',
    private String sequenceNo;   //                  varchar(200) DEFAULT '' COMMENT '单据号',
    private String sequenceStatus;   //              varchar(200) DEFAULT '' COMMENT '单据状态',
    private String ownerDeptQueryCode;   //            varchar(200) DEFAULT '' COMMENT '部门查询编码',
    private String swBuyContractId;   //    ==      varchar(200) DEFAULT '' COMMENT '变更合同',
    private String swBuyApplyId;   //  			varchar(200) DEFAULT '' COMMENT '关联采购申请',
    private String contractNo;   //					varchar(200) DEFAULT '' COMMENT '合同编号',
    private String contractShortName;   //			 varchar(200) DEFAULT '' COMMENT '合同简称',
    private String contractName;   //		   	varchar(200) DEFAULT '' COMMENT '合同全称',
    private Integer changed;   //						int(1) DEFAULT '0' COMMENT '是否变更',
    private String changeRemark;   //  			   		mediumtext COMMENT '变更备注',
    private Integer contractVersion;   //		 decimal(19,6) DEFAULT NULL COMMENT '合同版本号',
    private String changedPid;   //     =           varchar(200) DEFAULT '' COMMENT '上一版本',
    private Integer states;   //						int(1) DEFAULT '0'否、1是 COMMENT '审批状态',
    private Integer deleted;   //						int(1) DEFAULT '0'否、1是  COMMENT '删除标志',

}
