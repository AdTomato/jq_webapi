package com.authine.cloudpivot.web.api.entity;

import lombok.Data;
import org.omg.CORBA.FloatSeqHelper;

import java.util.Date;
import java.util.List;

/**
 * @Author: weiyao
 * @Date: 2020-03-18 16:07
 * @Description: 收入分析实体
 */
@Data
public class CwIncome extends XsConstractAndChanged {

    //客户姓名
    private String custName;
    //地区
    private String zero;
    //省份
    private String province;
    //市
    private String city;

    /*
    验收信息  用逗号分隔
     */
    //验收时间
    private String acceptDates;
    //是否验收1，是，0否
    private String isAccept;
    //验收意见
    private String acceptOpinions;
    /*
    回款信息
     */
    //回款总金额
    private Float moneyBackAll;
    //税率
    private Float taxPoint;
    //税额
    private Float tax;
    //剩余金额
    private Float remain;

//    private Float otherMoney;

    //汇款明细，剩余金额
    private List<Float> moneyBack;

    //开始结束时间
    private Date startDate;
    private Date endDate;

    //项目类别名称
    private String projectTypeName;




}
