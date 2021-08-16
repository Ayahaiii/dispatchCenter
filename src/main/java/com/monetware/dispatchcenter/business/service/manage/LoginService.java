package com.monetware.dispatchcenter.business.service.manage;

import com.monetware.dispatchcenter.business.dao.DcAdminDao;
import com.monetware.dispatchcenter.business.pojo.constant.DcAdminConstant;
import com.monetware.dispatchcenter.business.pojo.dto.manage.UserLoginDTO;
import com.monetware.dispatchcenter.business.pojo.po.DcAdmin;
import com.monetware.dispatchcenter.business.pojo.vo.manage.UserLoginVO;
import com.monetware.dispatchcenter.system.base.ErrorCode;
import com.monetware.dispatchcenter.system.base.ResultData;
import com.monetware.dispatchcenter.system.base.SystemConfig;
import com.monetware.dispatchcenter.system.exception.ServiceException;
import com.monetware.dispatchcenter.system.util.codec.MD5;
import com.monetware.dispatchcenter.system.util.jwt.JWTUtil;
import com.monetware.dispatchcenter.system.util.redis.RedisUtil;
import com.monetware.dispatchcenter.system.util.threadlocal.ThreadLocalManager;
import com.monetware.dispatchcenter.system.util.threadlocal.TokenContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Cookie
 * @create 2020-09-18 10:37
 * @Description: 登陆相关service
 */
@Slf4j
@Service
public class LoginService {

    @Autowired
    DcAdminDao dcAdminDao;

    @Autowired
    RedisUtil redisUtil;

    //=========================== Cookie Begin ===========================

    /**
     * @Author: Cookie
     * @Date: 2020-09-18 10:44
     * @Description: 用户登陆
     */
    public UserLoginDTO userLogin(UserLoginVO param) {
        //1.加密密码
        param.setPassword(MD5.encode(param.getPassword()));
        DcAdmin dcAdmin = new DcAdmin();
        dcAdmin.setUsername(param.getUsername());
        dcAdmin.setPassword(param.getPassword());
        dcAdmin = dcAdminDao.selectOne(dcAdmin);
        //判断管理员是否存在
        if (dcAdmin == null) {
            throw new ServiceException(ErrorCode.USER_NOT_EXIT);
        }
        //判断管理员是否禁用
        if (dcAdmin.getStatus().equals(DcAdminConstant.STATUS_DISABLE)) {
            throw new ServiceException(ErrorCode.USER_IS_DISABLE);
        }
        //redis缓存用户信息
        loginWithToken(dcAdmin.getId(), SystemConfig.LOGIN_WEB);
        UserLoginDTO result = new UserLoginDTO();
        result.setUsername(dcAdmin.getUsername());
        return result;
    }

    /**
     * @Author: Cookie
     * @Date: 2020-09-18 11:28
     * @Description: 管理员退出登陆
     */
    public Integer userLoginOut() {
        TokenContext tokenContext = ThreadLocalManager.getTokenContext();
        //从redis中删除用户信息
        redisUtil.remove(tokenContext.getToken());
        return ResultData.OK;
    }

    //============================== 共用方法==============================

    /**
     * @Author: Cookie
     * @Date: 2019/1/17 15:59
     * @Description: 用户登录公用方法
     */
    public void loginWithToken(Integer userId, Integer loginType) {
        TokenContext tokenContext = new TokenContext();
        tokenContext.setUserId(userId);
        tokenContext.setType(loginType);
        tokenContext.setCreateTime(System.currentTimeMillis());
        String securityToken = JWTUtil.createToken();
        tokenContext.setToken(securityToken);
        ThreadLocalManager.setTokenContext(tokenContext);
        redisUtil.set(securityToken, tokenContext, SystemConfig.REDIS_DELETE_TOKEN_TIME);
    }

    //============================ Cookie End ============================

}
