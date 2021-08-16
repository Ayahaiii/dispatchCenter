package com.monetware.dispatchcenter.business.pojo.dto.manage;

import com.monetware.dispatchcenter.business.pojo.po.DcService;
import lombok.Data;

import java.util.List;

/**
 * @author Cookie
 * @create 2020-09-14 17:52
 * @Description: 条件查询节点信息返回DTO
 */
@Data
public class SearchNodeByParamDTO {

    /**
     * id
     */
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

    /**
     *
     */
    private List<DcService> dcServiceList;

}

