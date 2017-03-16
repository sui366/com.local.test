package com.local.test.reptile.pojo.qo;

import org.apache.commons.lang3.StringUtils;

import com.shunwang.business.framework.mybatis.annotion.SingleValue;

public class SpiderDataQo extends PageQo {
	private String id;
	private Integer taskId;
	private Integer typeId;
	private String title;
	private String titleLike;

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

	@SingleValue(column = "title", equal = "like")
	public String getTitleLike() {
		if(StringUtils.isNotBlank(titleLike)){
			setTitleLike("%"+titleLike.trim()+"%");
		}
		return titleLike;
	}

	public void setTitleLike(String titleLike) {
		this.titleLike = titleLike;
	}

	@SingleValue(column = "type_id", equal = "=")
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	
}