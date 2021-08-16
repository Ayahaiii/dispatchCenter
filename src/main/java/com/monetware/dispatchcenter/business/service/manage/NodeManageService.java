package com.monetware.dispatchcenter.business.service.manage;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.monetware.dispatchcenter.business.dao.DcNodeDao;
import com.monetware.dispatchcenter.business.dao.DcServiceDao;
import com.monetware.dispatchcenter.business.pojo.constant.NodeConstant;
import com.monetware.dispatchcenter.business.pojo.dto.manage.SearchNodeByParamDTO;
import com.monetware.dispatchcenter.business.pojo.po.DcNode;
import com.monetware.dispatchcenter.business.pojo.po.DcService;
import com.monetware.dispatchcenter.business.pojo.vo.manage.AddNodeVO;
import com.monetware.dispatchcenter.business.pojo.vo.manage.SearchNodeByParamVO;
import com.monetware.dispatchcenter.business.pojo.vo.manage.UpdateNodeVO;
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
 * @create 2020-09-14 17:48
 * @Description: 后台管理节点相关方法
 */
@Slf4j
@Service
public class NodeManageService {

    @Autowired
    DcNodeDao dcNodeDao;

    @Autowired
    DcServiceDao dcServiceDao;

    //=========================== Cookie Begin ===========================

    /**
     * @Author: Cookie
     * @Date: 2020-09-16 15:23
     * @Description: 新增节点
     */
    public Integer addNode(AddNodeVO param) {
        //判断当前节点是否已经存在
        DcNode dcNode = new DcNode();
        dcNode.setIp(param.getIp());
        dcNode.setPort(param.getPort());
        dcNode = dcNodeDao.selectOne(dcNode);
        if (dcNode != null) {
            throw new ServiceException(ErrorCode.NODE_EXIT);
        }
        //增加节点
        dcNode = new DcNode();
        BeanUtils.copyProperties(param, dcNode);
        dcNode.setStatus(NodeConstant.STATUS_STOP);
        return dcNodeDao.insertSelective(dcNode);
    }

//    /**
//     * @Author: Cookie
//     * @Date: 2020-09-16 17:19
//     * @Description: 删除节点
//     */
//    public Integer deleteNode(CommonIdParam param) {
//        //删除节点
//        return dcNodeDao.deleteByPrimaryKey(param.getId());
//    }

    /**
     * @Author: Cookie
     * @Date: 2020-09-14 17:50
     * @Description: 条件查询节点信息
     */
    public PageList<SearchNodeByParamDTO> searchNodeByParam(SearchNodeByParamVO param) {
        Page page = new PageHelper().startPage(param.getPageNum(), param.getPageSize());
        //条件查询节点信息
        DcNode node = new DcNode();
        node.setStatus(param.getStatus());
        List<DcNode> nodeList = dcNodeDao.select(node);
        //重组返回信息
//        List<SearchNodeByParamDTO> result = BeanCopyUtil.copyListProperties(nodeList, () -> new SearchNodeByParamDTO());
        List<SearchNodeByParamDTO> result = new ArrayList<>();
        for (DcNode item :nodeList){
            SearchNodeByParamDTO dto = new SearchNodeByParamDTO();
            BeanUtils.copyProperties(item,dto);
            //查找该节点下所有service
            Example example = new Example(DcService.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("nodeId", item.getId());
            example.orderBy("status").asc();
            List<DcService> serviceList = dcServiceDao.selectByExample(example);
            dto.setDcServiceList(serviceList);
            result.add(dto);
        }
        return new PageList<>(page, result);
    }

    /**
     * @Author: Cookie
     * @Date: 2020-09-16 14:07
     * @Description: 暂停节点
     */
    public Integer stopNode(CommonIdParam param) {
        DcNode node = new DcNode();
        node.setStatus(NodeConstant.STATUS_STOP);
        node.setId(param.getId());
        return dcNodeDao.updateByPrimaryKeySelective(node);
    }

    /**
     * @Author: Cookie
     * @Date: 2020-09-16 17:38
     * @Description: 启动节点
     */
    public Integer startNode(CommonIdParam param) {
        DcNode node = dcNodeDao.selectByPrimaryKey(param.getId());
        node.setStatus(NodeConstant.STATUS_OK);
        return dcNodeDao.updateByPrimaryKeySelective(node);
    }

    /**
     * @Author: Cookie
     * @Date: 2020-09-16 17:40
     * @Description: 修改节点信息
     */
    public Integer updateNode(UpdateNodeVO param) {
        //判断修改后的节点是否已经存在
        DcNode dcNode = new DcNode();
        dcNode.setIp(param.getIp());
        dcNode.setPort(param.getPort());
        dcNode = dcNodeDao.selectOne(dcNode);
        if (dcNode != null && !dcNode.getId().equals(param.getId())) {
            throw new ServiceException(ErrorCode.NODE_EXIT);
        }
        //修改节点信息
        dcNode = dcNodeDao.selectByPrimaryKey(param.getId());
        dcNode.setIp(param.getIp());
        dcNode.setPort(param.getPort());
        dcNode.setName(param.getName());
        dcNode.setDescription(param.getDescription());
        return dcNodeDao.updateByPrimaryKey(dcNode);
    }

    //============================ Cookie End ============================

}
