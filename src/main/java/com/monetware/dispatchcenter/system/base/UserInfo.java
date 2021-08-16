package com.monetware.dispatchcenter.system.base;

import lombok.Data;

/**
 * @author Cookie
 * @create 2019-11-25 17:46
 * @Description:
 */
@Data
public class UserInfo {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String name;

    /**
     * 电话号码
     */
    private String telPhone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否已登录云质析
     */
    private boolean ifLoginNlp;

    /**
     * 云质析用户等级
     */
    private Integer nlpRole;

    /**
     * 云质析权限
     */
    private Object nlpPermission;

    /**
     * 是否已登录云采集
     */
    private boolean ifLoginSpider;

    /**
     * 云采集用户等级
     */
    private Integer spiderRole;

    /**
     * 云采集权限
     */
    private Object spiderPermission;

    /**
     * 是否已登录中文数据库
     */
    private boolean ifLoginCnd;

    /**
     * 中文数据库权限列表
     */
    private Object permission;

    /**
     * 1.普通用户
     * 2.管理员用户
     */
    private Integer type;

    /**
     * 用户登录唯一标识
     */
    private String wsKey;

    /**
     * 用户实时Token
     */
    private String token;

}
