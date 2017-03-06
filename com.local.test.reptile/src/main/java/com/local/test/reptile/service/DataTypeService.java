package com.local.test.reptile.service;

import org.springframework.stereotype.Service;

import com.local.test.reptile.dao.DataTypeDao;
import com.local.test.reptile.pojo.po.DataType;
import com.shunwang.business.framework.bo.CrudBo;

@Service
public class DataTypeService extends CrudBo<DataType, DataTypeDao> {

}
