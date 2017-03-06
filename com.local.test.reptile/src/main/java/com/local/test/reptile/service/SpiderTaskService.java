package com.local.test.reptile.service;

import org.springframework.stereotype.Service;

import com.local.test.reptile.dao.SpiderTaskDao;
import com.local.test.reptile.pojo.po.SpiderTask;
import com.shunwang.business.framework.bo.CrudBo;

@Service
public class SpiderTaskService extends CrudBo<SpiderTask, SpiderTaskDao> {

}
