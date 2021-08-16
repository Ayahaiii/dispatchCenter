package com.monetware.dispatchcenter.business.pojo.po;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author Cookie
 * @create 2020-09-18 10:25
 * @Description: 管理用户实体类
 */
@Data
public class DcAdmin {

    @Id
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态
     * 1：正常
     * 2：异常
     */
    private Integer status;

}
