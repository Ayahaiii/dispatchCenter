package com.monetware.dispatchcenter.business.pojo.vo.manage;

import com.monetware.dispatchcenter.system.base.PageParam;
import lombok.Data;

/**
 * @author Cookie
 * @create 2020-09-21 14:02
 * @Description: 条件查询任务传入VO
 */
@Data
public class SearchTaskByParamVO extends PageParam {

    /**
     * 节点id
     */
    private Integer nodeId;

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
     * 状态：
     * 1：未调度，2:运行中，3:运行成功，4：运行异常，5:已取消
     */
    private Integer status;

}
