package com.local.test.reptile.pojo.qo;

import com.shunwang.business.framework.mybatis.annotion.SingleValue;

public class SpiderTaskQo extends PageQo {

	private Integer status;

	@SingleValue(column = "status", equal = "=")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}