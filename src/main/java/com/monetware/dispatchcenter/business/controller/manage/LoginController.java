package com.monetware.dispatchcenter.business.controller.manage;

import com.monetware.dispatchcenter.business.pojo.dto.manage.UserLoginDTO;
import com.monetware.dispatchcenter.business.pojo.vo.manage.AddNodeVO;
import com.monetware.dispatchcenter.business.pojo.vo.manage.UserLoginVO;
import com.monetware.dispatchcenter.business.service.manage.LoginService;
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
 * @create 2020-09-18 10:35
 * @Description:
 */
@Api("调度中心用户登陆相关api")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    //=========================== Cookie Begin ===========================

    @ApiOperation(value = "用户登陆")
    @PostMapping("v1/userLogin")
    public ResultData<UserLoginDTO> userLogin(@RequestBody UserLoginVO param) {
        return new ResultData<>(0, "调用成功", loginService.userLogin(param));
    }

    @ApiOperation(value = "用户登出")
    @PostMapping("v1/userLoginOut")
    public ResultData<Integer> userLoginOut() {
        return new ResultData<>(0, "调用成功", loginService.userLoginOut());
    }

    //============================ Cookie End ============================

}
