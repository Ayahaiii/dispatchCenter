package com.monetware.dispatchcenter.business.controller.manage;

import com.monetware.dispatchcenter.business.pojo.dto.manage.SearchNodeByParamDTO;
import com.monetware.dispatchcenter.business.pojo.vo.manage.AddNodeVO;
import com.monetware.dispatchcenter.business.pojo.vo.manage.SearchNodeByParamVO;
import com.monetware.dispatchcenter.business.pojo.vo.manage.UpdateNodeVO;
import com.monetware.dispatchcenter.business.service.manage.NodeManageService;
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
 * @create 2020-09-14 17:41
 * @Description:
 */
@Api("调度中心节点管理相关api")
@RestController
@RequestMapping("/dispatch")
public class NodeManageController {

    @Autowired
    NodeManageService nodeManageService;

    //=========================== Cookie Begin ===========================

    @ApiOperation(value = "新增节点")
    @PostMapping("v1/addNode")
    public ResultData<Integer> addNode(@RequestBody AddNodeVO param) {
        return new ResultData<>(0, "调用成功", nodeManageService.addNode(param));
    }

//    @ApiOperation(value = "删除节点")
//    @PostMapping("v1/deleteNode")
//    public ResultData<Integer> deleteNode(@RequestBody CommonIdParam param) {
//        return new ResultData<>(0, "调用成功", nodeManageService.deleteNode(param));
//    }

    @ApiOperation(value = "条件查询节点")
    @PostMapping("v1/searchNodeByParam")
    public ResultData<PageList<SearchNodeByParamDTO>> searchNodeByParam(@RequestBody SearchNodeByParamVO param) {
        PageList<SearchNodeByParamDTO> result = nodeManageService.searchNodeByParam(param);
        return new ResultData<>(0, "调用成功", result);
    }

    @ApiOperation(value = "暂停节点")
    @PostMapping("v1/stopNode")
    public ResultData<Integer> stopNode(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", nodeManageService.stopNode(param));
    }

    @ApiOperation(value = "启动节点")
    @PostMapping("v1/startNode")
    public ResultData<Integer> startNode(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", nodeManageService.startNode(param));
    }

    @ApiOperation(value = "修改节点信息")
    @PostMapping("v1/updateNode")
    public ResultData<Integer> updateNode(@RequestBody UpdateNodeVO param) {
        return new ResultData<>(0, "调用成功", nodeManageService.updateNode(param));
    }

    //============================ Cookie End ============================

}
