package com.local.test.reptile.dao;

import org.apache.ibatis.annotations.Mapper;

import com.local.test.reptile.pojo.po.SpiderTask;
import com.shunwang.business.framework.dao.CrudDao;
@Mapper
public interface SpiderTaskDao extends CrudDao<SpiderTask> {

}