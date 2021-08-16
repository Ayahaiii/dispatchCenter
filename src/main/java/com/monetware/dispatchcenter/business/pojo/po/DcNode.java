package com.monetware.dispatchcenter.business.pojo.po;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author Cookie
 * @create 2020-09-11 11:32
 * @Description: 节点表
 */
@Data
public class DcNode {

    @Id
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * IP
     */
    private String ip;

    /**
     * 端口号
     */
    private String port;

    /**
     * 节点描述
     */
    private String description;

    /**
     * 状态：
     * 1.正常
     * 2.异常
     * 3.禁用
     */
    private Integer status;

}
