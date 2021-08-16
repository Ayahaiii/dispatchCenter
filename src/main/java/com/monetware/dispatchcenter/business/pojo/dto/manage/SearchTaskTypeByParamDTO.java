package com.monetware.dispatchcenter.business.pojo.dto.manage;

import lombok.Data;

/**
 * @author Cookie
 * @create 2020-09-21 18:30
 * @Description:
 */
@Data
public class SearchTaskTypeByParamDTO {

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
     * 描述
     */
    private String description;

}
