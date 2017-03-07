package com.local.test.reptile.pojo.qo;

import com.shunwang.business.framework.mybatis.annotion.SingleValue;

public class SpiderTypeQo extends PageQo {

	private Integer id;
	private Integer platformId;
	private String levelName;
	private String levelUrl;
	private Integer parentLevelId;
	private Integer levelType;

	@SingleValue(column = "id", equal = "=")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@SingleValue(column = "platform_id", equal = "=")
	public Integer getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}

	@SingleValue(column = "level_name", equal = "=")
	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	@SingleValue(column = "level_url", equal = "=")
	public String getLevelUrl() {
		return levelUrl;
	}

	public void setLevelUrl(String levelUrl) {
		this.levelUrl = levelUrl;
	}

	@SingleValue(column = "parent_level_id", equal = "=")
	public Integer getParentLevelId() {
		return parentLevelId;
	}

	public void setParentLevelId(Integer parentLevelId) {
		this.parentLevelId = parentLevelId;
	}

	@SingleValue(column = "level_type", equal = "=")
	public Integer getLevelType() {
		return levelType;
	}

	public void setLevelType(Integer levelType) {
		this.levelType = levelType;
	}

}