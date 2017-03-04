package com.test.httpClient;

import java.util.Map;

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
 * @Description: TODO  模拟抓取百度贴吧
 */
public class BaiduData {

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
		Map<String, String> map = Maps.newHashMap();
		map.put("cn", "香港电影");
		map.put("ci", "0");
		map.put("pcn", "电影");
		map.put("pci", "0");
		map.put("ct", "1");
		map.put("st", "new");
		map.put("pn", "17");

		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("http://www.tieba.com/f/index/forumpark?");
		sBuffer.append(org.apache.catalina.util.URLEncoder.DEFAULT.encode(new Gson().toJson(map)));

		System.out.println("分页数据:" + httpGet(sBuffer.toString()));

	}

}