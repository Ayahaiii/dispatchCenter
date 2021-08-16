package com.monetware.dispatchcenter.business.dao;

import com.monetware.dispatchcenter.business.pojo.po.DcTaskType;
import com.monetware.dispatchcenter.system.base.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DcTaskTypeDao extends MyMapper<DcTaskType> {
}
