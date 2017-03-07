package com.local.test.reptile.pojo.qo;

import com.shunwang.business.framework.mybatis.annotion.SingleValue;

public class SpiderDataQo extends PageQo {

	private String id;
	private Integer taskId;
	private String title;

	@SingleValue(column = "id", equal = "=")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@SingleValue(column = "task_id", equal = "=")
	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	@SingleValue(column = "title", equal = "=")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}