/*
 * $Id$
 * Copyright (c)  by iCafeMavin Information Technology Inc. All right reserved.
 */
package com.local.test.web.filter;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.shunwang.back.common.UserContext;
import com.shunwang.back.rpc.response.PermistionQueryResponse;
import com.shunwang.business.framework.util.JsUtil;
import com.shunwang.permission.core.action.pojo.Action;
import com.shunwang.permission.core.common.util.IpUtil;

public abstract class PermissionValidateFilter implements Filter {
	protected static final Logger filterlog = Logger.getLogger("filterLog");
	protected static String noValidateSuffix = "_backuser";
	protected static String noPassJumpUrl = "index";
	protected static String jsessionidPrefix = ";jsessionid=";
	protected static String loginUrl = "index";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain) throws IOException,
			ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		String userName = (String) UserContext.getValueFromSession(httpServletRequest, UserContext.USER_NAME_KEY);
		String servletPath = processUrl(httpServletRequest.getServletPath());
		String requestUri = processUrl(httpServletRequest.getRequestURI());

		com.shunwang.permission.core.UserContext.getInstance().setHttpSession(httpServletRequest.getSession());
		com.shunwang.permission.core.UserContext.getInstance().setUserName(userName);

		String clientIp = IpUtil.getRealIPAddress(httpServletRequest);
		// 记录访问日志
		this.creatLog(httpServletRequest, clientIp, userName, servletPath, requestUri);

		if (userName == null && !(isExecutionUrl(servletPath) || isExecutionUrl(requestUri))) {
			doRedirect(httpServletRequest, httpServletResponse, loginUrl);
			return;
		}

		if (isExecutionUrl(servletPath) || isExecutionUrl(requestUri)) {
			filterchain.doFilter(request, response);
			return;
		}

		if (this.couldExcuteUrl(httpServletRequest, servletPath) || this.couldExcuteUrl(httpServletRequest, requestUri)) {
			filterchain.doFilter(request, response);
		} else {
			filterlog.info("couldExcuteUrl:urlStr=" + servletPath + ", requestUri=" + requestUri);
			doRedirect(httpServletRequest, httpServletResponse, noPassJumpUrl);
		}

	}

	private String processUrl(String urlStr) {
		urlStr = urlStr.substring(urlStr.indexOf("/", 1) + 1).replaceFirst("^/+", "");

		// 存在jessionid去除
		int jsessionidIndex = urlStr.indexOf(jsessionidPrefix);
		if (jsessionidIndex != -1) {
			urlStr = urlStr.substring(0, jsessionidIndex);
		}
		return urlStr;
	}

	private boolean isExecutionUrl(String urlStr) {
		List<String> listNoLoginPage = getNologinPage();
		return "".equals(urlStr) || noFilter(urlStr) || listNoLoginPage.contains(urlStr)
				|| urlStr.substring(0, urlStr.lastIndexOf(".")).endsWith(noValidateSuffix);
	}

	/**
	 * 不需要登录的页面
	 * @return
	 */
	protected abstract List<String> getNologinPage();

	/**
	 * 登录页面
	 * @return
	 */
	protected String getLoginUrl() {
		return loginUrl;
	}

	/**
	 * 没有权限页面
	 * @return
	 */
	protected String getNoPermissionUrl() {
		return noPassJumpUrl;
	}

	protected boolean noFilter(String urlStr) {
		return false;
	}

	protected void doRedirect(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String relativeUrl) throws IOException {
		String redirectUrl = httpServletRequest.getContextPath() + "/" + relativeUrl;
		// 是否为jquery ajax异步请求验证
		if (JsUtil.validateJqueryAjax(httpServletRequest)) {
			JSONObject jsonObj = new JSONObject();
			try {
				jsonObj.put("expression", "window.location.href='" + redirectUrl + "'");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			JsUtil.printJsScript(httpServletResponse, jsonObj.toString());
		} else {
			httpServletResponse.sendRedirect("http://localhost:8080/monitorObjects/testIndex");
		}
	}

	private boolean couldExcuteUrl(HttpServletRequest httpServletRequest, String url) {
		PermistionQueryResponse userPermissionCache = UserContext.getPermistion(httpServletRequest);
		//filterlog.info("UserContext.getInstance().getUserPermissionCache():" + userPermissionCache);
		if (userPermissionCache == null) {
			return false;
		}
		Set<String> tapUrls = userPermissionCache.getUserTapUrl();
		//filterlog.info("tapUrls:" + tapUrls);
		if (tapUrls == null) {
			return false;
		}
		for (String tapUrl : tapUrls) {
			if (tapUrl.contains(url)) {
				return true;
			}
		}
		Map<String, Action> actionMap = userPermissionCache.getActionsByUser();
		//filterlog.info("actionMap.size():" + actionMap==null?0:actionMap.size());
		if (actionMap == null) {
			return false;
		}
		boolean result = actionMap.containsKey(url);
		if (!result) {
			/*filterlog.info("###nopermission url ="+url);
			for(Entry<String, Action> entry:actionMap.entrySet()){
				filterlog.info("key="+ entry.getKey() + " ,value Action.Url="+entry.getValue().getUrl() +",Action.ActionKey"+entry.getValue().getActionKey()+",Action.ActionName"+entry.getValue().getActionName());
			}*/
		}
		return result;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * 记录用户访问日志
	 * 
	 * @param req
	 * @param clientIp
	 * @param username
	 * @param urlStr
	 */
	private void creatLog(HttpServletRequest req, String clientIp, String username, String servletPath,
			String requestUri) {
		try {
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("IP:" + clientIp + " ");
			strBuilder.append("servletPath:" + servletPath + " ");
			strBuilder.append("requestUri:" + requestUri + " ");
			Map paraMap = req.getParameterMap();
			Iterator it = paraMap.entrySet().iterator();
			StringBuilder paramBuilder = new StringBuilder();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String key = (String) entry.getKey();
				String[] values = (String[]) entry.getValue();
				String value = "[";
				for (String v : values) {
					if (v.length() <= 64)
						value += v + ",";
				}
				value += "]";
				paramBuilder.append(key + ":" + value + ",");
			}
			strBuilder.append("username:" + username + " ");
			strBuilder.append("param:" + paramBuilder.toString());
			filterlog.info(strBuilder.toString());
		} catch (Exception e) {
			filterlog.error(e.getMessage());
		}
	}
}
