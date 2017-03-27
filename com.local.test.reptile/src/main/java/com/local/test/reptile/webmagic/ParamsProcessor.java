package com.local.test.reptile.webmagic;

import com.local.test.reptile.service.SpiderDataService;
import com.local.test.reptile.service.SpiderTypeService;

public class ParamsProcessor {

	private SpiderTypeService spiderTypeService;
	private SpiderDataService spiderDataService;
	private Integer taskId;
	private Integer typeId;
	
	public ParamsProcessor(SpiderDataService spiderDataService, SpiderTypeService spiderTypeService, Integer taskId, Integer typeId) {
		this.spiderTypeService = spiderTypeService;
		this.spiderDataService = spiderDataService;
		 this.taskId = taskId;
		 this.typeId = typeId;
	}

	public SpiderTypeService getSpiderTypeService() {
		return spiderTypeService;
	}

	public SpiderDataService getSpiderDataService() {
		return spiderDataService;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public Integer getTypeId() {
		return typeId;
	}
	
	
}
