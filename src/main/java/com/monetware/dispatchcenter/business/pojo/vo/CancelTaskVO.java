package com.monetware.dispatchcenter.business.pojo.vo;

import lombok.Data;

/**
 * @author Cookie
 * @create 2020-09-14 13:39
 * @Description: 取消任务传入参数
 */
@Data
public class CancelTaskVO {

    /**
     * 任务id
     */
    private Integer taskId;


    /**
     * 原因
     */
    private String reason;

}
