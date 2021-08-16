package com.monetware.dispatchcenter.business.pojo.po;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

/**
 * @author Cookie
 * @create 2020-09-11 11:38
 * @Description: 任务表
 */
@Data
public class DcTask {

    @Id
    private Integer id;

    /**
     * 服务id
     */
    private Integer serviceId;

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
     * 项目id
     */
    private Integer projectId;

    /**
     * 传递参数
     */
    private String parameters;

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
     * 状态：
     * 1：未调度，2:运行中，3:运行成功，4：运行异常，5:已取消
     */
    private Integer status;

    /**
     * 取消原因
     */
    private String cancelReason;

    /**
     * 创建用户
     */
    private Integer createUser;

    /**
     * 调度时间
     */
    private Date schedulingTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 创建时间
     */
    private Date createTime;

}
