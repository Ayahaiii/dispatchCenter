package com.monetware.dispatchcenter.business.pojo.vo.manage;

import lombok.Data;

/**
 * @author Cookie
 * @create 2020-09-21 18:55
 * @Description: 跟新项目类型VO
 */
@Data
public class UpdateTaskTypeVO {

    private Integer id;

    /**
     * 项目类型
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
