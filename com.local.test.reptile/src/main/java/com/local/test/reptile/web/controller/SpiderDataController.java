package com.local.test.reptile.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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

	@RequestMapping(value = { "/", "/index.shtml", "/home.shtml" })
	public ModelAndView home(HttpServletRequest request) throws Exception {

		Result result = new Result();

		SpiderDataQo query = new SpiderDataQo();
		query.setOrder("order by add_time desc");

		List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(ConditionFactory.buildSqlCondition(String.format("task_id in (select task.id from spider_task task where task.type_id in (select type.id from spider_type type where type.platform_id=%s and type.level_type=%s))", PlatfromEnum.ENJOY.getId(), LevelTypeEnum.ENJOY_MONTH_RANK.getId())));

		List<SpiderData> monthRankList = this.bo.query(query, conditions);
		List<SpiderDataVo> monthRankVoList = BeanUtil.copyObjList(monthRankList, SpiderDataVo.class);

		conditions.clear();
		conditions.add(ConditionFactory.buildSqlCondition(String.format("task_id in (select task.id from spider_task task where task.type_id in (select type.id from spider_type type where type.platform_id=%s and type.level_type=%s))", PlatfromEnum.ENJOY.getId(), LevelTypeEnum.ENJOY_FORGE.getId())));
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
		conditions.add(ConditionFactory.buildSqlCondition(String.format("task_id in (select task.id from spider_task task where task.type_id in (select type.id from spider_type type where type.platform_id in (%s,%s,%s) and type.level_type not in(%s,%s)))", PlatfromEnum.GAME_SKY.getId(), PlatfromEnum.BAIDU_BA.getId(), PlatfromEnum.ENJOY.getId(), LevelTypeEnum.ENJOY_MONTH_RANK.getId(), LevelTypeEnum.ENJOY_FORGE.getId())));

		if (null != pageSize) {
			query.setLimit(pageSize);
		}
		if (null != currentPageNum) {
			query.setPage(currentPageNum);
		}

		Page page = this.bo.list(query, conditions);

		page.setRows((List<SpiderDataVo>) page.getRows());

		result.setValue("page", page);

		return result;
	}

	/**
	 * 查看文章详情
	 */
	@RequestMapping("/loadContent.shtml")
	public ModelAndView loadContent(HttpServletRequest request, SpiderDataQo query) throws Exception {
		Result result = new Result();

		SpiderData spiderData = this.bo.get(query.getId());
		SpiderDataVo vo = BeanUtil.copyProperties(spiderData, SpiderDataVo.class);

		result.setValue("obj", vo);

		if (vo.getPlatformId().intValue() == PlatfromEnum.GAME_SKY.getId().intValue()) {
			return new ModelAndView("pages/gameSky-content").addObject("result", result);
		} else if (vo.getPlatformId().intValue() == PlatfromEnum.ENJOY.getId().intValue()) {
			return new ModelAndView("pages/enjoy-content").addObject("result", result);
		} else {
			return null;
		}

	}

	/**
	 * 查看百度的图片
	 */
	@RequestMapping("/readBaiduImage.shtml")
	public void readBaiduImage(HttpServletResponse response, String imageUrl) throws Exception {

		// Create global request configuration
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(120 * 1000).setConnectTimeout(120 * 1000).build();

		// Create an HttpClient with the given custom dependencies and
		// configuration.
		CloseableHttpClient httpclient = HttpClients.custom().setUserAgent("Mozilla/5.0 Firefox/26.0").setMaxConnTotal(120).setMaxConnPerRoute(120).setDefaultRequestConfig(defaultRequestConfig).build();

		HttpGet httpget = null;
		if(imageUrl.endsWith(".jpg")){
			httpget = new HttpGet(imageUrl);
		}else{
			httpget = new HttpGet("http://c.hiphotos.baidu.com/forum/whfpf%3D84%2C88%2C40%3Bq%3D90/sign=3c0b7b4c4e10b912bf94a5bea5c0c437/21087bf40ad162d903c95eeb18dfa9ec8a13cd26.jpg");
		}
		

//		String regex = "([\\S|\\s]+)(\\d+,\\d+)([\\S|\\s]*)";
		// String regex = "([\\S|\\s]+)(whfpf=)([\\S|\\s]*)";
		// if(!imageUrl.replaceAll(regex, "$2").isEmpty()){
		// httpget = new HttpGet("http://c.hiphotos.baidu.com/forum/whfpf%3D84%2C88%2C40%3Bq%3D90/sign=3c0b7b4c4e10b912bf94a5bea5c0c437/21087bf40ad162d903c95eeb18dfa9ec8a13cd26.jpg");
		// }

		httpget.setHeader("Referer", PlatfromEnum.BAIDU_BA.getUrl());

		System.out.println("executing request " + httpget.getURI());
		CloseableHttpResponse httpResponse = httpclient.execute(httpget);

		try {
			HttpEntity entity = httpResponse.getEntity();

			if (httpResponse.getStatusLine().getStatusCode() >= 400) {
				throw new IOException("Got bad response, error code = " + httpResponse.getStatusLine().getStatusCode() + " imageUrl: " + imageUrl);
			}
			if (entity != null) {
				InputStream input = entity.getContent();
				response.setContentType("image/*"); // 设置返回的文件类型
				// response.setContentType("Content-type: image/jpeg"); //
				// 设置返回的文件类型
				OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象

				int CACHE_SIZE = 1024 * 100;
				byte[] cache = new byte[CACHE_SIZE];
				try {
					int nRead = 0;
					while ((nRead = input.read(cache, 0, CACHE_SIZE)) != -1) {
						toClient.write(cache, 0, nRead);
					}
				} finally {
					input.close();
					toClient.close();
				}
			}
		} finally {
			httpResponse.close();

		}
	}


}
