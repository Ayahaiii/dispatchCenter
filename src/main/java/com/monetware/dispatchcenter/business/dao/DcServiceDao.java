package com.monetware.dispatchcenter.business.dao;

import com.monetware.dispatchcenter.business.pojo.po.DcService;
import com.monetware.dispatchcenter.system.base.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DcServiceDao extends MyMapper<DcService> {
}
