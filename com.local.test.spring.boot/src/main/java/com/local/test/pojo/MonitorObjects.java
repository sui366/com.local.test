package com.local.test.pojo;

import java.util.Date;

public class MonitorObjects {
	//
	private Long id;
	//
	private Integer productType;
	//
	private Integer procType;
	//
	private String addr;
	//
	private Integer isOpen;
	//
	private String updateUser;
	//
	private Date updateTime;

	private Integer ispType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public Integer getProcType() {
		return procType;
	}

	public void setProcType(Integer procType) {
		this.procType = procType;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getIspType() {
		return ispType;
	}

	public void setIspType(Integer ispType) {
		this.ispType = ispType;
	}
}
