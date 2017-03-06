package com.local.test.reptile.pojo.po;

import com.shunwang.business.framework.pojo.BasePojo;

public class DataType extends BasePojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer sourceType;
	private Long oneLevel;
	private Long twoLevel;
	private String twoLevelUrl;
	private Long threeLevel;
	private String threeLevelUrl;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	public Long getOneLevel() {
		return oneLevel;
	}

	public void setOneLevel(Long oneLevel) {
		this.oneLevel = oneLevel;
	}
	public Long getTwoLevel() {
		return twoLevel;
	}

	public void setTwoLevel(Long twoLevel) {
		this.twoLevel = twoLevel;
	}
	public String getTwoLevelUrl() {
		return twoLevelUrl;
	}

	public void setTwoLevelUrl(String twoLevelUrl) {
		this.twoLevelUrl = twoLevelUrl;
	}
	public Long getThreeLevel() {
		return threeLevel;
	}

	public void setThreeLevel(Long threeLevel) {
		this.threeLevel = threeLevel;
	}
	public String getThreeLevelUrl() {
		return threeLevelUrl;
	}

	public void setThreeLevelUrl(String threeLevelUrl) {
		this.threeLevelUrl = threeLevelUrl;
	}

	@Override
	public String toString() {
		return "DataType "+ 
				"[id=" + id +
				", sourceType=" + sourceType + 
				", oneLevel=" + oneLevel + 
				", twoLevel=" + twoLevel + 
				", twoLevelUrl=" + twoLevelUrl + 
				", threeLevel=" + threeLevel + 
				", threeLevelUrl=" + threeLevelUrl + 
		"]";
	}

}