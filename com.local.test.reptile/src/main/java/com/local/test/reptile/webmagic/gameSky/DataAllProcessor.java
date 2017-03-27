package com.local.test.reptile.webmagic.gameSky;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONObject;
import com.local.test.reptile.service.SpiderDataService;
import com.local.test.reptile.service.SpiderTypeService;
import com.local.test.reptile.webmagic.ParamsProcessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class DataAllProcessor extends ParamsProcessor implements PageProcessor {
	public DataAllProcessor(SpiderDataService spiderDataService, SpiderTypeService spiderTypeService, Integer taskId, Integer typeId) {
		super(spiderDataService, spiderTypeService, taskId, typeId);
	}

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

		for (Element element : elementsLi) {
			Elements elementsLiTt = element.getElementsByClass("tt");
			Elements elementTitle = elementsLiTt.get(0).getElementsByAttribute("title");
			System.out.println(elementTitle.get(0).childNode(0).toString());
		}

		// System.out.println(object);

	}

	@Override
	public Site getSite() {
		return site;
	}

	
}
