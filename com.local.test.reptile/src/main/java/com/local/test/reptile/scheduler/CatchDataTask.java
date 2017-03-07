package com.local.test.reptile.scheduler;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.local.test.reptile.service.SpiderDataService;
import com.local.test.reptile.service.SpiderTypeService;
import com.local.test.reptile.webmagic.gameSky.news.SwxfNewsProcessor;

import us.codecraft.webmagic.Spider;

@Component
@Configurable
@EnableScheduling
public class CatchDataTask {

	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
	private SpiderTypeService spiderTypeService;
	
	@Autowired
	private SpiderDataService spiderDataService;
	
	/**
	 * 定时抓取 游牧星空 菜单
	 */
	@Scheduled(cron = "0/30 * * * * ?")
	public void pcGameTypeProcessorData() throws Exception {
		
//		logger.info("开始抓取 游牧星空 单机游戏菜单");
//		Spider.create(new PcGameTypeProcessor(spiderTypeService)).addUrl("http://www.gamersky.com/pcgame/").run();
//		logger.info("结束抓取 游牧星空 单机游戏菜单");
//		
//		logger.info("开始抓取 游牧星空 网络游戏菜单");
//		Spider.create(new InternetGameTypeProcessor(spiderTypeService)).addUrl("http://ol.gamersky.com/").run();
//		logger.info("结束抓取 游牧星空 网络游戏菜单");
	}
	
	/**
	 * 守望先锋 使命召唤
	 * 新闻资讯
	 */
	@Scheduled(cron = "0/10 * * * * ?")
	public void gameSkyData() throws Exception {
		
		
		Spider.create(new SwxfNewsProcessor(spiderDataService)).addUrl(gameSkyDataUrl("20894").toString()).run();
		
		Spider.create(new SwxfNewsProcessor(spiderDataService)).addUrl(gameSkyDataUrl("1034").toString()).run();
		
	}



	private StringBuffer gameSkyDataUrl(String nodeId) {
		String sysTimeStr = String.valueOf(System.currentTimeMillis());
		Map<String, String> map = Maps.newHashMap();
		map.put("type", "updatenodelabel");
		map.put("isCache", "true");
		map.put("cacheTime", "60");
		map.put("nodeId", nodeId);
		map.put("isNodeId", "true");
		map.put("page", "1");

		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("http://db2.gamersky.com/LabelJsonpAjax.aspx?");
		sBuffer.append("callback=jQuery" + RandomStringUtils.randomNumeric(21) + "_" + RandomStringUtils.randomNumeric(13) + "&jsondata=");
		sBuffer.append(org.apache.catalina.util.URLEncoder.DEFAULT.encode(new Gson().toJson(map)));
		sBuffer.append("&_=" + sysTimeStr);
		return sBuffer;
	}
}
