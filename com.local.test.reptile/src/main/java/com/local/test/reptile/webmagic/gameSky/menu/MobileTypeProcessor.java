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
 * @ClassName: MobileTypeProcessor
 * @Description: TODO 游牧星空 手机游戏分类
 * @author: xf.sui
 * @date: 2017年3月6日 下午6:16:10
 */

public class MobileTypeProcessor extends ParamsProcessor implements PageProcessor {


	public MobileTypeProcessor(SpiderDataService spiderDataService, SpiderTypeService spiderTypeService, Integer taskId, Integer typeId) {
		super(spiderDataService, spiderTypeService, taskId, typeId);
	}

	@Override
	public void process(Page page) {

		Html html = page.getHtml();
		List<String> secondLevel = html.xpath("//div[@class='Mid']//div[@class='Mid_top']//div[@class='tag']//div[@class='txt']/a").all();
		List<String> picLevel = html.xpath("//div[@class='Mid']//div[@class='Mid_top']//div[@class='pic']/a").all();

		SpiderType entity = null;
		SpiderTypeQo queryPojo = new SpiderTypeQo();
		queryPojo.setPlatformId(PlatfromEnum.GAME_SKY.getId());
		queryPojo.setLevelType(LevelTypeEnum.GAME_SKAY_MOBILE_GAME.getId());
		Integer parentId = getSpiderTypeService().findMenuId(queryPojo);

		for (String level : secondLevel) {
			String regex = "<a([\\S|\\s]+)>([\\S|\\s]+)</a>";
			String url = new Html(level).xpath("//a/@href").toString();
			String name = level.replaceAll(regex, "$2");
			queryPojo = new SpiderTypeQo();
			queryPojo.setLevelName(name);
			queryPojo.setParentLevelId(parentId);
			if (null == getSpiderTypeService().findMenuId(queryPojo)) {
				entity = buildPo(name, url, parentId);
				getSpiderTypeService().save(entity);

				Integer gameId = getSpiderTypeService().findMenuId(queryPojo);
				Spider.create(new AnalyseGameUrlProcessor(getSpiderTypeService(), gameId, name, PlatfromEnum.GAME_SKY.getId())).addUrl(url).run();

			}
		}

		for (String level : picLevel) {
			String vote = new Html(level).xpath("//a/tidyText()").toString();
			String regex = "([\\S|\\s]*)<([\\S|\\s]*)>";
			String url = new Html(level).xpath("//a/@href").toString();

			queryPojo = new SpiderTypeQo();
			queryPojo.setLevelName(vote.replaceAll(regex, "$1"));
			queryPojo.setParentLevelId(parentId);
			if (null == getSpiderTypeService().findMenuId(queryPojo)) {
				entity = buildPo(vote.replaceAll(regex, "$1"), url, parentId);
				getSpiderTypeService().save(entity);

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

	// @Test
	// public void start() {
	// Spider.create(new
	// MobileTypeProcessor()).addUrl("http://shouyou.gamersky.com/").run();
	// }

}
