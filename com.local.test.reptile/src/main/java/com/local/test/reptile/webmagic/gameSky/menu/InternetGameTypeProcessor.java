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
 * @Description: TODO 游牧星空 网络游戏分类
 * @author: xf.sui
 * @date: 2017年3月6日 下午6:16:10
 */

public class InternetGameTypeProcessor implements PageProcessor{

	private SpiderTypeService spiderTypeService;
	
	public InternetGameTypeProcessor(SpiderTypeService spiderTypeService){
		this.spiderTypeService = spiderTypeService;
	}
	
    @Override
    public void process(Page page) { 
    	
    	Html html = page.getHtml();
    	List<String> threeLevel = html.xpath("//ul[@class='game-hot']//a").all();
    	
//    	for(String aStr:threeLevel){
////			String regex = "<a([\\S|\\s]+)href=\"([\\S|\\s]+)\" data-itemid=\"500438\">([\\S|\\s]+)</a>";
//			String regex = "<a([\\S|\\s]+)href=\"([\\S|\\s]+)\">([\\S|\\s]+)</a>";
//			System.out.println(aStr);
//			System.out.println(aStr.replaceAll(regex, "$2") +" == " + aStr.replaceAll(regex, "$3"));
//    	}
    	
    	SpiderType entity;
		SpiderTypeQo queryPojo;
		
		queryPojo = new SpiderTypeQo();
		queryPojo.setLevelName("网络游戏");
		queryPojo.setPlatformId(1);
    	
    	if(null == spiderTypeService.findMenuId(queryPojo)){
			entity = buildPo("网络游戏", "", 1);
			spiderTypeService.save(entity);
			
			buildThreeLevel(threeLevel, queryPojo);
		}else{
			buildThreeLevel(threeLevel, queryPojo);
			
		}
    	
    }

	private void buildThreeLevel(List<String> threeLevel, SpiderTypeQo queryPojo) {
		SpiderType entity;
		Integer parentId = spiderTypeService.findMenuId(queryPojo);
		for(String aStr:threeLevel){
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

//    @Test
//	public void start() {
//		Spider.create(new InternetGameTypeProcessor()).addUrl("http://ol.gamersky.com/").run();
//	}
	
	
}
