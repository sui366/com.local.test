package com.local.test.reptile.dao;

import org.apache.ibatis.annotations.Mapper;

import com.local.test.reptile.pojo.po.DataType;
import com.shunwang.business.framework.dao.CrudDao;
@Mapper
public interface DataTypeDao extends CrudDao<DataType> {

}