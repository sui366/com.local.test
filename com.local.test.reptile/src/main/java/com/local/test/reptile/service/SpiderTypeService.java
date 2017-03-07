package com.local.test.reptile.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.local.test.reptile.dao.SpiderTypeDao;
import com.local.test.reptile.pojo.po.SpiderType;
import com.local.test.reptile.pojo.qo.SpiderTypeQo;
import com.shunwang.business.framework.bo.CrudBo;

@Service
public class SpiderTypeService extends CrudBo<SpiderType, SpiderTypeDao> {

	/**
	 * 查询菜单是否存在
	 */
	public Integer findMenuId(SpiderTypeQo qo){
		Integer id = null;
		List<SpiderType> query = this.query(qo);
		if(!CollectionUtils.isEmpty(query)){
			id = query.get(0).getId();
		}
		return id;
	}
}
