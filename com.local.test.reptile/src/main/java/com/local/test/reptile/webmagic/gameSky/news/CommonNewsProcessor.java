package com.local.test.reptile.webmagic.gameSky.news;

import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;

import com.local.test.reptile.pojo.po.SpiderData;
import com.local.test.reptile.pojo.qo.SpiderDataQo;
import com.local.test.reptile.service.SpiderDataService;
import com.local.test.reptile.service.SpiderTypeService;
import com.local.test.reptile.util.DateUtil;
import com.local.test.reptile.webmagic.ParamsProcessor;
import com.local.test.reptile.webmagic.gameSky.content.GameSkyCommonContentProcessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * 
 * @ClassName: PcGameType 
 * @Description: TODO 游牧星空 新闻 公共
 * @author: xf.sui
 * @date: 2017年3月6日 下午6:16:10
 */

public class CommonNewsProcessor extends ParamsProcessor implements PageProcessor{

	
    public CommonNewsProcessor(SpiderDataService spiderDataService, SpiderTypeService spiderTypeService, Integer taskId, Integer typeId) {
		super(spiderDataService, spiderTypeService, taskId, typeId);
	}


	@Override
    public void process(Page page) { 
		
    	List<String> allLi = page.getHtml().xpath("//ul[@class='pic_txt contentpaging']//li").all();
    	
    	if(CollectionUtils.isEmpty(allLi)){
    		allLi = page.getHtml().xpath("//ul[@class='pictxt contentpaging']//li").all();
    	}
    	if(CollectionUtils.isEmpty(allLi)){
    		allLi = page.getHtml().xpath("//ul[@class='pic_txt']//li").all();
    	}
    	if(CollectionUtils.isEmpty(allLi)){
    		allLi = page.getHtml().xpath("//ul[@class='pictxt downlist']//li").all();
    	}
    	
    	SpiderData spiderData = new SpiderData();
    	SpiderDataQo qo = null;
    	for(String li:allLi){
    		
    		
    		qo = new SpiderDataQo();
    		qo.setTitle(new Html(li).xpath("//img/@alt").toString());
    		
    		if(null == getSpiderDataService().findMenuId(qo)){
    			
    			String uuid = UUID.randomUUID().toString();
    			spiderData.setId(uuid);
    			spiderData.setTaskId(getTaskId());
    			spiderData.setTypeId(getTypeId());
    			spiderData.setImgSrc(new Html(li).xpath("//img/@src").toString());
    			spiderData.setTitle(new Html(li).xpath("//img/@alt").toString());
    			try {
    				spiderData.setAbstractContent(new Html(li).xpath("//div[@class='text']/tidyText()").toString().trim());
				} catch (NullPointerException e) {
					spiderData.setAbstractContent(new Html(li).xpath("//div[@class='txt']/tidyText()").toString().trim());
				}
    			spiderData.setPublishTime(DateUtil.StringToDate(new Html(li).xpath("//div[@class='time']/tidyText()").toString().trim(), DateUtil.FORMAT_1));
    			String contentUrl = new Html(li).xpath("//div[@class='tit']//a/@href").toString();
    			spiderData.setContentUrl(contentUrl);
    			try {
					spiderData.setVisitCount(Long.valueOf(new Html(li).xpath("//div[@class='visit']/tidyText()").toString().trim()));
				} catch (NullPointerException e1) {
				}
    			try {
					spiderData.setCyCommentCount(Long.valueOf(new Html(li).xpath("//div[@class='pls cy_comment']/tidyText()").toString().trim()));
				} catch (NumberFormatException e) {
					
				}catch (NullPointerException e1) {
				}
    			spiderData.setAddTime(DateUtil.getCurrentTime());
    			getSpiderDataService().save(spiderData);
    			
				Spider.create(new GameSkyCommonContentProcessor(getSpiderDataService(), uuid)).addUrl(contentUrl).run();

    		}
    		
    	}
    	
    }


    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3).setSleepTime(1000);
    }

	
}
