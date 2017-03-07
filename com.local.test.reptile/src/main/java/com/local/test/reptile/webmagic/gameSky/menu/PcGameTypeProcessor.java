package com.local.test.reptile.webmagic.gameSky.menu;

import java.util.List;

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
 * @ClassName: PcGameType 
 * @Description: TODO 游牧星空 单机游戏分类
 * @author: xf.sui
 * @date: 2017年3月6日 下午6:16:10
 */

public class PcGameTypeProcessor implements PageProcessor{

	private SpiderTypeService spiderTypeService;
	
	public PcGameTypeProcessor(SpiderTypeService spiderTypeService){
		this.spiderTypeService = spiderTypeService;
	}
	
    @Override
    public void process(Page page) { 
    	
    	Html html = page.getHtml();
    	List<String> secondLevel = html.xpath("//div[@class='Mid0nav']/a").all();
    	List<String> threeLevel = html.xpath("//div[@class='Mid0cont']").all();
    	
//    	for(String type:secondLevel){
//    		String vote = new Html(type).xpath("//a//span/tidyText()").toString();//二级 菜单
////    		System.out.println(vote);
//    	}
//    	
//    	for(String type:threeLevel){
//    		
//    		List<String> all = new Html(type).xpath("//a").all();
//    		for(String aStr:all){
//    			String regex = "<a([\\S|\\s]+)href=\"([\\S|\\s]+)/\">([\\S|\\s]+)</a>";
//    			
//    			System.out.println(aStr.replaceAll(regex, "$3"));
//    		}
//    	}
    	
    	for(int i=0;i<secondLevel.size(); i++){
    		String vote = new Html(secondLevel.get(i)).xpath("//a//span/tidyText()").toString();//二级 菜单
    		
    		SpiderTypeQo queryPojo = new SpiderTypeQo();
    		
    		SpiderType entity = null;
    		queryPojo.setLevelName(vote.trim());
    		queryPojo.setPlatformId(1);
    		if(null == spiderTypeService.findMenuId(queryPojo)){
    			entity = buildPo(vote, "", 1);
    			spiderTypeService.save(entity);
        		
        		Integer parentId = spiderTypeService.findMenuId(queryPojo);
        		
        		saveThreeLevel(threeLevel, i, parentId);
    		}else{
    			Integer parentId = spiderTypeService.findMenuId(queryPojo);
    			saveThreeLevel(threeLevel, i, parentId);
    		}
    	}
    	
    }

	private void saveThreeLevel(List<String> threeLevel, int i, Integer parentId) {
		SpiderType entity;
		SpiderTypeQo queryPojo;
		String threeObj = threeLevel.get(i);
		List<String> all = new Html(threeObj).xpath("//a").all();
		for(String aStr:all){
			String regex = "<a([\\S|\\s]+)href=\"([\\S|\\s]+)\">([\\S|\\s]+)</a>";
			queryPojo = new SpiderTypeQo();
			queryPojo.setLevelName(aStr.replaceAll(regex, "$3"));
			queryPojo.setPlatformId(1);
			if(null == spiderTypeService.findMenuId(queryPojo)){
				entity = buildPo(aStr.replaceAll(regex, "$3"), aStr.replaceAll(regex, "$2"), parentId);
				spiderTypeService.save(entity);
			}
		}
	}

	private SpiderType buildPo(String vote, String levelUrl, Integer parentLevelId) {
		SpiderType entity;
		entity = new SpiderType();
		entity.setLevelName(vote.trim());
		entity.setLevelType(LevelTypeEnum.MENU.getId());
		entity.setLevelUrl(levelUrl);
		entity.setParentLevelId(parentLevelId);
		entity.setPlatformId(1);
		return entity;
	}

    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3).setSleepTime(1000);
    }

	public void start() {
//		Spider.create(new PcGameTypeProcessor()).addUrl("http://www.gamersky.com/pcgame/").run();
	}
	
	
}
