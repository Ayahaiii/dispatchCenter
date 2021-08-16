package com.monetware.dispatchcenter.business.pojo.vo.manage;

import com.monetware.dispatchcenter.system.base.PageParam;
import lombok.Data;

/**
 * @author Cookie
 * @create 2020-09-14 17:51
 * @Description: 条件查询节点传入VO
 */
@Data
public class SearchNodeByParamVO extends PageParam {

    /**
     * 状态：
     * 1.正常
     * 2.异常
     */
    private Integer status;

}
