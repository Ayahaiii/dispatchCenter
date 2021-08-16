package com.monetware.dispatchcenter.business.service.manage;

import com.monetware.dispatchcenter.business.dao.DcServiceDao;
import com.monetware.dispatchcenter.business.dao.DcTaskDao;
import com.monetware.dispatchcenter.business.pojo.constant.ServiceConstant;
import com.monetware.dispatchcenter.business.pojo.constant.TaskConstant;
import com.monetware.dispatchcenter.business.pojo.dto.manage.SearchServiceByParamDTO;
import com.monetware.dispatchcenter.business.pojo.po.DcService;
import com.monetware.dispatchcenter.business.pojo.po.DcTask;
import com.monetware.dispatchcenter.business.pojo.vo.manage.AddServiceVO;
import com.monetware.dispatchcenter.business.pojo.vo.manage.SearchServiceByParamVO;
import com.monetware.dispatchcenter.business.pojo.vo.manage.UpdateServiceVO;
import com.monetware.dispatchcenter.system.base.CommonIdParam;
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
 * @create 2020-09-17 15:21
 * @Description:
 */
@Slf4j
@Service
public class ServiceManageService {

    @Autowired
    DcServiceDao dcServiceDao;

    @Autowired
    DcTaskDao dcTaskDao;

    //=========================== Cookie Begin ===========================

    /**
     * @Author: Cookie
     * @Date: 2020-09-17 15:22
     * @Description: 新增service
     */
    public Integer addService(AddServiceVO param) {
        DcService dcService = new DcService();
        BeanUtils.copyProperties(param, dcService);
        dcService.setStatus(ServiceConstant.STATUS_STOP);
        return dcServiceDao.insertSelective(dcService);
    }

    /**
     * @Author: Cookie
     * @Date: 2020-09-17 15:23
     * @Description: 条件查询service
     */
    public List<SearchServiceByParamDTO> searchServiceByParam(SearchServiceByParamVO param) {
        //条件查询service
        Example example = new Example(DcService.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("nodeId", param.getNodeId());
        example.orderBy("status").asc();
        List<DcService> serviceList = dcServiceDao.selectByExample(example);
        //返回结果
        List<SearchServiceByParamDTO> result = new ArrayList<>();
        for (DcService item : serviceList) {
            SearchServiceByParamDTO dto = new SearchServiceByParamDTO();
            BeanUtils.copyProperties(item, dto);
            //查询当前服务下所有运行的任务数量
            DcTask dcTask = new DcTask();
            dcTask.setServiceId(item.getId());
            dcTask.setStatus(TaskConstant.STATUS_RUNNING);
            dto.setTaskRunningNum(dcTaskDao.selectCount(dcTask));
            //查询当前服务所有运行异常的任务数量
            dcTask.setStatus(TaskConstant.STATUS_FAIL);
            dto.setTaskErrorNum(dcTaskDao.selectCount(dcTask));
            result.add(dto);
        }
        return result;
    }

    /**
     * @Author: Cookie
     * @Date: 2020-09-17 15:24
     * @Description: 禁用service
     */
    public Integer stopService(CommonIdParam param) {
        DcService dcService = dcServiceDao.selectByPrimaryKey(param.getId());
        dcService.setStatus(ServiceConstant.STATUS_STOP);
        return dcServiceDao.updateByPrimaryKeySelective(dcService);
    }

    /**
     * @Author: Cookie
     * @Date: 2020-09-17 15:25
     * @Description: 启用service
     */
    public Integer startService(CommonIdParam param) {
        DcService dcService = dcServiceDao.selectByPrimaryKey(param.getId());
        dcService.setStatus(ServiceConstant.STATUS_OK);
        return dcServiceDao.updateByPrimaryKeySelective(dcService);
    }

    /**
     * @Author: Cookie
     * @Date: 2020-09-17 15:27
     * @Description: 修改service
     */
    public Integer updateService(UpdateServiceVO param) {
        DcService dcService = dcServiceDao.selectByPrimaryKey(param.getId());
        dcService.setHeartBeatUrl(param.getHeartBeatUrl());
        dcService.setNodeId(param.getNodeId());
        dcService.setProjectType(param.getProjectType());
        dcService.setUrl(param.getUrl());
        return dcServiceDao.updateByPrimaryKey(dcService);
    }

    //============================ Cookie End ============================

}
