package com.local.test.reptile.service;

import org.springframework.stereotype.Service;

import com.local.test.reptile.dao.DataContentDao;
import com.local.test.reptile.pojo.po.DataContent;
import com.shunwang.business.framework.bo.CrudBo;

@Service
public class DataContentService extends CrudBo<DataContent, DataContentDao> {

}
