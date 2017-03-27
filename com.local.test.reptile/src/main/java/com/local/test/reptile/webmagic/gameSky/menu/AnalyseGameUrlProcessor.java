package com.local.test.reptile.webmagic.gameSky.menu;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.local.test.reptile.pojo.po.SpiderType;
import com.local.test.reptile.pojo.qo.SpiderTypeQo;
import com.local.test.reptile.service.SpiderTypeService;
import com.local.test.reptile.util.enums.LevelTypeEnum;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * 
 * @ClassName: MobileTypeProcessor 
 * @Description: TODO 游牧星空 游戏页面
 * @author: xf.sui
 * @date: 2017年3月6日 下午6:16:10
 */

public class AnalyseGameUrlProcessor implements PageProcessor{

	private SpiderTypeService spiderTypeService;
	private Integer parentId;
	private String parentName;
	private Integer platFormId;
	
	public AnalyseGameUrlProcessor(SpiderTypeService spiderTypeService, Integer parentId, String parentName,Integer platFormId){
		this.spiderTypeService = spiderTypeService;
		this.parentId = parentId;
		this.parentName = parentName;
		this.platFormId = platFormId;
	}
	
    @Override
    public void process(Page page) { 
    	
    	Html html = page.getHtml();
    	
    	List<String> links = html.xpath("//ul[@class='nav']//li/a").all();
    	for(String link:links){
    		String regexNew = "<a class=\"lik\" href=\"([\\S|\\s]+)\">新闻资讯</a>";
    		String regexSkill = "<a class=\"lik\" href=\"([\\S|\\s]+)\">攻略经验</a>";
    		
    		String str1 = new Html(link).xpath("//span[@class='txt']/tidyText()").toString();
    		
    		if(link.matches(regexNew)){
    			String levelUrl = link.replaceAll(regexNew, "$1");
    			saveNew(levelUrl);
    		}
    		else if(null != str1 && str1.contains("资讯")){
    			String levelUrl = new Html(link).xpath("//a/@href").toString();
    			saveNew(levelUrl);
    		}
    		
    		if(link.matches(regexSkill)){
    			String levelUrl = link.replaceAll(regexSkill, "$1");
    			saveSkill(levelUrl);
    		}
    		else if(null != str1 && str1.contains("攻略")){
    			String levelUrl = new Html(link).xpath("//a/@href").toString();
    			saveNew(levelUrl);
    		}
    	}
    	
    	if(CollectionUtils.isEmpty(links)){
    		links = html.xpath("//ul[@class='topnav']//li/a").all();
    		for(String link:links){
    			
    			String str1 = new Html(link).xpath("//span/tidyText()").toString();
    			
    			if(null != str1 && str1.contains("资讯")){
    				String levelUrl = new Html(link).xpath("//a/@href").toString();
        			saveNew(levelUrl);
    			}
    			else if(null != str1 && str1.contains("攻略")){
    				String levelUrl = new Html(link).xpath("//a/@href").toString();
    				saveSkill(levelUrl);
    			}
    		}
    	}
    	
    	if(CollectionUtils.isEmpty(links)){
    		links = html.xpath("//div[@class='topnav']/a").all();
    		for(String link:links){
    			
    			String str1 = new Html(link).xpath("//span/tidyText()").toString();
    			
    			if(null != str1 && str1.contains("资讯")){
    				String levelUrl = new Html(link).xpath("//a/@href").toString();
    				saveNew(levelUrl);
    			}
    			else if(null != str1 && str1.contains("攻略")){
    				String levelUrl = new Html(link).xpath("//a/@href").toString();
    				saveSkill(levelUrl);
    			}
    		}
    	}
    	
    	if(CollectionUtils.isEmpty(links)){
    		links = html.xpath("//div[@class='nav']/a").all();
    		for(String link:links){
    			
    			String str1 = new Html(link).xpath("//a/tidyText()").toString();
    			
    			if(null != str1 && str1.contains("资讯")){
    				String levelUrl = new Html(link).xpath("//a/@href").toString();
    				saveNew(levelUrl);
    			}
    			else if(null != str1 && str1.contains("攻略")){
    				String levelUrl = new Html(link).xpath("//a/@href").toString();
    				saveSkill(levelUrl);
    			}
    		}
    	}
    }

	private void saveSkill(String levelUrl) {
		SpiderTypeQo queryPojo = queryType(levelUrl);
		Integer menuId = spiderTypeService.findMenuId(queryPojo);
		if(null == menuId){
			SpiderType entity = buildPo(parentName + "-攻略", levelUrl, parentId, LevelTypeEnum.GAME_SKAY_SKILL.getId(), platFormId);
			spiderTypeService.save(entity);
		}
	}

	private void saveNew(String levelUrl) {
		SpiderTypeQo queryPojo = queryType(levelUrl);
		Integer menuId = spiderTypeService.findMenuId(queryPojo);
		if(null == menuId){
			SpiderType entity = buildPo(parentName + "-新闻", levelUrl, parentId, LevelTypeEnum.GAME_SKAY_NEWS.getId(), platFormId);
			spiderTypeService.save(entity);
		}
	}

	private SpiderTypeQo queryType(String levelUrl) {
		SpiderTypeQo queryPojo = new SpiderTypeQo();
		queryPojo.setPlatformId(platFormId);
		queryPojo.setParentLevelId(parentId);
		queryPojo.setLevelUrl(levelUrl);
		queryPojo.setPlatformId(platFormId);
		queryPojo.setLevelType(LevelTypeEnum.GAME_SKAY_NEWS.getId());
		return queryPojo;
	}


	private SpiderType buildPo(String vote, String levelUrl, Integer parentLevelId, Integer levelType, Integer platFormId) {
		SpiderType entity;
		entity = new SpiderType();
		entity.setLevelName(vote.trim());
		entity.setLevelType(levelType);
		entity.setLevelUrl(levelUrl);
		entity.setParentLevelId(parentLevelId);
		entity.setPlatformId(platFormId);
		return entity;
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
