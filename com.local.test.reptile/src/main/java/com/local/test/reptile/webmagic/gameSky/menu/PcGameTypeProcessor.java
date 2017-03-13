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
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * 
 * @ClassName: PcGameType 
 * @Description: TODO 游牧星空 单机游戏分类
 * @author: xf.sui
 * @date: 2017年3月6日 下午6:16:10
 */

public class PcGameTypeProcessor extends ParamsProcessor implements PageProcessor{

	
    public PcGameTypeProcessor(SpiderDataService spiderDataService, SpiderTypeService spiderTypeService, Integer taskId, Integer typeId) {
		super(spiderDataService, spiderTypeService, taskId, typeId);
	}

	@Override
    public void process(Page page) { 
    	
    	Html html = page.getHtml();
    	List<String> secondLevel = html.xpath("//div[@class='Mid0nav']/a").all();
    	List<String> threeLevel = html.xpath("//div[@class='Mid0cont']").all();
    	
    	SpiderType entity = null;
    	SpiderTypeQo queryPojo = new SpiderTypeQo();
    	queryPojo.setPlatformId(PlatfromEnum.GAME_SKY.getId());
    	queryPojo.setLevelType(LevelTypeEnum.GAME_SKAY_PC_GAME.getId());
    	Integer parentId = getSpiderTypeService().findMenuId(queryPojo);
    	
    	Integer threeParentId = null;
    	for(int i=0;i<secondLevel.size(); i++){
    		String vote = new Html(secondLevel.get(i)).xpath("//a//span/tidyText()").toString();//二级 菜单
    		
    		queryPojo = new SpiderTypeQo();
    		queryPojo.setLevelName(vote.trim());
    		queryPojo.setParentLevelId(parentId);
    		if(null == getSpiderTypeService().findMenuId(queryPojo)){
    			entity = buildPo(vote, "", parentId);
    			getSpiderTypeService().save(entity);
        		
    			threeParentId = getSpiderTypeService().findMenuId(queryPojo);
        		
        		saveThreeLevel(threeLevel, i, threeParentId);
    		}else{
    			threeParentId = getSpiderTypeService().findMenuId(queryPojo);
    			saveThreeLevel(threeLevel, i, threeParentId);
    		}
    	}
    	
    }

	private void saveThreeLevel(List<String> threeLevel, int i, Integer parentId) {
		SpiderType entity;
		SpiderTypeQo queryPojo;
		String threeObj = threeLevel.get(i);
		List<String> all = new Html(threeObj).xpath("//a").all();
		for(String aStr:all){
			String regex = "<a([\\S|\\s]+)>([\\S|\\s]+)([<i>|\\S|\\s|</i>]*)</a>";
			
			String url = new Html(aStr).xpath("//a/@href").toString();
    		String name = aStr.replaceAll(regex, "$2");
    		
			queryPojo = new SpiderTypeQo();
			queryPojo.setLevelName(name);
			queryPojo.setParentLevelId(parentId);
			if(null == getSpiderTypeService().findMenuId(queryPojo)){
				entity = buildPo(name, url, parentId);
				getSpiderTypeService().save(entity);
				
//				Integer gameId = spiderTypeService.findMenuId(queryPojo);
//				Spider.create(new AnalyseGameUrlProcessor(spiderTypeService, gameId, name, PlatfromEnum.GAME_SKY.getId())).addUrl(url).run();

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

	public void start() {
//		Spider.create(new PcGameTypeProcessor()).addUrl("http://www.gamersky.com/pcgame/").run();
	}
	
	
}
