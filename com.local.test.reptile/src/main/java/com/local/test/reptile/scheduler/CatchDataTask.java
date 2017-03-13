package com.local.test.reptile.scheduler;

import java.lang.reflect.Constructor;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.local.test.reptile.pojo.po.SpiderPageProcess;
import com.local.test.reptile.pojo.po.SpiderTask;
import com.local.test.reptile.pojo.po.SpiderType;
import com.local.test.reptile.pojo.qo.SpiderTaskQo;
import com.local.test.reptile.service.SpiderDataService;
import com.local.test.reptile.service.SpiderPageProcessService;
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

}
