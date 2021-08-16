package com.monetware.dispatchcenter.business.dao;

import com.monetware.dispatchcenter.business.pojo.po.DcAdmin;
import com.monetware.dispatchcenter.system.base.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DcAdminDao extends MyMapper<DcAdmin> {

}
