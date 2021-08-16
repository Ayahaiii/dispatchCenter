package com.monetware.dispatchcenter.business.pojo.vo.manage;

import com.monetware.dispatchcenter.system.base.PageParam;
import lombok.Data;

/**
 * @author Cookie
 * @create 2020-09-21 18:18
 * @Description: 条件查询任务类型传入VO
 */
@Data
public class SearchTaskTypeByParamVO extends PageParam {

    /**
     * 项目类型
     */
    private Integer projectType;

    /**
     * 关键词
     */
    private String keywords;

}
