package com.monetware.dispatchcenter.business.pojo.vo.manage;

import lombok.Data;

/**
 * @author Cookie
 * @create 2020-09-21 10:47
 * @Description: 修改服务service传入VO
 */
@Data
public class UpdateServiceVO {

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

}
