package com.local.test.reptile.pojo.qo;

import com.shunwang.business.framework.pojo.Grid;

public class PageQo extends Grid {

	private String order;
	private int limit = 10;
	private int offset = 1;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		setRp(limit);
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		setPage((offset + getLimit())/getLimit());
		this.offset = offset;
	}

}
