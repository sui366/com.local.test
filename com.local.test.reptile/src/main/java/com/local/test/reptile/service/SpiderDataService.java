package com.local.test.reptile.service;

import org.springframework.stereotype.Service;

import com.local.test.reptile.dao.SpiderDataDao;
import com.local.test.reptile.pojo.po.SpiderData;
import com.shunwang.business.framework.bo.CrudBo;

@Service
public class SpiderDataService extends CrudBo<SpiderData, SpiderDataDao> {

}
