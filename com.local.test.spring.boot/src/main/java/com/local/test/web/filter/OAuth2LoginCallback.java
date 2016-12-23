package com.local.test.web.filter;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shunwang.back.servlet.OAuth2LoginCallbackServlet;



/**
 * ****************************
 * 版权所有：顺网科技 所有权利
 * 创建日期: 2015年9月7日 下午2:26:28
 * 创建作者：LiYueHong (yh.li@shunwang.com)
 * 类名称　：OAuth2LoginCallback.java
 * 版　　本：
 * 功　　能：
 * 最后修改：
 * 修改记录：
****************************************
 */
@WebServlet(name="OAuth2LoginCallback", urlPatterns={"/OAuth2LoginCallback"})
public class OAuth2LoginCallback extends OAuth2LoginCallbackServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6699699488274330740L;

	@Override
	protected void jumpMainPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect((new StringBuilder(String.valueOf(request.getContextPath()))).append("/index.jsp")
					.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void jumpNoPermisionPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect((new StringBuilder(String.valueOf(request.getContextPath()))).append("/noPermission.jsp").toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
