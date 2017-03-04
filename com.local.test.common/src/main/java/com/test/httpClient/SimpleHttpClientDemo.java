package com.test.httpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.google.common.collect.Maps;
import com.google.gson.Gson;

import japa.parser.ParseException;

public class SimpleHttpClientDemo {

	/**
	 * 模拟请求
	 * 
	 * @param url
	 *            资源地址
	 * @param map
	 *            参数列表
	 * @param encoding
	 *            编码
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String send(String url, Map<String, String> map, String encoding) throws ParseException, IOException {
		String body = "";

		// 创建httpclient对象
		CloseableHttpClient client = HttpClients.createDefault();
		// 创建post方式请求对象
		HttpPost httpPost = new HttpPost(url);

		// 装填参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (map != null) {
			for (Entry<String, String> entry : map.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		// 设置参数到请求对象中
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

		System.out.println("请求地址：" + url);
		System.out.println("请求参数：" + nvps.toString());

		// 设置header信息
		// 指定报文头【Content-type】、【User-Agent】
		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
		httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		// 执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = client.execute(httpPost);
		// 获取结果实体
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			// 按指定编码转换结果实体为String类型
			body = EntityUtils.toString(entity, encoding);
		}
		EntityUtils.consume(entity);
		// 释放链接
		response.close();
		return body;
	}
	
	@Test
	public void testNormal() throws ParseException, IOException{
		String url="http://php.weather.sina.com.cn/iframe/index/w_cl.php";  
        Map<String, String> map = new HashMap<String, String>();  
        map.put("code", "js");  
        map.put("day", "0");  
        map.put("city", "上海");  
        map.put("dfc", "1");  
        map.put("charset", "utf-8");  
        String body = SimpleHttpClientDemo.send(url, map,"utf-8");  
        System.out.println("交易响应结果：");  
        System.out.println(body);  
  
        System.out.println("-----------------------------------");  
  
        map.put("city", "北京");  
        body = SimpleHttpClientDemo.send(url, map, "utf-8");  
        System.out.println("交易响应结果：");  
        System.out.println(body);  
	}
	
	@Test
	public void httpGet(){
		
		String sysTimeStr = String.valueOf(System.currentTimeMillis());
		
		Map<String, String> map = Maps.newHashMap();
//		jsondata:{"type":"getlabelpage","currentPage":2,"pagesize":"50","recordCount":"110854","pagesDisplay":6}
		map.put("type", "getlabelpage");
		map.put("currentPage", "2");
		map.put("pagesize", "50");
		map.put("recordCount", "110854");
		map.put("pagesDisplay", "6");
		
		Gson gson = new Gson();  
		
		String json = gson.toJson(map);
		
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("http://db2.gamersky.com/LabelJsonpAjax.aspx?"); 
		sBuffer.append("callback=jQuery183032957754508413006_1488522699257&jsondata=");
		sBuffer.append(org.apache.catalina.util.URLEncoder.DEFAULT.encode(json));
		sBuffer.append("&_="+sysTimeStr);
        System.out.println("url="+sBuffer);
		
		String result="";
          try {
                // 根据地址获取请求
                HttpGet request = new HttpGet(sBuffer.toString());//这里发送get请求
             // 创建httpclient对象
        		CloseableHttpClient httpClient = HttpClients.createDefault();
                // 通过请求对象获取响应对象
                HttpResponse response = httpClient.execute(request);
                
                // 判断网络连接状态码是否正常(0--200都数正常)
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    result= EntityUtils.toString(response.getEntity(),"utf-8");
                } 
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
          System.out.println(result);
    }
	
//	@Test
//	public void testNormal() throws ParseException, IOException{
//		
//	}
	
	
}