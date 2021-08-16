package com.monetware.dispatchcenter.business.pojo.vo.manage;

import lombok.Data;

/**
 * @author Cookie
 * @create 2020-09-18 14:57
 * @Description: 新增服务传入参数
 */
@Data
public class AddServiceVO {

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
