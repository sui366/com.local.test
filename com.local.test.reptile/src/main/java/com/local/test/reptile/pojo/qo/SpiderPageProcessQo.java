package com.local.test.reptile.pojo.qo;

import com.shunwang.business.framework.mybatis.annotion.SingleValue;

public class SpiderPageProcessQo extends PageQo {

	
	private String id;

	@SingleValue(column = "id", equal = "=")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}