package com.monetware.dispatchcenter.system.base;

import lombok.Data;

/**
 * @author Cookie
 * @create 2019-11-25 17:31
 * @Description: UUMS 登录返回参数
 */
@Data
public class OauthLoginData {

    private Integer code;

    private String message;

    private String access_token;

    private Object userInfo;

}
