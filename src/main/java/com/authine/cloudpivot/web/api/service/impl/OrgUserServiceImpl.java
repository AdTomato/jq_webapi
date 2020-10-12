package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.OrgUser;
import com.authine.cloudpivot.web.api.mapper.OrgUserMapper;
import com.authine.cloudpivot.web.api.service.OrgUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2020-03-02 13:18
 * @Description:
 */
@Service
public class OrgUserServiceImpl implements OrgUserService {
    @Resource
    OrgUserMapper orgUserMapper;

    @Override
    public List<OrgUser> getUserIdAndId() {
        return orgUserMapper.getUserIdAndId();
    }
}
