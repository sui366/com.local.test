package com.local.test.reptile.webmagic.baidu;

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
 * @ClassName: TypeProcessor
 * @Description: TODO 百度贴吧分类
 * @author: xf.sui
 * @date: 2017年3月6日 下午6:16:10
 */

public class TypeProcessor extends ParamsProcessor implements PageProcessor {

	public TypeProcessor(SpiderDataService spiderDataService, SpiderTypeService spiderTypeService, Integer taskId, Integer typeId) {
		super(spiderDataService, spiderTypeService, taskId, typeId);
	}

	@Override
	public void process(Page page) {

		Html html = page.getHtml();
		List<String> secondLevel = html.xpath("//div[@class='class-item']").all();

		Integer threeParentId = null;
		SpiderType entity = null;
		SpiderTypeQo queryPojo = new SpiderTypeQo();
		queryPojo.setPlatformId(PlatfromEnum.BAIDU_BA.getId());
		queryPojo.setLevelType(LevelTypeEnum.BAIDU_TIEBA_TYPE.getId());
		Integer parentId = getSpiderTypeService().findMenuId(queryPojo);
		
		

		for (String str : secondLevel) {
			String vote = new Html(str).xpath("//a[@class='class-item-title']").toString();
			String regex = "<a class=\"class-item-title\" [\\S|\\s]+>([\\S|\\s]+)</a>";
			String name = vote.replaceAll(regex, "$1");

			queryPojo = new SpiderTypeQo();
			queryPojo.setLevelName(name.trim());
			queryPojo.setParentLevelId(parentId);
			
			threeParentId = getSpiderTypeService().findMenuId(queryPojo);

			if (null == threeParentId) {
				entity = buildPo(name, "", parentId);
				getSpiderTypeService().save(entity);

				threeParentId = getSpiderTypeService().findMenuId(queryPojo);

				saveTypes(threeParentId, str, vote);
			} else {
				saveTypes(threeParentId, str, vote);
			}

		}

	}

	private void saveTypes(Integer threeParentId, String str, String vote) {
		SpiderType entity;
		SpiderTypeQo queryPojo;
		String name;
		List<String> lis = new Html(str).xpath("//li//a").all();
		for (String li : lis) {
			String url = li.replaceAll("<a href=\"([\\S|\\s]+)\">([\\S|\\s]+)</a>", "$1").replaceAll("amp;", "");
			name = li.replaceAll("<a [\\S|\\s]+>([\\S|\\s]+)</a>", "$1");

			queryPojo = new SpiderTypeQo();
			queryPojo.setLevelName(name.trim());
			queryPojo.setParentLevelId(threeParentId);
			if (null == getSpiderTypeService().findMenuId(queryPojo)) {
				entity = buildPo(name, url, threeParentId);
				getSpiderTypeService().save(entity);
//				System.out.println(JSONObject.toJSON(entity));
			}
		}
	}

	private SpiderType buildPo(String vote, String levelUrl, Integer parentLevelId) {
		SpiderType entity;
		entity = new SpiderType();
		entity.setLevelName(vote.trim());
		entity.setLevelType(LevelTypeEnum.BAIDU_TIEBA_TYPE.getId());
		entity.setLevelUrl(levelUrl);
		entity.setParentLevelId(parentLevelId);
		entity.setPlatformId(PlatfromEnum.BAIDU_BA.getId());
		return entity;
	}

	@Override
	public Site getSite() {
		return Site.me().setRetryTimes(3).setSleepTime(1000);
	}

}
