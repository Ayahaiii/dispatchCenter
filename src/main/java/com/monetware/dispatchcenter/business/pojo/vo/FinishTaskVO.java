package com.monetware.dispatchcenter.business.pojo.vo;

import lombok.Data;

/**
 * @author Cookie
 * @create 2020-09-14 17:25
 * @Description: 结束任务传入参数
 */
@Data
public class FinishTaskVO {

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
     * 任务类型
     */
    private Integer taskType;

}
