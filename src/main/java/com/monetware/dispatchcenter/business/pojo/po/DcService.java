package com.monetware.dispatchcenter.business.pojo.po;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author Cookie
 * @create 2020-09-11 11:35
 * @Description: 节点服务表
 */
@Data
public class DcService {

    @Id
    private Integer id;

    /**
     * 对应节点id
     */
    private Integer nodeId;

    /**
     * 地址
     */
    private String url;

    /**
     * 项目类型：
     * 1.云采集，2.云文析，3.云统计
     */
    private Integer projectType;

    /**
     * 心跳检测url
     */
    private String heartBeatUrl;

    /**
     * 状态：
     * 1.正常
     * 2.异常
     * 3.关闭
     */
    private Integer status;

}
