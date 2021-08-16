package com.monetware.dispatchcenter.business.controller.manage;

import com.monetware.dispatchcenter.business.pojo.dto.manage.SearchTaskTypeByParamDTO;
import com.monetware.dispatchcenter.business.pojo.dto.manage.SearchTaskTypeDetailDTO;
import com.monetware.dispatchcenter.business.pojo.vo.manage.AddTaskTypeVO;
import com.monetware.dispatchcenter.business.pojo.vo.manage.SearchTaskTypeByParamVO;
import com.monetware.dispatchcenter.business.pojo.vo.manage.UpdateTaskTypeVO;
import com.monetware.dispatchcenter.business.service.manage.TaskTypeManageService;
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
 * @create 2020-09-21 17:43
 * @Description:
 */
@Api("调度中心TaskApi管理相关api")
@RestController
@RequestMapping("/taskApi")
public class TaskTypeManageController {

    @Autowired
    TaskTypeManageService taskTypeManageService;

    //=========================== Cookie Begin ===========================

    @ApiOperation(value = "新增任务类型")
    @PostMapping("v1/addTaskType")
    public ResultData<Integer> addTaskType(@RequestBody AddTaskTypeVO param) {
        return new ResultData<>(0, "调用成功", taskTypeManageService.addTaskType(param));
    }

    @ApiOperation(value = "删除任务类型")
    @PostMapping("v1/deleteTaskType")
    public ResultData<Integer> deleteTaskType(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", taskTypeManageService.deleteTaskType(param));
    }

    @ApiOperation(value = "条件查询任务类型")
    @PostMapping("v1/searchTaskTypeByParam")
    public ResultData<PageList<SearchTaskTypeByParamDTO>> searchTaskTypeByParam(@RequestBody SearchTaskTypeByParamVO param) {
        return new ResultData<>(0, "调用成功", taskTypeManageService.searchTaskTypeByParam(param));
    }

    @ApiOperation(value = "查看项目类型详情")
    @PostMapping("v1/searchTaskTypeDetail")
    public ResultData<SearchTaskTypeDetailDTO> searchTaskTypeDetail(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", taskTypeManageService.searchTaskTypeDetail(param));
    }


    @ApiOperation(value = "修改项目类型")
    @PostMapping("v1/updateTaskType")
    public ResultData<Integer> updateTaskType(@RequestBody UpdateTaskTypeVO param) {
        return new ResultData<>(0, "调用成功", taskTypeManageService.updateTaskType(param));
    }

    //============================ Cookie End ============================

}
