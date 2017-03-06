package com.local.test.reptile.webmagic;

import java.util.List;

import org.junit.Test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * 
 * @ClassName: PcGameType 
 * @Description: TODO 游牧星空 单机游戏分类
 * @author: xf.sui
 * @date: 2017年3月6日 下午6:16:10
 */
public class PcGameTypeProcessor implements PageProcessor{

    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    @Override
    public void process(Page page) { 
    	
    	Html html = page.getHtml();
    	List<String> secondLevel = html.xpath("//div[@class='Mid0nav']/a").all();
    	for(String type:secondLevel){
    		String vote = new Html(type).xpath("//a//span/tidyText()").toString();//二级 菜单
    		
//    		System.out.println(vote);
    	}
    	
    	List<String> threeLevel = html.xpath("//div[@class='like2']").all();
    	for(String type:threeLevel){
    		System.out.println("******************** begin *****************************");
    		System.out.println(type);
    		System.out.println("******************** end   *****************************");
    	}
    	
    }

    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3).setSleepTime(1000);
    }

	@Test
	public void start() {
		Spider.create(new PcGameTypeProcessor()).addUrl("http://www.gamersky.com/pcgame/").run();

	}
}
