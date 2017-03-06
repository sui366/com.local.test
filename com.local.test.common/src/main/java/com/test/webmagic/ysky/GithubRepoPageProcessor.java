package com.test.webmagic.ysky;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class GithubRepoPageProcessor implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    @Override
    public void process(Page page) { 
    	

    	String rawText = page.getRawText();
    	String regex = "(jQuery\\d+_\\d+\\()([\\S|\\s]+)(\\);)";
    	String replaceAll = rawText.replaceAll(regex, "$2");
    	
    	JSONObject jsonObject = JSONObject.parseObject(replaceAll);
    	Object object = jsonObject.get("body");
    	
    	Document doc = Jsoup.parseBodyFragment(object.toString());
    	Element body = doc.body();
    	
    	Elements elementsLi = body.getElementsByTag("li");
    	
    	for(Element element:elementsLi){
    		Elements elementsLiTt = element.getElementsByClass("tt");
    		Elements elementTitle = elementsLiTt.get(0).getElementsByAttribute("title");
    		System.out.println(elementTitle.get(0).childNode(0).toString());
    	}
    	
    	
    	
//    	System.out.println(object);
    	
    	
    }

    @Override
    public Site getSite() {
        return site;
    }

	/**
	 * 获取主体数据 (img src：200*112.jpg图片地址 title：标题 txt：简介 time：发布时间/)
	 */
	@Test
	public void updatenodelabel() {
		String sysTimeStr = String.valueOf(System.currentTimeMillis());
		Map<String, String> map = Maps.newHashMap();
		map.put("type", "updatenodelabel");
		map.put("isCache", "true");
		map.put("cacheTime", "60");
		map.put("nodeId", "11007");
		map.put("isNodeId", "true");
		map.put("page", "1");// 获取第几页的数据

		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("http://db2.gamersky.com/LabelJsonpAjax.aspx?");
		sBuffer.append("callback=jQuery" + RandomStringUtils.randomNumeric(21) + "_" + RandomStringUtils.randomNumeric(13) + "&jsondata=");
		sBuffer.append(org.apache.catalina.util.URLEncoder.DEFAULT.encode(new Gson().toJson(map)));
		sBuffer.append("&_=" + sysTimeStr);
		
		Spider.create(new GithubRepoPageProcessor())
	      .addUrl(sBuffer.toString())
	      //启动爬虫
	      .run();

	}

}