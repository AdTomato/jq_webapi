package com.authine.cloudpivot.web.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wangyong
 * @time: 2020/5/9 15:11
 * @Description:
 */
@Api(value = "钉钉同步接口", tags = "钉钉::回调地址接口")
@Controller
@RequestMapping("/public")
@Slf4j
public class MyDingtalkNotifyController extends BaseController {

    /**
     * 钉钉回调异步通知
     *
     * @param body
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    @ApiOperation(value = "钉钉回调异步通知", notes = "钉钉回调异步通知")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "body", value = "消息体", required = true, dataType = "JSONObject", paramType = "query"),
            @ApiImplicitParam(name = "signature", value = "加密签名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "nonce", value = "随机数", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("/dingtalk/notify")
    @ResponseBody
    public String dingtalkNotify(@RequestBody JSONObject body, @RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce) {
        log.info("钉钉回调异步通知：" + body);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("encrypt", body.getString("encrypt"));
        parameters.put("signature", signature);
        parameters.put("timestamp", timestamp);
        parameters.put("nonce", nonce);
        return String.valueOf(getOrganizationFacade().incrementalSync(parameters));
    }

}
