package com.monetware.dispatchcenter.business.pojo.vo.manage;

import lombok.Data;

/**
 * @author Cookie
 * @create 2020-09-18 10:44
 * @Description: 用户登陆传入VO
 */
@Data
public class UserLoginVO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
