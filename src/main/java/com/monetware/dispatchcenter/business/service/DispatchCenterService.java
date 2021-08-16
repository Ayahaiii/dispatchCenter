package com.monetware.dispatchcenter.business.service;

import com.monetware.dispatchcenter.business.dao.DcTaskDao;
import com.monetware.dispatchcenter.business.pojo.constant.TaskConstant;
import com.monetware.dispatchcenter.business.pojo.po.DcTask;
import com.monetware.dispatchcenter.business.pojo.vo.AddTaskVO;
import com.monetware.dispatchcenter.business.pojo.vo.CancelTaskVO;
import com.monetware.dispatchcenter.business.pojo.vo.FinishTaskVO;
import com.monetware.dispatchcenter.system.base.ErrorCode;
import com.monetware.dispatchcenter.system.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Cookie
 * @create 2020-09-14 10:19
 * @Description: 调度中心service
 */
@Slf4j
@Service
public class DispatchCenterService {

    @Autowired
    DcTaskDao dcTaskDao;

    //=========================== Cookie Begin ===========================

    /**
     * @Author: Cookie
     * @Date: 2020-09-14 13:15
     * @Description: 新增任务
     */
    public Integer addTask(AddTaskVO param) {
        //判断项目中是否存在相同projectType+projectId+taskType 的未执行任务
        DcTask dcTask = new DcTask();
        dcTask.setProjectType(param.getProjectType());
        dcTask.setProjectId(param.getProjectId());
        dcTask.setTaskType(param.getTaskType());
        dcTask.setStatus(TaskConstant.STATUS_NO_RUN);
        dcTask = dcTaskDao.selectOne(dcTask);
        if (dcTask != null) {
            throw new ServiceException(ErrorCode.TASK_ADD_WRONG, "任务已存在");
        }
        //新增任务
        dcTask = new DcTask();
        BeanUtils.copyProperties(param, dcTask);
        dcTask.setStatus(TaskConstant.STATUS_NO_RUN);
        dcTask.setCreateTime(new Date());
        return dcTaskDao.insertSelective(dcTask);
    }

    /**
     * @Author: Cookie
     * @Date: 2020-09-14 13:38
     * @Description: 取消任务
     */
    public Integer cancelTask(CancelTaskVO param) {
        DcTask dcTask = dcTaskDao.selectByPrimaryKey(param.getTaskId());
        if (!dcTask.getStatus().equals(TaskConstant.STATUS_NO_RUN)) {
            //如果当前任务不为等待调度中，需要返回提示星系
            throw new ServiceException(ErrorCode.TASK_CANCEL_WRONG, "任务状态不为等待调度，不可暂停");
        }
        dcTask.setStatus(TaskConstant.STATUS_CANCEL);
        return dcTaskDao.updateByPrimaryKeySelective(dcTask);
    }

    /**
     * @Author: Cookie
     * @Date: 2020-09-14 14:19
     * @Description: 结束任务(成功)
     */
    public Integer finishTaskSuccess(FinishTaskVO param) {
        //查询任务(只有运行重的项目可以结束)
        DcTask dcTask = new DcTask();
        dcTask.setProjectType(param.getProjectType());
        dcTask.setProjectId(param.getProjectId());
        dcTask.setTaskType(param.getTaskType());
        dcTask.setStatus(TaskConstant.STATUS_RUNNING);
        dcTask = dcTaskDao.selectOne(dcTask);
        if (dcTask == null) {
            throw new ServiceException(ErrorCode.TASK_FINISH_WRONG, "未找到对应在分析中的任务");
        }
        dcTask.setEndTime(new Date());
        dcTask.setStatus(TaskConstant.STATUS_SUCCESS);
        return dcTaskDao.updateByPrimaryKeySelective(dcTask);
    }



    //============================ Cookie End ============================

}
