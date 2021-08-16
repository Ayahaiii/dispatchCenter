package com.monetware.dispatchcenter.business.pojo.constant;

/**
 * @author Cookie
 * @create 2020-09-14 13:34
 * @Description: 任务常量类
 */
public class TaskConstant {

    /**
     * 未调度
     */
    public static final Integer STATUS_NO_RUN = 1;

    /**
     * 运行中
     */
    public static final Integer STATUS_RUNNING = 2;

    /**
     * 运行成功
     */
    public static final Integer STATUS_SUCCESS= 3;

    /**
     * 运行失败
     */
    public static final Integer STATUS_FAIL = 4;

    /**
     * 取消调度
     */
    public static final Integer STATUS_CANCEL = 5;
}
