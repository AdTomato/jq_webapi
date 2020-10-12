package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.HBizAttachment;
import com.authine.cloudpivot.web.api.entity.SwBuyContractChangeDetail;
import com.authine.cloudpivot.web.api.entity.SwContractAndChanged;
import com.authine.cloudpivot.web.api.mapper.I1uycXsContractListPayMapper;
import com.authine.cloudpivot.web.api.mapper.SwContractAndChangedMapper;
import com.authine.cloudpivot.web.api.service.I1uycXsContractListPayService;
import com.authine.cloudpivot.web.api.service.SwConstractAndChangedService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class SwConstractAndChangedServiceImpl implements SwConstractAndChangedService {

    @Resource
    private SwContractAndChangedMapper swContractAndChangedMapper;

    @Resource
    private I1uycXsContractListPayMapper i1uycXsContractListPayMapper;
    @Resource
    private I1uycXsContractListPayService i1uycXsContractListPayService;

    @Override
    public String insertSwConstract(String id) {
        SwContractAndChanged xsConstractAndChanged=swContractAndChangedMapper.selectById(id);
        String preId2=UUID.randomUUID().toString().replace("-","");
        if(xsConstractAndChanged !=null){
            //是否变更 0未变更，1，已变更
            xsConstractAndChanged.setChanged(0);
            //上一版本
            xsConstractAndChanged.setChangedPid(xsConstractAndChanged.getSwBuyContractId());
            //版本号
            xsConstractAndChanged.setContractVersion(xsConstractAndChanged.getContractVersion()==null?1:xsConstractAndChanged.getContractVersion()+1);
            xsConstractAndChanged.setStates(1);//是
            xsConstractAndChanged.setDeleted(0);//否
            //  xsConstractAndChanged.setStates(0);
            xsConstractAndChanged.setId(preId2);
            swContractAndChangedMapper.insertSwConstract(xsConstractAndChanged);
        }else{
            return "Id错误";
        }
        //采购合同变更子表插入
        List<SwBuyContractChangeDetail> list=i1uycXsContractListPayMapper.getSwChangeByParentId(id);
        if(list !=null && list.size()>0){
            int i=1;
            for(SwBuyContractChangeDetail listPay: list){

                String id1=listPay.getId();
             //   String parentid=listPay.getParentId();
                //插入采购合同子表
                String id2=UUID.randomUUID().toString().replace("-","");

                listPay.setId(id2);
                listPay.setParentId(preId2);//主表Id
                listPay.setSortKey(i);
                i1uycXsContractListPayService.insertSwBuyContractChangeDetail(listPay);

                HBizAttachment biz=i1uycXsContractListPayMapper.getFujianInfo(id1,id);
                //插入附件
                if(biz !=null){
                    biz.setId(UUID.randomUUID().toString().replace("-",""));
                    biz.setBizObjectId(id2);
                    biz.setParentBizObjectId(preId2);
                    biz.setParentSchemaCode("sw_buy_contract");
                    biz.setSchemaCode("sw_buy_contract_detail");
                    i1uycXsContractListPayService.insertSwBuyFujian(biz);
                }

                i++;
            }

        }
        return "succeed";
    }
}
