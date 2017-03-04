/*
 * $Id$
 * Copyright (c)  by iCafeMavin Information Technology Inc. All right reserved.
 */
package com.local.test.web.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shunwang.permission.core.common.cache.SystemCache;


@WebFilter(filterName="ValidateFilter",urlPatterns={"*.do", "*.jsp", "*.action", "*.shtml"})
public class ValidateFilter extends PermissionValidateFilter {

	private static final String ERROR_JSP = "error.vm";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain) throws IOException,
			ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		try {
			super.doFilter(request, response, filterchain);
		} catch (Exception e) {
			filterlog.error(e.getMessage(), e);
			doRedirect(httpServletRequest, httpServletResponse, ERROR_JSP);
		}

	}

	@Override
	protected List<String> getNologinPage() {
		return CacheContext.getNoLoginPage();
	}

	@Override
	protected boolean noFilter(String urlStr) {
		return SystemCache.noFilter(urlStr);
	}
}