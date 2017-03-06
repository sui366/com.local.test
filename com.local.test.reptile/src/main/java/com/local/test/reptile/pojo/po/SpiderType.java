package com.local.test.reptile.pojo.po;

import com.shunwang.business.framework.pojo.BasePojo;

public class SpiderType extends BasePojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer platformId;
	private String levelName;
	private String levelUrl;
	private Integer parentLevelId;
	private Integer levelType;

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
	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getLevelUrl() {
		return levelUrl;
	}

	public void setLevelUrl(String levelUrl) {
		this.levelUrl = levelUrl;
	}
	public Integer getParentLevelId() {
		return parentLevelId;
	}

	public void setParentLevelId(Integer parentLevelId) {
		this.parentLevelId = parentLevelId;
	}
	public Integer getLevelType() {
		return levelType;
	}

	public void setLevelType(Integer levelType) {
		this.levelType = levelType;
	}

	@Override
	public String toString() {
		return "SpiderType "+ 
				"[id=" + id +
				", platformId=" + platformId + 
				", levelName=" + levelName + 
				", levelUrl=" + levelUrl + 
				", parentLevelId=" + parentLevelId + 
				", levelType=" + levelType + 
		"]";
	}

}