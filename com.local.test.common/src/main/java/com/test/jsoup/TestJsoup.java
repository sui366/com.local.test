//package com.test.jsoup;
//
//import java.io.IOException;
//import java.util.Map;
//
//import org.apache.commons.lang3.RandomStringUtils;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.jsoup.Connection;
//import org.jsoup.Connection.Method;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.junit.Test;
//
//import com.google.common.collect.Maps;
//import com.google.gson.Gson;
//
//public class TestJsoup {
//
//	/**
//	 * get请求
//	 */
//	private String httpGet(String url) {
//
//		String result = "";
//		try {
//			// 根据地址获取请求
//			HttpGet request = new HttpGet(url);// 这里发送get请求
//			// 创建httpclient对象
//			CloseableHttpClient httpClient = HttpClients.createDefault();
//			// 通过请求对象获取响应对象
//			HttpResponse response = httpClient.execute(request);
//
//			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//				result = EntityUtils.toString(response.getEntity(), "utf-8");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//	/**
//	 * 获取分页
//	 * @throws IOException 
//	 * {"type":"getlabelpage","currentPage":2,"pagesize":"50","recordCount":"110929","pagesDisplay":6}
//	 */
//	@Test
//	public void getlabelpage() throws IOException {
//		String sysTimeStr = String.valueOf(System.currentTimeMillis());
//		Map<String, String> map = Maps.newHashMap();
//		map.put("type", "getlabelpage");
//		map.put("currentPage", "2");
//		map.put("pagesize", "50");
//		map.put("recordCount", "110929");
//		map.put("pagesDisplay", "6");
//
//		StringBuffer sBuffer = new StringBuffer();
//		sBuffer.append("http://db2.gamersky.com/LabelJsonpAjax.aspx?");
//		sBuffer.append("callback=jQuery" + RandomStringUtils.randomNumeric(21) + "_" + RandomStringUtils.randomNumeric(13) + "&jsondata=");
//		sBuffer.append(org.apache.catalina.util.URLEncoder.DEFAULT.encode(new Gson().toJson(map)));
//		sBuffer.append("&_=" + sysTimeStr);
//
////		System.out.println("分页数据:" + httpGet(sBuffer.toString()));
//		
////		String url = "http://db2.gamersky.com/LabelJsonpAjax.aspx";
////		HashMap<String, String> params = Maps.newHashMap();
////		params.put("callback", "jQuery" + RandomStringUtils.randomNumeric(21) + "_" + RandomStringUtils.randomNumeric(13));
////		params.put("jsondata", org.apache.catalina.util.URLEncoder.DEFAULT.encode(new Gson().toJson(map)));
////		params.put("_", sysTimeStr);
////		Connection conn = httpGet(url, params, Method.GET);
//		
//		Connection conn = Jsoup.connect("http://db2.gamersky.com/LabelJsonpAjax.aspx?callback=jQuery1830689472506986931_1488632038298&jsondata=%7B%22type%22%3A%22getlabelpage%22%2C%22currentPage%22%3A2%2C%22pagesize%22%3A%2250%22%2C%22recordCount%22%3A%22110929%22%2C%22pagesDisplay%22%3A6%7D&_=1488632068051")
//				.method(Method.GET)
//				.timeout(10000)
//				.referrer("http://www.gamersky.com/news/")
//				;
//		
//		Document doc = Jsoup.parseBodyFragment(html);  
//		Element body = doc.body();  
////		document.getElementsMatchingText(pattern)
//
//	}
////
////	/**
////	 * 获取主体数据 (img src：200*112.jpg图片地址 title：标题 txt：简介 time：发布时间/)
////	 * 
////	 * page 参数 从 分页数据中获取
////	 */
////	@Test
////	public void updatenodelabel() {
////		String sysTimeStr = String.valueOf(System.currentTimeMillis());
////		Map<String, String> map = Maps.newHashMap();
////		map.put("type", "updatenodelabel");
////		map.put("isCache", "true");
////		map.put("cacheTime", "60");
////		map.put("nodeId", "11007");
////		map.put("isNodeId", "true");
////		map.put("page", "4");// 获取第几页的数据
////
////		StringBuffer sBuffer = new StringBuffer();
////		sBuffer.append("http://db2.gamersky.com/LabelJsonpAjax.aspx?");
////		sBuffer.append("callback=jQuery" + RandomStringUtils.randomNumeric(21) + "_" + RandomStringUtils.randomNumeric(13) + "&jsondata=");
////		sBuffer.append(org.apache.catalina.util.URLEncoder.DEFAULT.encode(new Gson().toJson(map)));
////		sBuffer.append("&_=" + sysTimeStr);
////
////		String data = httpGet(sBuffer.toString());
////		data = data.replaceAll("\\s", "");
//////		data.
////		data = data.substring(30000, data.length());
////		System.out.println(data);
//////		String data = "<a href=\"http://www.gamersky.com/news/201703/874592.shtml\" target=\"_blank\" title=\"尼尔机械纪元日本销量登顶 超级机器人大战V第二\"><img src=\"http://img1.gamersky.com/image2017/03/20170301_cks_170_24/gamersky_01origin_01_2017312120FAF.jpg\" alt=\"尼尔机械纪元日本销量登顶 超级机器人大战V第二\"  width=\"200\" height=\"110\" class=\"pe_u_thumb\" border=\"0\"></a>>target=\"_blank\" title=\"尼尔机械纪元日本销量登顶 超级机器人大战V第二\"><img src=\"http://img1.gamersky.com/image2017/03/20170301_cks_170_24/gamersky_01origin_01_2017312120FAF.jpg\"";
////		Pattern p = Pattern.compile("title=\"[\\S|(?![<|>])]+\\s*[\\S|(?![<|>])]+\">");
////		Matcher m = p.matcher(data);
////		while (m.find()) {
////			System.out.println(m.group());
////		}
////		// System.out.println("分页主体数据:" + httpGet(sBuffer.toString()));
////
////	}
////
////	/**
////	 * 评论数
////	 * 
////	 * 请求参数： 从主体数据中获取所有data-sid的值拼成字符串 874082,874013 返回参数：
////	 * "874082":{"id":"874082","comments":90},"874013":{"id":"874013","comments"
////	 * :105}
////	 * 
////	 * 效果:<div class="pls cy_comment" data-sid="874082">90</div>
////	 */
////	@Test
////	public void count() {
////		String sysTimeStr = String.valueOf(System.currentTimeMillis());
////
////		StringBuffer sBuffer = new StringBuffer();
////		sBuffer.append("http://cm.gamersky.com/commentapi/count?");
////		sBuffer.append("callback=jQuery" + RandomStringUtils.randomNumeric(21) + "_" + RandomStringUtils.randomNumeric(13) + "&jsondata=");
////		sBuffer.append("&_=" + sysTimeStr);
////
////		System.out.println("评论数据:" + httpGet(sBuffer.toString()));
////
////	}
//}
