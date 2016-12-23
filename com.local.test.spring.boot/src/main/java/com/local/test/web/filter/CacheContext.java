package com.local.test.web.filter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

import com.shunwang.business.framework.util.StringUtil;

/**
 * @Description 缓存 
 * @author gc.nie
 * @date 2014-3-27 下午7:23:42
 * @Copyright Copyright (c) 2013 Shunwang,Inc. All Rights Reserved.
 */
public class CacheContext implements ApplicationContextAware, ServletContextAware {
	private static final Logger log = Logger.getLogger(CacheContext.class);
	private static ApplicationContext context;
	private static String SYSTEM_OS_NAME = "";
	private static final List<String> noLoginPage = new ArrayList<String>();
	private static final String filterLoginPolicyFile = "/WEB-INF/filterLoginPolicy.xml";
	private static String PROJECT_PATH = "";

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		context = arg0;
	}

	@Override
	public void setServletContext(ServletContext context) {
		log.info("init ServletContext start !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//		context.setAttribute("localPath", PathHelper.getLocalUrl());
//		context.setAttribute("staticPath", PathHelper.getUploadUrl());
		loadNoFilterList();
		PROJECT_PATH = context.getRealPath("/");

		log.info("init ServletContext end !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}

	/**
	 * 获取spring bean
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	/**
	 * 判断当前操作系统是否是windows
	 * @return
	 */
	public static boolean isWindowsOs() {
		if (StringUtil.isNullStr(SYSTEM_OS_NAME)) {
			SYSTEM_OS_NAME = System.getProperty("os.name").toLowerCase();
		}
		return SYSTEM_OS_NAME.startsWith("window");
	}

	public static List<String> getNoLoginPage() {
		return noLoginPage;
	}

	public static String getProjectPath() {
		return PROJECT_PATH;
	}

	private void loadNoFilterList() {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new File(context.getResource("").getFile().getAbsolutePath()
					+ filterLoginPolicyFile));
			Element filters = doc.getRootElement();
			List<?> filterList = filters.element("actionWirteNames").elements("filter");
			for (Iterator<?> iterator = filterList.iterator(); iterator.hasNext();) {
				Element page = (Element) iterator.next();
				noLoginPage.add(page.getTextTrim());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}
}
