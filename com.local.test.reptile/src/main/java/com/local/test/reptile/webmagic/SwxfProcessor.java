package com.local.test.reptile.webmagic;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.local.test.reptile.pojo.po.SpiderData;
import com.local.test.reptile.util.DateUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
/**
 * @ClassName: GameSkyProcessor 
 * @Description: TODO 守望先锋
 * @author: xf.sui
 * @date: 2017年3月6日 下午4:48:56
 */
public class SwxfProcessor implements PageProcessor {
	
	private static Map<String, String> paramsMap = Maps.newHashMap();
	private static StringBuffer url = new StringBuffer();
	static{
		String sysTimeStr = String.valueOf(System.currentTimeMillis());
		paramsMap.put("type", "updatenodelabel");
		paramsMap.put("isCache", "true");
		paramsMap.put("cacheTime", "60");
		paramsMap.put("nodeId", "20894");
		paramsMap.put("isNodeId", "true");
		paramsMap.put("page", "1");// 获取第几页的数据

		url.append("http://db2.gamersky.com/LabelJsonpAjax.aspx?");
		url.append("callback=jQuery" + RandomStringUtils.randomNumeric(21) + "_" + RandomStringUtils.randomNumeric(13) + "&jsondata=");
		url.append(org.apache.catalina.util.URLEncoder.DEFAULT.encode(new Gson().toJson(paramsMap)));
		url.append("&_=" + sysTimeStr);
	}


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
    	
    	SpiderData data = null;
    	
    	for(Element element:elementsLi){
    		
    		data = new SpiderData();
    		Elements elementsImg = element.getElementsByTag("img");
    		
    		data.setImgSrc(elementsImg.get(0).attr("src"));
    		data.setTitle(elementsImg.get(0).attr("alt"));
    		data.setAbstractContent(element.getElementsByClass("text").text());
    		
    		data.setPublishTime(DateUtil.StringToDate(element.getElementsByClass("time").text(), DateUtil.FORMAT_1));
    		
    		System.out.println(jsonObject.toJSONString(data));
    	}
    	
    }

    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3).setSleepTime(1000);
    }

//	public void start() {
//		Spider.create(new SwxfProcessor()).addUrl(url.toString()).run();
//
//	}

}