package com.monetware.dispatchcenter.business.controller;

import com.monetware.dispatchcenter.business.pojo.vo.AddTaskVO;
import com.monetware.dispatchcenter.business.pojo.vo.CancelTaskVO;
import com.monetware.dispatchcenter.business.pojo.vo.FinishTaskVO;
import com.monetware.dispatchcenter.business.service.DispatchCenterService;
import com.monetware.dispatchcenter.system.base.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cookie
 * @create 2020-09-11 13:08
 * @Description: 调度中心controller
 */
@Api("调度中心相关api")
@RestController
@RequestMapping("/dispatch")
public class DispatchCenterController {

    @Autowired
    DispatchCenterService dispatchCenterService;

    //=========================== Cookie Begin ===========================

    @ApiOperation(value = "新增任务")
    @PostMapping("v1/addTask")
    public ResultData<Integer> addTask(@RequestBody AddTaskVO param) {
        return new ResultData<>(0, "获取成功", dispatchCenterService.addTask(param));
    }

    @ApiOperation(value = "取消任务")
    @PostMapping("v1/cancelTask")
    public ResultData<Integer> cancelTask(@RequestBody CancelTaskVO param) {
        return new ResultData<>(0, "获取成功", dispatchCenterService.cancelTask(param));
    }

    @ApiOperation(value = "结束任务(成功)")
    @PostMapping("v1/finishTaskSuccess")
    public ResultData<Integer> finishTaskSuccess(@RequestBody FinishTaskVO param) {
        return new ResultData<>(0, "获取成功", dispatchCenterService.finishTaskSuccess(param));
    }

    //============================ Cookie End ============================

}
