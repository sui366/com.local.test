package com.local.test.reptile.pojo.po;

import com.shunwang.business.framework.pojo.BasePojo;

public class SpiderTask extends BasePojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer platformId;
	private String url;
	private String taskName;
	private Integer pageProcessId;
	private Integer status;
	private Integer typeId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getPageProcessId() {
		return pageProcessId;
	}

	public void setPageProcessId(Integer pageProcessId) {
		this.pageProcessId = pageProcessId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "SpiderTask " + "[id=" + id + ", platformId=" + platformId + ", url=" + url + ", taskName=" + taskName + ", pageProcessId=" + pageProcessId + ", status=" + status + "]";
	}

}