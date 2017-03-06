package com.local.test.reptile.pojo.po;

import com.shunwang.business.framework.pojo.BasePojo;

public class SpiderPlatform extends BasePojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long platformId;
	private String name;

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SpiderPlatform "+ 
				"[platformId=" + platformId +
				", name=" + name + 
		"]";
	}

}