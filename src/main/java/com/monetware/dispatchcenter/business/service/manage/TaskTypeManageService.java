package com.monetware.dispatchcenter.business.service.manage;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.monetware.dispatchcenter.business.dao.DcTaskTypeDao;
import com.monetware.dispatchcenter.business.pojo.dto.manage.SearchTaskTypeByParamDTO;
import com.monetware.dispatchcenter.business.pojo.dto.manage.SearchTaskTypeDetailDTO;
import com.monetware.dispatchcenter.business.pojo.po.DcTaskType;
import com.monetware.dispatchcenter.business.pojo.vo.manage.AddTaskTypeVO;
import com.monetware.dispatchcenter.business.pojo.vo.manage.SearchTaskTypeByParamVO;
import com.monetware.dispatchcenter.business.pojo.vo.manage.UpdateTaskTypeVO;
import com.monetware.dispatchcenter.system.base.CommonIdParam;
import com.monetware.dispatchcenter.system.base.ErrorCode;
import com.monetware.dispatchcenter.system.base.PageList;
import com.monetware.dispatchcenter.system.exception.ServiceException;
import com.monetware.dispatchcenter.system.util.BeanCopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cookie
 * @create 2020-09-21 17:43
 * @Description: taskApiManage相关方法
 */
@Slf4j
@Service
public class TaskTypeManageService {

    @Autowired
    DcTaskTypeDao dcTaskTypeDao;

    //=========================== Cookie Begin ===========================

    /**
     * @Author: Cookie
     * @Date: 2020-09-21 18:02
     * @Description: 新增任务类型
     */
    public Integer addTaskType(AddTaskTypeVO param) {
        //判断同一个projectType下是否已经添加过这个taskType
        DcTaskType dcTaskType = new DcTaskType();
        dcTaskType.setTaskType(param.getTaskType());
        dcTaskType.setProjectType(param.getProjectType());
        dcTaskType = dcTaskTypeDao.selectOne(dcTaskType);
        if (dcTaskType != null) {
            throw new ServiceException(ErrorCode.TASK_TYPE_EXIT);
        }
        //新增任务类型
        dcTaskType = new DcTaskType();
        BeanUtils.copyProperties(param, dcTaskType);
        return dcTaskTypeDao.insertSelective(dcTaskType);
    }

    /**
     * @Author: Cookie
     * @Date: 2020-09-21 18:11
     * @Description: 删除任务类型
     */
    public Integer deleteTaskType(CommonIdParam param) {
        return dcTaskTypeDao.deleteByPrimaryKey(param.getId());
    }

    /**
     * @Author: Cookie
     * @Date: 2020-09-21 18:17
     * @Description: 条件查询任务类型
     */
    public PageList<SearchTaskTypeByParamDTO> searchTaskTypeByParam(SearchTaskTypeByParamVO param) {
        //条件查询任务类型
        Example example = new Example(DcTaskType.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(param.getKeywords())) {
            criteria.andLike("taskName", "%" + param.getKeywords() + "%");
        }
        if (param.getProjectType() != null) {
            criteria.andEqualTo("projectType", param.getProjectType());
        }
        Page page = PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<DcTaskType> dcTaskTypeList = dcTaskTypeDao.selectByExample(example);
        //重组返回数据
        List<SearchTaskTypeByParamDTO> result = new ArrayList<>();
        BeanCopyUtil.copyListProperties(dcTaskTypeList, () -> new SearchTaskTypeByParamDTO());
        return new PageList<>(page, result);
    }


    /**
     * @Author: Cookie
     * @Date: 2020-09-21 18:38
     * @Description: 查看项目类型详情
     */
    public SearchTaskTypeDetailDTO searchTaskTypeDetail(CommonIdParam param) {
        DcTaskType dcTaskType = dcTaskTypeDao.selectByPrimaryKey(param.getId());
        SearchTaskTypeDetailDTO result = new SearchTaskTypeDetailDTO();
        BeanUtils.copyProperties(dcTaskType, result);
        return result;
    }

    /**
     * @Author: Cookie
     * @Date: 2020-09-21 18:54
     * @Description: 修改项目类型
     */
    public Integer updateTaskType(UpdateTaskTypeVO param) {
        //判断跟新后的projectType 和taskType 是否已经存在
        DcTaskType dcTaskType = new DcTaskType();
        dcTaskType.setTaskType(param.getTaskType());
        dcTaskType.setProjectType(param.getProjectType());
        dcTaskType = dcTaskTypeDao.selectOne(dcTaskType);
        if (dcTaskType != null && !dcTaskType.getId().equals(param.getId())) {
            throw new ServiceException(ErrorCode.TASK_TYPE_EXIT);
        }
        //跟新项目类型
        dcTaskType = dcTaskTypeDao.selectByPrimaryKey(param.getId());
        dcTaskType.setProjectType(param.getProjectType());
        dcTaskType.setTaskType(param.getTaskType());
        dcTaskType.setCancelUrl(param.getCancelUrl());
        dcTaskType.setDescription(param.getDescription());
        dcTaskType.setSchedulingUrl(param.getSchedulingUrl());
        dcTaskType.setTaskName(param.getTaskName());
        return dcTaskTypeDao.updateByPrimaryKey(dcTaskType);
    }

    //============================ Cookie End ============================

}
