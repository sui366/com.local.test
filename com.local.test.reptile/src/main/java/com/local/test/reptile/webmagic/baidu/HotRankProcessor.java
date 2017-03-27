package com.local.test.reptile.webmagic.baidu;

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
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * 
 * @ClassName: HotRankProcessor
 * @Description: TODO 百度热议榜
 * @author: xf.sui
 * @date: 2017年3月6日 下午6:16:10
 */

public class HotRankProcessor extends ParamsProcessor implements PageProcessor {



	public HotRankProcessor(SpiderDataService spiderDataService, SpiderTypeService spiderTypeService, Integer taskId, Integer typeId) {
		super(spiderDataService, spiderTypeService, taskId, typeId);
	}

	@Override
	public void process(Page page) {
		
		SpiderData spiderData = null;
    	SpiderDataQo qo = null;
    	
		List<String> allLi = page.getHtml().xpath("//ul[@class='topic-top-list']//li").all();
		for (String li : allLi) {
			spiderData = new SpiderData();
			String title = new Html(li).xpath("//a/text()").toString();
			String url = new Html(li).xpath("//a/@href").toString();
			String imgUrl = new Html(li).xpath("//img/@src").toString();
			String content = new Html(li).xpath("//p[@class='topic-top-item-desc']/text()").toString();
			
//			if(imgUrl.indexOf("&")>-1){
//				imgUrl = imgUrl.substring(0, imgUrl.indexOf("&"));
//			}
			
			qo = new SpiderDataQo();
    		qo.setTitle(title);
    		qo.setTaskId(getTaskId());
    		if(null == getSpiderDataService().findMenuId(qo)){
    			spiderData.setTitle(title);
    			spiderData.setAbstractContent(content);
    			spiderData.setAddTime(DateUtil.getCurrentTime());
    			spiderData.setContentUrl(url);
    			spiderData.setId(UUID.randomUUID().toString());
    			spiderData.setImgSrc(imgUrl);
    			spiderData.setTaskId(getTaskId());
    			spiderData.setTypeId(getTypeId());
    			getSpiderDataService().save(spiderData);
    		}
			
//			System.out.println(JSONObject.toJSON(spiderData));
		}

	}

	@Override
	public Site getSite() {
		return Site.me().setRetryTimes(3).setSleepTime(1000);
	}
	
//	@Test
//	public void start(){
//		 Spider.create(new HotRankProcessor()).addUrl("http://www.tieba.com/hottopic/browse/topicList?res_type=1").run();
//	}
	
}
