package com.monetware.dispatchcenter.business.pojo.vo;

import lombok.Data;

/**
 * @author Cookie
 * @create 2020-09-14 13:15
 * @Description: 新增任务传入VO
 */
@Data
public class AddTaskVO {

    /**
     * 项目类型：
     * 1.云采集，2.云文析，3.云统计
     */
    private Integer projectType;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 传递参数
     */
    private String parameters;

    /**
     * 任务类型
     */
    private Integer taskType;

    /**
     * 角色（各个项目角色会有不同）
     */
    private Integer role;

    /**
     * 任务优先级，视调度策略而定会参与调度
     */
    private Integer priority;

    /**
     * 任务量，视调度策略而定会参与调度
     */
    private Integer quantity;

    /**
     * 创建用户
     */
    private Integer createUser;


}
