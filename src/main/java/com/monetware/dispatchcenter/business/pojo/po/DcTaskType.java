package com.monetware.dispatchcenter.business.pojo.po;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author Cookie
 * @create 2020-09-11 11:43
 * @Description: 任务调用api表
 */
@Data
public class DcTaskType {

    @Id
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
