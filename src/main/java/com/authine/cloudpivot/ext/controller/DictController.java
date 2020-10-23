package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.entity.Dict;
import com.authine.cloudpivot.ext.service.DictService;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/23 13:54
 */
@RestController
@RequestMapping("/api/dict")
public class DictController extends BaseController {

    @Resource
    private DictService dictService;

    /**
     * 获取用户字典
     *
     * @return ResponseResult<List < WorkOrder>>
     */
    @GetMapping("/user")
    public ResponseResult<List<Dict>> userDict() {
        List<Dict> userDict = dictService.getUserDict();
        return this.getOkResponseResult(userDict, "获取用户字典成功");
    }

    /**
     * 获取字典
     *
     * @return ResponseResult<List < WorkOrder>>
     */
    @GetMapping("/{dictItem}")
    public ResponseResult<List<Dict>> dict(@PathVariable("dictItem") String dictItem) {
        List<Dict> userDict = dictService.getDict(dictItem);
        return this.getOkResponseResult(userDict, "获取字典成功");
    }
}
