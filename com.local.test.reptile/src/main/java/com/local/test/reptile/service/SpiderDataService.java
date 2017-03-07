package com.local.test.reptile.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.local.test.reptile.dao.SpiderDataDao;
import com.local.test.reptile.pojo.po.SpiderData;
import com.local.test.reptile.pojo.qo.SpiderDataQo;
import com.shunwang.business.framework.bo.CrudBo;

@Service
public class SpiderDataService extends CrudBo<SpiderData, SpiderDataDao> {

	/**
	 * 查询数据是否存在
	 */
	public String findMenuId(SpiderDataQo qo){
		String id = null;
		List<SpiderData> query = this.query(qo);
		if(!CollectionUtils.isEmpty(query)){
			id = query.get(0).getId();
		}
		return id;
	}
}
