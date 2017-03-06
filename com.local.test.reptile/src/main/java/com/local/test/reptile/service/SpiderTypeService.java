package com.local.test.reptile.service;

import org.springframework.stereotype.Service;

import com.local.test.reptile.dao.SpiderTypeDao;
import com.local.test.reptile.pojo.po.SpiderType;
import com.shunwang.business.framework.bo.CrudBo;

@Service
public class SpiderTypeService extends CrudBo<SpiderType, SpiderTypeDao> {

}
