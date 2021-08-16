package com.monetware.dispatchcenter.business.pojo.dto.manage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Cookie
 * @create 2020-09-21 14:22
 * @Description: 条件查询task返回DTO
 */
@Data
public class SearchTaskByParamDTO {

    /**
     * 项目类型：
     * 1.云采集，2.云文析，3.云统计
     */
    private Integer projectType;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 状态：
     * 1：未调度，2:运行中，3:运行成功，4：运行异常，5:已取消
     */
    private Integer status;

    /**
     * 调度时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date schedulingTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}
