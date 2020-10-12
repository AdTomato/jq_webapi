package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.engine.api.facade.OrganizationFacade;
import com.authine.cloudpivot.engine.api.model.organization.UserModel;
import com.authine.cloudpivot.web.api.dubbo.DubboConfigService;
import com.authine.cloudpivot.web.api.entity.CwIncome;
import com.authine.cloudpivot.web.api.entity.I1uycXsContractListPay;
import com.authine.cloudpivot.web.api.entity.XsConstractAndChanged;
import com.authine.cloudpivot.web.api.mapper.I1uycXsContractListPayMapper;
import com.authine.cloudpivot.web.api.mapper.XsContractAndChangedMapper;
import com.authine.cloudpivot.web.api.service.I1uycXsContractListPayService;
import com.authine.cloudpivot.web.api.service.XsConstractAndChangedService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.swagger.util.Json;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class XsConstractAndChangedServiceImpl implements XsConstractAndChangedService {

    @Resource
    private XsContractAndChangedMapper xsContractAndChangedMapper;

    @Resource
    private I1uycXsContractListPayMapper i1uycXsContractListPayMapper;
    @Resource
    private I1uycXsContractListPayService i1uycXsContractListPayService;

    @Autowired
    private DubboConfigService dubboConfigService;

    @Override
    public String insertXsConstract(String id) {
        XsConstractAndChanged xsConstractAndChanged=xsContractAndChangedMapper.selectById(id);
        String preId2=UUID.randomUUID().toString().replace("-","");//插入的合同变更Id
        if(xsConstractAndChanged !=null){
            //是否变更 0未变更，1，已变更
            xsConstractAndChanged.setChanged(0);
            //上一版本
            xsConstractAndChanged.setChangedPid(xsConstractAndChanged.getChangedContractId());
            // 版本号
            xsConstractAndChanged.setVersion(xsConstractAndChanged.getVersion()==null?2:xsConstractAndChanged.getVersion()+1);
            xsConstractAndChanged.setDeleted(0);
            xsConstractAndChanged.setStates(1);
          //  xsConstractAndChanged.setStates(0);
            xsConstractAndChanged.setId(preId2);
            xsContractAndChangedMapper.insertXsConstract(xsConstractAndChanged);
        }else{
            return "Id错误";
        }
        //合同变更子表插入
        List<I1uycXsContractListPay> list=i1uycXsContractListPayMapper.getChangeByParentId(id);
        if(list !=null && list.size()>0){
            int i=1;
            for(I1uycXsContractListPay listPay: list){
                listPay.setId(UUID.randomUUID().toString().replace("-",""));
                listPay.setSortKey(i);
                listPay.setParentId(preId2);
                i++;
            }
            i1uycXsContractListPayService.insertI1uycXsContractListPay(list);
        }
        return "succeed";
    }

    //获取收入分析列表
    @Override
    public List<CwIncome> getCwIncomeList(Date startDate, Date endDate) {
        List<CwIncome> cwlist=xsContractAndChangedMapper.getCwIncomeInfo(startDate,endDate);
        if(cwlist !=null && cwlist.size()>0){
            for(CwIncome list:cwlist){
                Float backMoney=0f;
                List<I1uycXsContractListPay> listPay=i1uycXsContractListPayMapper.getChangeByParentId(list.getId());
                if(listPay !=null && listPay.size()>0 ){
                    //回款金额
                    List<Float> backmoneyList=new ArrayList<>();
                    for(I1uycXsContractListPay pay :listPay){
                        if(pay.getMoney()!=null && pay.getMoney()>0){
                            backmoneyList.add(pay.getMoney());
                            backMoney=backMoney+pay.getMoney();
                        }
                    }
                    //回款明细
                    list.setMoneyBack(backmoneyList);
                    //回款总金额
                    list.setMoneyBackAll(backMoney);
                }
                //剩余未回款金额
                if(list.getAllMoney()!=null){
                    list.setRemain(list.getAllMoney()-backMoney);
                }
                //設置省市
                if(StringUtils.isNotBlank(list.getZero())){
                    //JSONObject获取以{}开头的字符串
                    JSONObject jsonZ = JSONObject.fromObject(list.getZero());
                    list.setProvince(jsonZ.getString("provinceName"));
                    list.setCity(jsonZ.getString("cityName"));
                }
                //商务代表saleId设置姓名
                if(StringUtils.isNotBlank(list.getSalerId())){
                    //JSONArray获取以【】开头的字符串
                    JSONArray jsonArray = JSONArray.fromObject(list.getSalerId());
                    UserModel user=this.getOrganizationFacade().getUserById(jsonArray.getJSONObject(0).getString("id"));
                    list.setSalerId(user.getName());
                }

                //设置验收信息
                //是否验收1,是；0，否
                list.setIsAccept("否");
                List<Map<String,String>> reporInfo=xsContractAndChangedMapper.getAcceptReportInfo(list.getId());
                String accdate="";
                String accOption="";
                if(reporInfo !=null && reporInfo.size()>0 ){
                    list.setIsAccept("是");
                    for(Map<String,String> map :reporInfo){
                        accdate=accdate+map.get("acceptDate")+";";
                        accOption=accOption+map.get("acceptOpinion")+";";
                    }
                }

                list.setAcceptDates(accdate);
                list.setAcceptOpinions(accOption);
            }
            return cwlist;
        }
        return null;
    }

    public OrganizationFacade getOrganizationFacade() {
        return this.dubboConfigService.getOrganizationFacade();
    }
}
