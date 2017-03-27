package com.local.test.reptile.webmagic.enjoy;

import java.util.List;
import java.util.UUID;

import com.local.test.reptile.pojo.po.SpiderData;
import com.local.test.reptile.pojo.qo.SpiderDataQo;
import com.local.test.reptile.service.SpiderDataService;
import com.local.test.reptile.service.SpiderTypeService;
import com.local.test.reptile.util.DateUtil;
import com.local.test.reptile.webmagic.ParamsProcessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * 
 * @ClassName: RankProcessor 
 * @Description: TODO 有意思吧 排行榜
 * @author: xf.sui
 * @date: 2017年3月6日 下午6:16:10
 * 
 */

public class MonthRankProcessor extends ParamsProcessor implements PageProcessor{

	
    public MonthRankProcessor(SpiderDataService spiderDataService, SpiderTypeService spiderTypeService, Integer taskId, Integer typeId) {
		super(spiderDataService, spiderTypeService, taskId, typeId);
	}


	@Override
    public void process(Page page) { 
    	
    	Html html = page.getHtml();
    	
    	List<String> sidebars = html.xpath("//div[@class='sidebar']").all();
    	for(String sidebar:sidebars){
    		
    		String h2 =  new Html(sidebar).xpath("//h2/tidyText()").toString();
    		
    		if(null != h2 && h2.contains("月排行榜")){
    			
    			SpiderData spiderData = new SpiderData();
    	    	SpiderDataQo qo = null;
    	    	
        		
    	    	List<String> links =  new Html(sidebar).xpath("//li//a").all();
        		for(String link: links){
        			String regex = "<a([\\S|\\s]+)>([\\S|\\s]+)</a>";
        			String title = link.replaceAll(regex, "$2").trim();
        			String url = new Html(link).xpath("//a/@href").toString();
        			
        			qo = new SpiderDataQo();
            		qo.setTitle(title);
            		qo.setTaskId(getTaskId());
        			
            		if(null == getSpiderDataService().findMenuId(qo)){
            			
            			String uuid = UUID.randomUUID().toString();
            			spiderData.setId(uuid);
            			spiderData.setTaskId(getTaskId());
            			spiderData.setTypeId(getTypeId());
            			spiderData.setTitle(title);
            			spiderData.setContentUrl(url);
            			spiderData.setAddTime(DateUtil.getCurrentTime());
            			getSpiderDataService().save(spiderData);
            			
            			Spider.create(new EnjoyCommonContentProcessor(getSpiderDataService(), uuid)).addUrl(url).run();
            		}
        		}
    		}
    		
    	}
    	
    }


    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3).setSleepTime(1000);
    }

//    @Test
//	public void start() {
//		Spider.create(new MobileTypeProcessor()).addUrl("http://shouyou.gamersky.com/").run();
//	}
	
}
