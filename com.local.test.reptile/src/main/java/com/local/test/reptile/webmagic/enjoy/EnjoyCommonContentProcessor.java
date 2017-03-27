package com.local.test.reptile.webmagic.enjoy;

import com.google.common.base.Strings;
import com.local.test.reptile.pojo.po.SpiderData;
import com.local.test.reptile.service.SpiderDataService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 
 * @Description: TODO
 * @author: xf.sui
 * @date: 2017年3月13日 下午2:22:03
 */
public class EnjoyCommonContentProcessor implements PageProcessor{
	
	private SpiderDataService spiderDataService;
	private String dataId;
	
	public EnjoyCommonContentProcessor(SpiderDataService spiderDataService, String dataId){
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
			String content = page.getHtml().xpath("//div[@class='contents']").toString();
			
			
			
			content = content.replace("<p style=\"text-align:center;\"><img src=\"http://static.u148.net/images/icon-article.gif\"></p>", "");
			
			content = content.replace("<script type=\"text/javascript\">[\\S|\\s]+</script>", "");
			content = content.replace("<div class=\"sponsor-google\">", "<div>");
			content = content.replace("<a class=\"link01\" href=\"[\\S|\\s]+\" target=\"_blank\">[原始链接]</a>", "");
			
			String regex = "([\\S|\\s]+)(<p style=\"text-align:center;\">[\\S|\\s]+</p>)([\\S|\\s]*)";
			
	    	spiderData.setContent(content.replaceAll(regex, "$1$3"));
	    	spiderDataService.update(spiderData);
		}
		
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
