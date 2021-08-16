package com.monetware.dispatchcenter.system.interceptor;


import com.monetware.dispatchcenter.system.util.threadlocal.ThreadLocalManager;
import com.monetware.dispatchcenter.system.util.threadlocal.TokenContext;

import javax.servlet.http.HttpServletRequest;

/**
 * url放行
 */
public class UrlFilter {

    /**
     * url过滤
     */
    public static boolean urlHandler(HttpServletRequest request) throws Exception {
        //截取需要的url路径
        String url = request.getServletPath();
        //过滤不需要拦截的url
        if (urlFilter(url)) {
            //登录和swagger不拦截
            TokenContext tokenContext = new TokenContext();
            ThreadLocalManager.setTokenContext(tokenContext);
            return true;
        }
        return false;
    }

    /**
     * 基础放行Url集合
     */
    private static boolean urlFilter(String url) {
        if (url.contains("swagger") //swagger-ui
                || url.contains("druid/")            //德鲁伊连接池
                || url.contains("/login")
                ) {
            return true;
        }
        return false;
    }
}
