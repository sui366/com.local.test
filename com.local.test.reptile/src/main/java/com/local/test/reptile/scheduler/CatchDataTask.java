package com.local.test.reptile.scheduler;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.local.test.reptile.pojo.po.SpiderPageProcess;
import com.local.test.reptile.pojo.po.SpiderTask;
import com.local.test.reptile.pojo.po.SpiderTaskFull;
import com.local.test.reptile.pojo.po.SpiderType;
import com.local.test.reptile.pojo.qo.SpiderTaskFullQo;
import com.local.test.reptile.pojo.qo.SpiderTaskQo;
import com.local.test.reptile.service.SpiderDataService;
import com.local.test.reptile.service.SpiderPageProcessService;
import com.local.test.reptile.service.SpiderTaskFullService;
import com.local.test.reptile.service.SpiderTaskService;
import com.local.test.reptile.service.SpiderTypeService;
import com.local.test.reptile.util.enums.TaskStatusEnum;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
@Configurable
@EnableScheduling
public class CatchDataTask {

	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
	private SpiderTypeService spiderTypeService;

	@Autowired
	private SpiderDataService spiderDataService;

	@Autowired
	private SpiderPageProcessService spiderPageProcessService;

	@Autowired
	private SpiderTaskService spiderTaskService;
	@Autowired
	private SpiderTaskFullService spiderTaskFullService;

	/**
	 * 定时抓取 游牧星空 菜单
	 */
	@Scheduled(cron = "0 0/1 * * * ?")
	public void pcGameTypeProcessorData() throws Exception {

		// Spider.create(new
		// PcGameTypeProcessor(spiderTypeService)).addUrl("http://www.gamersky.com/pcgame/").run();
		//
		// Spider.create(new
		// InternetGameTypeProcessor(spiderTypeService)).addUrl("http://ol.gamersky.com/").run();
		//
		// Spider.create(new
		// MobileTypeProcessor(spiderTypeService)).addUrl("http://shouyou.gamersky.com/").run();

	}

	/**
	 * 游牧星空
	 */
	// @Scheduled(cron = "0 0/1 * * * ?")
	@Scheduled(cron = "0/10 * * * * ?")
	public void gameSkyData() throws Exception {

		SpiderTaskQo spiderTaskQo = new SpiderTaskQo();
		spiderTaskQo.setRp(999);
		spiderTaskQo.setStatus(TaskStatusEnum.RUN.getId());
		List<SpiderTask> taskList = spiderTaskService.query(spiderTaskQo);

		SpiderPageProcess spiderPageProcess = null;
		for (SpiderTask task : taskList) {

			spiderPageProcess = spiderPageProcessService.get(task.getPageProcessId());

			SpiderType spiderType = spiderTypeService.get(task.getTypeId());
			
			Class<?> processClass = Class.forName(spiderPageProcess.getProcessClass());

			Constructor<?> declaredConstructor = processClass.getDeclaredConstructor(SpiderDataService.class, SpiderTypeService.class, Integer.class, Integer.class);

			Object newInstance = declaredConstructor.newInstance(spiderDataService, spiderTypeService, task.getId(), spiderType.getId());

			Spider.create((PageProcessor) newInstance).addUrl(spiderType.getLevelUrl()).run();

		}

	}
	/**
	 * 游牧星空 - 全量
	 */
	@Scheduled(cron = "30 1 17 * * ?")
	public void gameSkyDataFull() throws Exception {
		
		SpiderTaskFullQo spiderTaskFullQo = new SpiderTaskFullQo();
		spiderTaskFullQo.setRp(999);
		spiderTaskFullQo.setStatus(TaskStatusEnum.RUN.getId());
		List<SpiderTaskFull> taskList = spiderTaskFullService.query(spiderTaskFullQo);
		
		SpiderPageProcess spiderPageProcess = null;
		for (SpiderTaskFull task : taskList) {
			
			spiderPageProcess = spiderPageProcessService.get(task.getPageProcessId());
			
			SpiderType spiderType = spiderTypeService.get(task.getTypeId());
			
			Class<?> processClass = Class.forName(spiderPageProcess.getProcessClass());
			
			Constructor<?> declaredConstructor = processClass.getDeclaredConstructor(SpiderDataService.class, SpiderTypeService.class, Integer.class, Integer.class);
			
			Object newInstance = declaredConstructor.newInstance(spiderDataService, spiderTypeService, task.getId(), spiderType.getId());
			
			Integer endPageNum = task.getEndPageNum();
			Integer startPageNum = task.getStartPageNum();
			String url = "";
			HashSet<String> urlSet = Sets.newHashSet();
			for(int i=startPageNum; i>=endPageNum; i++){
				url = buildUrl(task.getNodeId(), i, task.getUrl()).toString();
				urlSet.add(url);
			}
			String[] urls = new String[urlSet.size()];
			urlSet.toArray(urls);
			Spider.create((PageProcessor) newInstance).addUrl(urls).run();
		}
		
	}
	
	private StringBuffer buildUrl(Integer nodeId, Integer page, String url) {
		String sysTimeStr = String.valueOf(System.currentTimeMillis());
		Map<String, String> map = Maps.newHashMap();
		map.put("type", "updatenodelabel");
		map.put("isCache", "true");
		map.put("cacheTime", "60");
		map.put("nodeId", nodeId.toString());
		map.put("isNodeId", "true");
		map.put("page", page.toString());// 获取第几页的数据

		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(url+"?");
		sBuffer.append("callback=jQuery" + RandomStringUtils.randomNumeric(21) + "_" + RandomStringUtils.randomNumeric(13) + "&jsondata=");
		sBuffer.append(org.apache.catalina.util.URLEncoder.DEFAULT.encode(new Gson().toJson(map)));
		sBuffer.append("&_=" + sysTimeStr);
		return sBuffer;
	}

}
