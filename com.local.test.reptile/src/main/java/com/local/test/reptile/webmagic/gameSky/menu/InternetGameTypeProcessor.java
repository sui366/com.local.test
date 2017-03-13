package com.local.test.reptile.webmagic.gameSky.menu;

import java.util.List;

import com.local.test.reptile.pojo.po.SpiderType;
import com.local.test.reptile.pojo.qo.SpiderTypeQo;
import com.local.test.reptile.service.SpiderDataService;
import com.local.test.reptile.service.SpiderTypeService;
import com.local.test.reptile.util.enums.LevelTypeEnum;
import com.local.test.reptile.util.enums.PlatfromEnum;
import com.local.test.reptile.webmagic.ParamsProcessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * 
 * @ClassName: PcGameType 
 * @Description: TODO 游牧星空 网络游戏分类
 * @author: xf.sui
 * @date: 2017年3月6日 下午6:16:10
 */

public class InternetGameTypeProcessor extends ParamsProcessor implements PageProcessor{

    public InternetGameTypeProcessor(SpiderDataService spiderDataService, SpiderTypeService spiderTypeService, Integer taskId, Integer typeId) {
		super(spiderDataService, spiderTypeService, taskId, typeId);
	}

	@Override
    public void process(Page page) { 
    	
    	Html html = page.getHtml();
    	List<String> threeLevel = html.xpath("//ul[@class='game-hot']//a").all();
    	
    	SpiderType entity;
		SpiderTypeQo queryPojo;
		
		queryPojo = new SpiderTypeQo();
		queryPojo.setLevelType(LevelTypeEnum.GAME_SKAY_INTERNET_GAME.getId());
		queryPojo.setPlatformId(PlatfromEnum.GAME_SKY.getId());
		Integer parentId = getSpiderTypeService().findMenuId(queryPojo);
    	
		for(String aStr:threeLevel){
			String regex = "<a([\\S|\\s]+)>([\\S|\\s]+)</a>";
			String url = new Html(aStr).xpath("//a/@href").toString();
			String name = aStr.replaceAll(regex, "$2");
			queryPojo = new SpiderTypeQo();
			queryPojo.setLevelName(name);
			queryPojo.setParentLevelId(parentId);
			if(null == getSpiderTypeService().findMenuId(queryPojo)){
				entity = buildPo(name, url, parentId);
				getSpiderTypeService().save(entity);
				
				Integer gameId = getSpiderTypeService().findMenuId(queryPojo);
				
				Spider.create(new AnalyseGameUrlProcessor(getSpiderTypeService(), gameId, name, PlatfromEnum.GAME_SKY.getId())).addUrl(url).run();
			}
		}
    	
    }

	private SpiderType buildPo(String vote, String levelUrl, Integer parentLevelId) {
		SpiderType entity;
		entity = new SpiderType();
		entity.setLevelName(vote.trim());
		entity.setLevelType(LevelTypeEnum.GAME_SKAY_MENU.getId());
		entity.setLevelUrl(levelUrl);
		entity.setParentLevelId(parentLevelId);
		entity.setPlatformId(PlatfromEnum.GAME_SKY.getId());
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
