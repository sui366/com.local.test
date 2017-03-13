package com.local.test.reptile.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.local.test.reptile.pojo.po.SpiderData;
import com.local.test.reptile.pojo.qo.SpiderDataQo;
import com.local.test.reptile.pojo.vo.SpiderDataVo;
import com.local.test.reptile.service.SpiderDataService;
import com.local.test.reptile.util.BeanUtil;
import com.local.test.reptile.util.Result;
import com.local.test.reptile.util.enums.LevelTypeEnum;
import com.local.test.reptile.util.enums.PlatfromEnum;
import com.shunwang.business.framework.mybatis.query.condition.Condition;
import com.shunwang.business.framework.mybatis.query.condition.ConditionFactory;
import com.shunwang.business.framework.pojo.Page;
import com.shunwang.business.framework.spring.mvc.controller.CrudController;

@Controller
public class SpiderDataController extends CrudController<SpiderData, SpiderDataService> {
	
	@RequestMapping(value = { "/", "/index.shtml", "/home.shtml"})
	public ModelAndView home(HttpServletRequest request) throws Exception {

		Result result = new Result();
		
		SpiderDataQo query = new SpiderDataQo();
		query.setOrder("order by add_time desc");
		
		List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(ConditionFactory.buildSqlCondition(String.format("task_id in (select task.id from spider_task task where task.type_id in (select type.id from spider_type type where type.platform_id=%s and type.level_type=%s))", PlatfromEnum.ENJOY.getId(),LevelTypeEnum.ENJOY_MONTH_RANK.getId())));
		
		List<SpiderData> monthRankList = this.bo.query(query, conditions);
		List<SpiderDataVo> monthRankVoList = BeanUtil.copyObjList(monthRankList, SpiderDataVo.class);
		
		conditions.clear();
		conditions.add(ConditionFactory.buildSqlCondition(String.format("task_id in (select task.id from spider_task task where task.type_id in (select type.id from spider_type type where type.platform_id=%s and type.level_type=%s))", PlatfromEnum.ENJOY.getId(),LevelTypeEnum.ENJOY_FORGE.getId())));
		List<SpiderData> forgeList = this.bo.query(query, conditions);
		List<SpiderDataVo> forgeListVoList = BeanUtil.copyObjList(forgeList, SpiderDataVo.class);
		
		result.setValue("monthRankList", monthRankVoList);
		result.setValue("forgeList", forgeListVoList);
		
		return new ModelAndView("pages/home").addObject("result", result);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/spiderDataList")
	@ResponseBody
	public Result spiderDataList(SpiderDataQo query, HttpServletRequest request, Integer currentPageNum, Integer pageSize) throws Exception {
		Result result = new Result();
		
		
		List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(ConditionFactory.buildSqlCondition(String.format("task_id in (select task.id from spider_task task where task.type_id in (select type.id from spider_type type where type.platform_id in (%s,%s)))", PlatfromEnum.GAME_SKY.getId(),PlatfromEnum.BAIDU_BA.getId())));
		
		if(null != pageSize){
			query.setLimit(pageSize);
		}
		if(null != currentPageNum){
			query.setPage(currentPageNum);
		}
		
		Page page = this.bo.list(query, conditions);
		
		page.setRows((List<SpiderDataVo>)page.getRows());
		
		result.setValue("page", page);
		
		return result;
	}
	
	
}
