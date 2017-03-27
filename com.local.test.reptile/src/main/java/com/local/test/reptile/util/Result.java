package com.local.test.reptile.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.shunwang.business.framework.pojo.Page;

/**
 * The Class Result.
 */
@SuppressWarnings("serial")
public class Result implements Serializable {


	/** 分页查询出来的记录集合. */
	private Collection<?> dataList;

	/** 数据集. */
	private Map<String, Object> data = new HashMap<String, Object>();

	/** datatables分页大小. */
	protected Integer pageSize = 0;

	/** datatables分页页数. */
	protected Integer pageNum = 0;
	
	/** datatables 记录数. */
	protected Integer totalNum = 0;

	/** datatables请求校验码. */
	protected String sEcho = "0";

	/** The msg code. */
	private String msgCode = null;

	/** The message. */
	private String message = null;

	/** The success. */
	private boolean success = true;
	
	public Result() {

	}
	
	public Result(HttpServletRequest request) {
		String activeLi = request.getParameter("activeLi");
		String activeMenu = request.getParameter("activeMenu");
		if (StringUtils.isNotBlank(activeLi)) {
			request.getSession().setAttribute("activeLi", activeLi);
		}
		if (StringUtils.isNotBlank(activeLi)) {
			request.getSession().setAttribute("activeMenu", activeMenu);
		}
	}

	public Result(Collection<?> list, HttpServletRequest request) {
		String sEcho = request.getParameter("sEcho");
		if (StringUtils.isNotBlank(sEcho)) {
			this.setsEcho(sEcho.trim());
		}
		this.setDataList(list);
	}

	public Result(Page page, HttpServletRequest request) {
		String sEcho = request.getParameter("sEcho");
		if (StringUtils.isNotBlank(sEcho)) {
			this.setsEcho(sEcho.trim());
		}
		this.setDataList(page.getRows());
		this.setPageSize(page.getRp());
		this.setTotalNum(page.getTotal());
	}

	/**
	 * Gets the list data.
	 *
	 * @return the list data
	 */
	public Collection<?> getDataList() {
		return dataList;
	}

	public Map<String, Object> getData() {
		return data;
	}

	/**
	 * Gets the i total display records.
	 *
	 * @return the i total display records
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * Gets the i total records.
	 *
	 * @return the i total records
	 */
	public Integer getPageNum() {
		return pageNum;
	}

	public String getMsgCode() {
		return msgCode;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Gets the s echo.
	 *
	 * @return the s echo
	 */
	public String getsEcho() {
		return sEcho;
	}

	/**
	 * Gets the value.
	 *
	 * @param key
	 *            the key
	 * @return the value
	 */
	public Object getValue(String key) {
		if (StringUtils.isBlank(key)) {
			return null;
		}

		return data.get(key.trim());
	}

	/**
	 * Checks if is success.
	 *
	 * @return true, if is success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Sets the list data.
	 *
	 * @param dataList
	 *            the new list data
	 */
	public void setDataList(Collection<?> dataList) {
		this.dataList = dataList;
	}

	/**
	 * Sets the i total display records.
	 *
	 * @param iTotalDisplayRecords
	 *            the new i total display records
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * Sets the i total records.
	 *
	 * @param iTotalRecords
	 *            the new i total records
	 */
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	/**
	 * Sets the message.
	 *
	 * @param message
	 *            the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Sets the s echo.
	 *
	 * @param sEcho
	 *            the new s echo
	 */
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	/**
	 * Sets the success.
	 *
	 * @param success
	 *            the new success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	
	/**
	 * Sets the value.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public void setValue(String key, Object value) {
		if (StringUtils.isNotBlank(key) && null != value) {
			data.put(key, value);
		}
	}

	public void setFailureMsg(String msgCode, String message) {
		setSuccess(false);
		setMsgCode(msgCode);
		setMessage(message);
	}

	public void setPage(Page page, HttpServletRequest request) {
		String sEcho = request.getParameter("sEcho");
		if (StringUtils.isNotBlank(sEcho)) {
			this.setsEcho(sEcho.trim());
		}
		this.setsEcho("1");
		this.setDataList(page.getRows());
		this.setPageSize(page.getRp());
		this.setTotalNum(page.getTotal());
	}
}
