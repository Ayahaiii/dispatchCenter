package com.monetware.dispatchcenter.business.pojo.vo.manage;

import lombok.Data;

/**
 * @author Cookie
 * @create 2020-09-16 17:41
 * @Description: 修改节点传入参数
 */
@Data
public class UpdateNodeVO {

    private Integer id;

    /**
     * 节点名称
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

}
