package com.local.test.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {




	@RequestMapping(value = { "/", "/index.shtml", "/index.jsp" })
	public ModelAndView root(HttpServletRequest request) throws Exception {
		
		return new ModelAndView("index");
	}

	@RequestMapping("/home.shtml")
	public ModelAndView home(HttpServletRequest request) throws Exception {


		return new ModelAndView("pages/home");
	}
	
	
	/**
	 * 获取服务器当前时间
	 */
	@RequestMapping("/sysTime.action")
	@ResponseBody
	public Date sysTime() {
		return new Date();
	}

	/**
	 * 登出
	 */
	@RequestMapping("/logOut.action")
	@ResponseBody
	public void logOut(HttpServletRequest request) {
		request.getSession().invalidate();
	}
}