package com.monetware.dispatchcenter.business.pojo.vo.manage;

import lombok.Data;

/**
 * @author Cookie
 * @create 2020-09-16 15:25
 * @Description: 新增节点传入参数
 */
@Data
public class AddNodeVO {

    /**
     * IP
     */
    private String ip;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 端口号
     */
    private String port;

    /**
     * 节点描述
     */
    private String description;

}
