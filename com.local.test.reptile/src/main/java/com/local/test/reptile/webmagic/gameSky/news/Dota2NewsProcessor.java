package com.local.test.reptile.webmagic.gameSky.news;

import java.util.List;
import java.util.UUID;

import com.local.test.reptile.pojo.po.SpiderData;
import com.local.test.reptile.pojo.qo.SpiderDataQo;
import com.local.test.reptile.service.SpiderDataService;
import com.local.test.reptile.util.DateUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * 
 * @ClassName: PcGameType 
 * @Description: TODO 游牧星空 Dota2
 * @author: xf.sui
 * @date: 2017年3月6日 下午6:16:10
 */

public class Dota2NewsProcessor implements PageProcessor{

	private SpiderDataService spiderDataService;
	
	public Dota2NewsProcessor(SpiderDataService spiderDataService){
		this.spiderDataService = spiderDataService;
	}
	
    @Override
    public void process(Page page) { 
    	
    	List<String> allLi = page.getHtml().xpath("//ul[@class='pic_txt']//li").all();
    	SpiderData spiderData = new SpiderData();
    	SpiderDataQo qo = null;
    	for(String li:allLi){
    		
    		qo = new SpiderDataQo();
    		qo.setTitle(new Html(li).xpath("//img/@alt").toString());
    		
    		if(null == spiderDataService.findMenuId(qo)){
    			
    			spiderData.setId(UUID.randomUUID().toString());
    			spiderData.setImgSrc(new Html(li).xpath("//img/@src").toString());
    			spiderData.setTitle(new Html(li).xpath("//img/@alt").toString());
    			spiderData.setAbstractContent(new Html(li).xpath("//div[@class='text']/tidyText()").toString().trim());
    			try {
				} catch (NullPointerException e) {
					spiderData.setAbstractContent(new Html(li).xpath("//div[@class='txt']/tidyText()").toString().trim());
				}
    			spiderData.setPublishTime(DateUtil.StringToDate(new Html(li).xpath("//div[@class='time']/tidyText()").toString().trim(), DateUtil.FORMAT_1));
    			spiderData.setContentUrl(new Html(li).xpath("//div[@class='tit']//a/@href").toString());
    			spiderData.setAddTime(DateUtil.getCurrentTime());
    			spiderDataService.save(spiderData);
//    			System.out.println(JSONObject.toJSON(spiderData));
    		}
    		
    	}
    	page.addTargetRequests(page.getHtml().links().regex(page.getUrl().toString()+"[\\w|/]*.shtml").all(), 1L);
    }


    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3).setSleepTime(1000);
    }
    
//    @Test
//	public void start() {
//		Spider.create(new Dota2NewsProcessor()).addUrl("http://ol.gamersky.com/z/dota2/n/").run();
//	}
	
}
