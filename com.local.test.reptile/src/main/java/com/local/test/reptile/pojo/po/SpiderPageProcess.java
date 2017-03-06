package com.local.test.reptile.pojo.po;

import com.shunwang.business.framework.pojo.BasePojo;

public class SpiderPageProcess extends BasePojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String processClass;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getProcessClass() {
		return processClass;
	}

	public void setProcessClass(String processClass) {
		this.processClass = processClass;
	}

	@Override
	public String toString() {
		return "SpiderPageProcess "+ 
				"[id=" + id +
				", name=" + name + 
				", processClass=" + processClass + 
		"]";
	}

}