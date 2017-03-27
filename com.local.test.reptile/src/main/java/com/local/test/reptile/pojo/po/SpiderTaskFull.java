package com.local.test.reptile.pojo.po;

import com.shunwang.business.framework.pojo.BasePojo;

public class SpiderTaskFull extends BasePojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String url;
	private String taskName;
	private Integer pageProcessId;
	private Integer status;
	private Integer typeId;
	private Integer catchType;
	private Integer startPageNum;
	private Integer endPageNum;
	private Integer nodeId;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	public Integer getCatchType() {
		return catchType;
	}

	public void setCatchType(Integer catchType) {
		this.catchType = catchType;
	}
	public Integer getStartPageNum() {
		return startPageNum;
	}

	public void setStartPageNum(Integer startPageNum) {
		this.startPageNum = startPageNum;
	}
	public Integer getEndPageNum() {
		return endPageNum;
	}

	public void setEndPageNum(Integer endPageNum) {
		this.endPageNum = endPageNum;
	}

	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	@Override
	public String toString() {
		return "SpiderTaskFull "+ 
				"[id=" + id +
				", url=" + url + 
				", taskName=" + taskName + 
				", pageProcessId=" + pageProcessId + 
				", status=" + status + 
				", typeId=" + typeId + 
				", catchType=" + catchType + 
				", startPageNum=" + startPageNum + 
				", endPageNum=" + endPageNum + 
		"]";
	}

}