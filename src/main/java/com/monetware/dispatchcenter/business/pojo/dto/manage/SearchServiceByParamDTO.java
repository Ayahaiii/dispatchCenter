package com.monetware.dispatchcenter.business.pojo.dto.manage;

import lombok.Data;

/**
 * @author Cookie
 * @create 2020-09-21 11:13
 * @Description: 条件查询serviceDTO
 */
@Data
public class SearchServiceByParamDTO {

    private Integer id;

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

    /**
     * 正在运行的任务数量
     */
    private Integer taskRunningNum;

    /**
     * 运行异常的任务数量
     */
    private Integer taskErrorNum;

}
