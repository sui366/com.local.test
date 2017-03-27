package com.local.test.reptile.webmagic.gameSky.content;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Strings;
import com.local.test.reptile.pojo.po.SpiderData;
import com.local.test.reptile.service.SpiderDataService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 
 * @ClassName: CommonContentProcessor 
 * @Description: TODO
 * @author: xf.sui
 * @date: 2017年3月13日 下午2:22:03
 */
public class GameSkyCommonContentProcessor implements PageProcessor{
	
	private SpiderDataService spiderDataService;
	private String dataId;
	
	public GameSkyCommonContentProcessor(SpiderDataService spiderDataService, String dataId){
		this.spiderDataService = spiderDataService;
		this.dataId = dataId;
	}
	
	@Override
    public void process(Page page) { 
		
		SpiderData spiderData = spiderDataService.get(dataId);
		if(null != spiderData){
			StringBuffer sBuffer = new StringBuffer();
			if(!Strings.isNullOrEmpty(spiderData.getContent())){
				sBuffer.append(spiderData.getContent());
			}
			List<String> texts = page.getHtml().xpath("//div[@class='Mid2L_con']//p").all();
	    	for(String text:texts){
	    		if(text.indexOf("更多相关资讯请关注")>-1 || text.indexOf("更多相关内容请关注")>-1 ){
	    			continue;
	    		}
	    		sBuffer.append(text);
	    	}
	    	spiderData.setContent(sBuffer.toString());
	    	spiderDataService.update(spiderData);
		}
		
		List<String> links = page.getHtml().xpath("//div[@class='page_css']//a").all();
		List<String> lists = new ArrayList<String>();
    	
    	String regex = "http://ol.gamersky.com/[news|gl]+/[\\d|/|_]+.shtml";
    	for(String link:links){
    		if(link.matches(regex) && !link.equals(page.getUrl().toString())){
    			lists.add(link);
    		}
    	}
		page.addTargetRequests(lists);
		
    }

    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3).setSleepTime(1000);
    }
    
//    @Test
//    public void start(){
//    	Spider.create(new CommonContentProcessor()).addUrl("http://ol.gamersky.com/news/201703/874679.shtml").run();
//    }

}
