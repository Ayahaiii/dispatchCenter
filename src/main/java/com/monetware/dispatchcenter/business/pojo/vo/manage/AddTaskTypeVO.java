package com.monetware.dispatchcenter.business.pojo.vo.manage;

import lombok.Data;

/**
 * @author Cookie
 * @create 2020-09-21 17:49
 * @Description: 新增TaskApi传入参数
 */
@Data
public class AddTaskTypeVO {

    /**
     * 项目类型：
     * 1.云采集，2.云文析，3.云统计
     */
    private Integer projectType;

    /**
     * 任务类型
     */
    private Integer taskType;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 调度url
     */
    private String schedulingUrl;

    /**
     * 取消url
     */
    private String cancelUrl;

    /**
     * 描述
     */
    private String description;

}
