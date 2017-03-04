package com.test.httpClient;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
/**
 * @Description: TODO  模拟抓取游牧星空的新闻数据
 */
public class GamerskyData {

	/**
	 * get请求
	 */
	private String httpGet(String url) {

		String result = "";
		try {
			// 根据地址获取请求
			HttpGet request = new HttpGet(url);// 这里发送get请求
			// 创建httpclient对象
			CloseableHttpClient httpClient = HttpClients.createDefault();
			// 通过请求对象获取响应对象
			HttpResponse response = httpClient.execute(request);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取分页
	 */
	@Test
	public void getlabelpage() {
		String sysTimeStr = String.valueOf(System.currentTimeMillis());
		Map<String, String> map = Maps.newHashMap();
		map.put("type", "getlabelpage");
		map.put("currentPage", "2");
		map.put("pagesize", "50");
		map.put("recordCount", "110854");
		map.put("pagesDisplay", "6");

		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("http://db2.gamersky.com/LabelJsonpAjax.aspx?");
		sBuffer.append("callback=jQuery" + RandomStringUtils.randomNumeric(21) + "_" + RandomStringUtils.randomNumeric(13) + "&jsondata=");
		sBuffer.append(org.apache.catalina.util.URLEncoder.DEFAULT.encode(new Gson().toJson(map)));
		sBuffer.append("&_=" + sysTimeStr);

		System.out.println("分页数据:" + httpGet(sBuffer.toString()));

	}

	/**
	 * 获取主体数据 (img src：200*112.jpg图片地址 title：标题 txt：简介 time：发布时间/)
	 * 
	 * page 参数 从 分页数据中获取
	 */
	@Test
	public void updatenodelabel() {
		String sysTimeStr = String.valueOf(System.currentTimeMillis());
		Map<String, String> map = Maps.newHashMap();
		map.put("type", "updatenodelabel");
		map.put("isCache", "true");
		map.put("cacheTime", "60");
		map.put("nodeId", "11007");
		map.put("isNodeId", "true");
		map.put("page", "4");// 获取第几页的数据

		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("http://db2.gamersky.com/LabelJsonpAjax.aspx?");
		sBuffer.append("callback=jQuery" + RandomStringUtils.randomNumeric(21) + "_" + RandomStringUtils.randomNumeric(13) + "&jsondata=");
		sBuffer.append(org.apache.catalina.util.URLEncoder.DEFAULT.encode(new Gson().toJson(map)));
		sBuffer.append("&_=" + sysTimeStr);

		System.out.println("分页主体数据:" + httpGet(sBuffer.toString()));

	}

	/**
	 * 评论数
	 * 
	 * 请求参数： 从主体数据中获取所有data-sid的值拼成字符串 874082,874013 返回参数：
	 * "874082":{"id":"874082","comments":90},"874013":{"id":"874013","comments"
	 * :105}
	 * 
	 * 效果:<div class="pls cy_comment" data-sid="874082">90</div>
	 */
	@Test
	public void count() {
		String sysTimeStr = String.valueOf(System.currentTimeMillis());

		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("http://cm.gamersky.com/commentapi/count?");
		sBuffer.append("callback=jQuery" + RandomStringUtils.randomNumeric(21) + "_" + RandomStringUtils.randomNumeric(13) + "&jsondata=");
		sBuffer.append("&_=" + sysTimeStr);

		System.out.println("评论数据:" + httpGet(sBuffer.toString()));

	}

	@Test
	public void test() {

	}
}