package com.monetware.dispatchcenter.business.service.manage;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.monetware.dispatchcenter.business.dao.DcServiceDao;
import com.monetware.dispatchcenter.business.dao.DcTaskTypeDao;
import com.monetware.dispatchcenter.business.dao.DcTaskDao;
import com.monetware.dispatchcenter.business.pojo.dto.manage.SearchTaskByParamDTO;
import com.monetware.dispatchcenter.business.pojo.dto.manage.SearchTaskDetailDTO;
import com.monetware.dispatchcenter.business.pojo.po.DcService;
import com.monetware.dispatchcenter.business.pojo.po.DcTask;
import com.monetware.dispatchcenter.business.pojo.po.DcTaskType;
import com.monetware.dispatchcenter.business.pojo.vo.manage.SearchTaskByParamVO;
import com.monetware.dispatchcenter.system.base.CommonIdParam;
import com.monetware.dispatchcenter.system.base.PageList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Cookie
 * @create 2020-09-21 11:53
 * @Description:
 */
@Slf4j
@Service
public class TaskManageService {

    @Autowired
    DcTaskDao dcTaskDao;

    @Autowired
    DcServiceDao dcServiceDao;

    @Autowired
    DcTaskTypeDao dcTaskApiDao;

    //=========================== Cookie Begin ===========================

    /**
     * @Author: Cookie
     * @Date: 2020-09-21 13:29
     * @Description: 条件查询任务
     */
    public PageList<SearchTaskByParamDTO> searchTaskByParam(SearchTaskByParamVO param) {
        Example example = new Example(DcTask.class);
        Example.Criteria criteria = example.createCriteria();
        //如果节点不为空,查询该节点下的所有service
        if (param.getNodeId() != null) {
            DcService dcService = new DcService();
            dcService.setNodeId(param.getNodeId());
            List<DcService> serviceList = dcServiceDao.select(dcService);
            List<Integer> serviceIdList = serviceList.stream().map(DcService::getId).collect(Collectors.toList());
            criteria.andIn("serviceId", serviceIdList);
        }
        if (param.getProjectType() != null) {
            criteria.andEqualTo("projectType", param.getProjectType());
        }
        if (param.getTaskType() != null) {
            criteria.andEqualTo("taskType", param.getTaskType());
        }
        if (param.getStatus() != null) {
            criteria.andEqualTo("status", param.getStatus());
        }
        Page page = PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<DcTask> taskList = dcTaskDao.selectByExample(example);
        List<SearchTaskByParamDTO> result = new ArrayList<>();
        //重组返回参数
        for (DcTask item : taskList) {
            SearchTaskByParamDTO dto = new SearchTaskByParamDTO();
            BeanUtils.copyProperties(item, dto);
            //查询task任务名称
            DcTaskType dcTaskApi = new DcTaskType();
            dcTaskApi.setProjectType(item.getProjectType());
            dcTaskApi.setTaskType(item.getTaskType());
            dcTaskApi = dcTaskApiDao.selectOne(dcTaskApi);
            if (dcTaskApi != null) {
                dto.setTaskName(dcTaskApi.getTaskName());
            }
            result.add(dto);
        }
        return new PageList<>(page, result);
    }

    /**
     * @Author: Cookie
     * @Date: 2020-09-21 16:34
     * @Description: 查询任务详情
     */
    public SearchTaskDetailDTO searchTaskDetail(CommonIdParam param) {
        //查询任务详情
        DcTask dcTask = dcTaskDao.selectByPrimaryKey(param.getId());
        SearchTaskDetailDTO result = new SearchTaskDetailDTO();
        BeanUtils.copyProperties(dcTask, result);
        //查询任务类型名称
        DcTaskType dcTaskApi = new DcTaskType();
        dcTaskApi.setTaskType(dcTask.getTaskType());
        dcTaskApi.setProjectType(dcTask.getProjectType());
        dcTaskApi = dcTaskApiDao.selectOne(dcTaskApi);
        if (dcTaskApi != null) {
            result.setTaskName(dcTaskApi.getTaskName());
        }
        return result;
    }

    //============================== 共用方法==============================


}
