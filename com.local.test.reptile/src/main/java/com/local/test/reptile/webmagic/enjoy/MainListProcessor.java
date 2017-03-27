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
 * @ClassName: MainListProcessor 
 * @Description: TODO 有意思吧 一首页列表
 * @author: xf.sui
 * @date: 2017年3月6日 下午6:16:10
 * 
 */

public class MainListProcessor extends ParamsProcessor implements PageProcessor{

	
    public MainListProcessor(SpiderDataService spiderDataService, SpiderTypeService spiderTypeService, Integer taskId, Integer typeId) {
		super(spiderDataService, spiderTypeService, taskId, typeId);
	}


	@Override
    public void process(Page page) { 
		
		SpiderDataQo qo = null;
		SpiderData spiderData = null;
    	Html html = page.getHtml();
    	
    	List<String> sidebars = html.xpath("//div[@class='mainlist']").all();
    	for(String sidebar:sidebars){
    		
    		String title = new Html(sidebar).xpath("//div[@class='list-content']//h1//a").toString();
    		title = title.replaceAll("<a ([\\S|\\s]+)>([\\S|\\s]+)</a>", "$2");
    		
    		qo = new SpiderDataQo();
    		qo.setTitle(title);
    		qo.setTaskId(getTaskId());
    		
    		if(null == getSpiderDataService().findMenuId(qo)){
    			String imgSrc = new Html(sidebar).xpath("//div[@class='big-img']//img/@src").toString();
    			String contentUrl = new Html(sidebar).xpath("//div[@class='big-img']//a/@href").toString();
    			String abstractContent = new Html(sidebar).xpath("//div[@class='list-content']//div[@class='summary']").toString();
    			abstractContent = abstractContent.replaceAll("<div class=\"summary\">([\\S|\\s]+)</div>", "$1");
    			String author = new Html(sidebar).xpath("//div[@class='list-content']//div[@class='index-time']//a[@class='link4']").toString();
    			author = author.replaceAll("<a ([\\S|\\s]+)>([\\S|\\s]+)</a>", "$2");
    			
    			String publishTime = new Html(sidebar).xpath("//div[@class='list-content']//div[@class='data']//div[@class='data-text']").toString();
    			publishTime = publishTime.replaceAll("<div class=\"data-text\">([\\s]*)推荐于：([\\d|-]+)([\\s]*)</div>", "$2");
    			
//    			String star = new Html(sidebar).xpath("//div[@class='list-content']//div[@class='data']//div[@class='data-star']").toString();
//    			star = star.replaceAll("<div class=\"data-star level4\"></div>", replacement)
    			
    			String count = new Html(sidebar).xpath("//div[@class='list-content']//div[@class='data']//div[@class='data-right']").toString();
    			String visitCount = count.replaceAll("<div class=\"data-right\">([\\s]*)浏览：(\\d+) / 评论：(\\d+)([\\s]*)</div>", "$2");
    			String cyCommentCount = count.replaceAll("<div class=\"data-right\">([\\s]*)浏览：(\\d+) / 评论：(\\d+)([\\s]*)</div>", "$3");
    			
    			
    			spiderData = new SpiderData();
    			String uuid = UUID.randomUUID().toString();
    			spiderData.setId(uuid);
    			spiderData.setTaskId(getTaskId());
    			spiderData.setTypeId(getTypeId());
    			spiderData.setTitle(title);
    			spiderData.setContentUrl(contentUrl);
    			spiderData.setImgSrc(imgSrc);
    			spiderData.setAbstractContent(abstractContent);
    			spiderData.setAddTime(DateUtil.getCurrentTime());
    			spiderData.setAuthor(author);
    			spiderData.setPublishTime(DateUtil.StringToDate(publishTime+"  00:00:00", DateUtil.FORMAT_1));
    			try {
					spiderData.setCyCommentCount(Long.valueOf(cyCommentCount));
				} catch (NumberFormatException e) {
					
				}
    			try {
					spiderData.setVisitCount(Long.valueOf(visitCount));
				} catch (NumberFormatException e) {
					
				}
    			getSpiderDataService().save(spiderData);
    			
    			Spider.create(new EnjoyCommonContentProcessor(getSpiderDataService(), uuid)).addUrl(contentUrl).run();
    		}
    		
    	}
    	
    }


    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3).setSleepTime(1000);
    }

//    @Test
//	public void start() {
////		Spider.create(new MobileTypeProcessor()).addUrl("http://shouyou.gamersky.com/").run();
//    	
//    	
//	}
	
}
