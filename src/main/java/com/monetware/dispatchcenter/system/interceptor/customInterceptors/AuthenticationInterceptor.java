package com.monetware.dispatchcenter.system.interceptor.customInterceptors;

import com.monetware.dispatchcenter.system.base.ErrorCode;
import com.monetware.dispatchcenter.system.base.SystemConfig;
import com.monetware.dispatchcenter.system.exception.ServiceException;
import com.monetware.dispatchcenter.system.interceptor.UrlFilter;
import com.monetware.dispatchcenter.system.util.spring.SpringBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    WebTokenCheck webTokenCheck = SpringBeanUtil.getBean(WebTokenCheck.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        log.info("=========Request Begin=========");
        //判断时候在不拦截范围
        if (UrlFilter.urlHandler(request)) {
            return true;
        }
        //其他接口拦截(可扩展：web,app,miniapp..）
        if (request.getHeader(SystemConfig.WEB_TOKEN) != null || request.getParameter(SystemConfig.WEB_TOKEN) != null) {
            //webToken验证
            return webTokenCheck.check(request);
        } else {
            throw new ServiceException(ErrorCode.TOKEN_WITHOUT);
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
