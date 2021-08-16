package com.monetware.dispatchcenter.system.interceptor.customInterceptors;

import com.monetware.dispatchcenter.system.base.ErrorCode;
import com.monetware.dispatchcenter.system.base.SystemConfig;
import com.monetware.dispatchcenter.system.exception.ServiceException;
import com.monetware.dispatchcenter.system.util.jwt.JWTUtil;
import com.monetware.dispatchcenter.system.util.redis.RedisUtil;
import com.monetware.dispatchcenter.system.util.threadlocal.ThreadLocalManager;
import com.monetware.dispatchcenter.system.util.threadlocal.TokenContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * web端Token验证
 */
@Component
public class WebTokenCheck {


    @Autowired
    RedisUtil redisUtil;

    /**
     * Token验证
     */
    protected boolean check(HttpServletRequest request) throws Exception {
        //初始化token变量
        String securityToken;
        //获取url中的token
        securityToken = request.getHeader(SystemConfig.WEB_TOKEN);
        if (securityToken == null) {
            securityToken = request.getParameter(SystemConfig.WEB_TOKEN);
        }
        //判断token是否合法
        if (!JWTUtil.checkToken(securityToken)) {
            throw new ServiceException(ErrorCode.TOKEN_WITHOUT);
        }

        //根据token获取redis中保存的用户信息
        TokenContext tokenContext = (TokenContext) redisUtil.get(securityToken);
        if (tokenContext == null) {
            //若无查询的信息，则token无效
            throw new ServiceException(ErrorCode.TOKEN_WITHOUT);
        }
        //判断是否超时
        long currentTime = System.currentTimeMillis();
        long tokenCreateTime = Long.parseLong(tokenContext.getCreateTime().toString());
        if (currentTime - tokenCreateTime > 0 && currentTime - tokenCreateTime <= (SystemConfig.OVERDUE_TOKEN_TIME)) {
            ThreadLocalManager.setTokenContext(tokenContext);
        } else {
            //超过24小时后需要重新登录
            redisUtil.remove(securityToken);
            ThreadLocalManager.removeTokenContext();
            throw new ServiceException(ErrorCode.TOKEN_WITHOUT);
        }
        return true;
    }
}
