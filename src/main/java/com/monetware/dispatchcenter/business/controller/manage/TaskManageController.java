package com.monetware.dispatchcenter.business.controller.manage;

import com.monetware.dispatchcenter.business.pojo.dto.manage.SearchTaskByParamDTO;
import com.monetware.dispatchcenter.business.pojo.dto.manage.SearchTaskDetailDTO;
import com.monetware.dispatchcenter.business.pojo.vo.manage.SearchTaskByParamVO;
import com.monetware.dispatchcenter.business.service.manage.TaskManageService;
import com.monetware.dispatchcenter.system.base.CommonIdParam;
import com.monetware.dispatchcenter.system.base.PageList;
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
 * @create 2020-09-21 17:10
 * @Description:
 */
@Api("调度中心任务管理相关api")
@RestController
@RequestMapping("/task")
public class TaskManageController {

    @Autowired
    TaskManageService taskManageService;

    //=========================== Cookie Begin ===========================

    @ApiOperation(value = "条件查询任务")
    @PostMapping("v1/searchTaskByParam")
    public ResultData<PageList<SearchTaskByParamDTO>> searchTaskByParam(@RequestBody SearchTaskByParamVO param) {
        return new ResultData<>(0, "调用成功", taskManageService.searchTaskByParam(param));
    }

    @ApiOperation(value = "查询任务详情")
    @PostMapping("v1/searchTaskDetail")
    public ResultData<SearchTaskDetailDTO> searchTaskDetail(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", taskManageService.searchTaskDetail(param));
    }

    //============================ Cookie End ============================

}
