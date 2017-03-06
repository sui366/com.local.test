package com.test.webmagic;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
    	
//    	Json json = page.getJson();

    	String rawText = page.getRawText();
    	String regex = "(jQuery\\d+_\\d+\\()([\\S|\\s]+)(\\);)";
    	String replaceAll = rawText.replaceAll(regex, "$2");
    	
    	JSONObject jsonObject = JSONObject.parseObject(replaceAll);
    	Object object = jsonObject.get("body");
    	
    	Document doc = Jsoup.parseBodyFragment(object.toString());
    	Element body = doc.body();
    	
//    	PlainText create = Json.create(replaceAll);
    	
    	System.out.println(object);
//    	rawText.replace(target, replacement)
    	
//    	System.out.println("json="+json.toString());
    	
        // 部分二：定义如何抽取页面信息，并保存下来
//        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
//        if (page.getResultItems().get("name") == null) {
//            //skip this page
//            page.setSkip(true);
//        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

        // 部分三：从页面发现后续的url地址来抓取
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
    }

    @Override
    public Site getSite() {
        return site;
    }
    
    /**
	 * 获取分页
	 */
	@Test
	public void getlabelpage() {
		String sysTimeStr = String.valueOf(System.currentTimeMillis());
		Map<String, String> map = Maps.newHashMap();
		map.put("type", "getlabelpage");
		map.put("currentPage", "2");
		map.put("pagesize", "50");
		map.put("recordCount", "110854");
		map.put("pagesDisplay", "6");

		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("http://db2.gamersky.com/LabelJsonpAjax.aspx?");
		sBuffer.append("callback=jQuery" + RandomStringUtils.randomNumeric(21) + "_" + RandomStringUtils.randomNumeric(13) + "&jsondata=");
		sBuffer.append(org.apache.catalina.util.URLEncoder.DEFAULT.encode(new Gson().toJson(map)));
		sBuffer.append("&_=" + sysTimeStr);
		
		Spider.create(new GithubRepoPageProcessor())
	      .addUrl(sBuffer.toString())
	      //开启5个线程抓取
	      .thread(1)
	      //启动爬虫
	      .run();

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
		map.put("page", "4");// 获取第几页的数据

		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("http://db2.gamersky.com/LabelJsonpAjax.aspx?");
		sBuffer.append("callback=jQuery" + RandomStringUtils.randomNumeric(21) + "_" + RandomStringUtils.randomNumeric(13) + "&jsondata=");
		sBuffer.append(org.apache.catalina.util.URLEncoder.DEFAULT.encode(new Gson().toJson(map)));
		sBuffer.append("&_=" + sysTimeStr);
		
		Spider.create(new GithubRepoPageProcessor())
	      .addUrl(sBuffer.toString())
	      //开启5个线程抓取
	      .thread(1)
	      //启动爬虫
	      .run();

	}

	/**
	 * 评论数
	 */
	@Test
	public void count() {
		String sysTimeStr = String.valueOf(System.currentTimeMillis());

		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("http://cm.gamersky.com/commentapi/count?");
		sBuffer.append("callback=jQuery" + RandomStringUtils.randomNumeric(21) + "_" + RandomStringUtils.randomNumeric(13) + "&jsondata=");
		sBuffer.append("&_=" + sysTimeStr);

		Spider.create(new GithubRepoPageProcessor())
	      .addUrl(sBuffer.toString())
	      //开启5个线程抓取
	      .thread(1)
	      //启动爬虫
	      .run();

	}


}