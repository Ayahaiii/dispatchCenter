package com.monetware.dispatchcenter.business.controller.manage;

import com.monetware.dispatchcenter.business.pojo.dto.manage.SearchServiceByParamDTO;
import com.monetware.dispatchcenter.business.pojo.vo.manage.AddServiceVO;
import com.monetware.dispatchcenter.business.pojo.vo.manage.SearchServiceByParamVO;
import com.monetware.dispatchcenter.business.pojo.vo.manage.UpdateServiceVO;
import com.monetware.dispatchcenter.business.service.manage.ServiceManageService;
import com.monetware.dispatchcenter.system.base.CommonIdParam;
import com.monetware.dispatchcenter.system.base.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Cookie
 * @create 2020-09-17 15:15
 * @Description:
 */
@Api("调度中心服务管理管理相关api")
@RestController
@RequestMapping("/dispatch")
public class ServiceManageController {

    @Autowired
    ServiceManageService serviceManageService;

    //=========================== Cookie Begin ===========================

    @ApiOperation(value = "新增service")
    @PostMapping("v1/addService")
    public ResultData<Integer> addService(@RequestBody AddServiceVO param) {
        return new ResultData<>(0, "调用成功", serviceManageService.addService(param));
    }

    @ApiOperation(value = "条件查询service")
    @PostMapping("v1/searchServiceByParam")
    public ResultData<List<SearchServiceByParamDTO>> searchServiceByParam(@RequestBody SearchServiceByParamVO param) {
        return new ResultData<>(0, "调用成功", serviceManageService.searchServiceByParam(param));
    }

    @ApiOperation(value = "禁用service")
    @PostMapping("v1/stopService")
    public ResultData<Integer> stopService(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", serviceManageService.stopService(param));
    }

    @ApiOperation(value = "启用service")
    @PostMapping("v1/startService")
    public ResultData<Integer> startService(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", serviceManageService.startService(param));
    }

    @ApiOperation(value = "修改service")
    @PostMapping("v1/updateService")
    public ResultData<Integer> updateService(@RequestBody UpdateServiceVO param) {
        return new ResultData<>(0, "调用成功", serviceManageService.updateService(param));
    }

    //============================ Cookie End ============================

}
