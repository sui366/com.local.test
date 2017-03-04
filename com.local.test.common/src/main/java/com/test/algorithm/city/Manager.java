package com.test.algorithm.city;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Manager {

//	public static void main(String[] args) {
//		String JsonContext = new JsonUtil().ReadFile("https://rawgit.com/ecomfe/echarts/master/map/json/china-cities.json");
//		JSONArray jsonArray = JSONArray.fromObject(JsonContext);
//		int size = jsonArray.size();
//		for (int i = 0; i < size; i++) {
//			JSONObject jsonObject = jsonArray.getJSONObject(i);
//			JSONArray jsonArray2 = JSONArray.fromObject(jsonObject.getString("public").toString());
//			int size1 = jsonArray2.size();
//			for (int j = 0; j < size1; j++) {
//				JSONObject jsonObject1 = jsonArray2.getJSONObject(j);
//				if (jsonObject1.getInt("version") == 4) {
//					System.out.println("对外IPv4地址：" + jsonObject1.getString("addr"));
//				} else {
//					System.out.println("对外IPv6地址：" + jsonObject1.getString("addr"));
//				}
//			}
//		}
//	}
	
	public static void main(String args[]){
        JsonParser parse =new JsonParser();  //创建json解析器
        try {
            JsonObject json=(JsonObject) parse.parse(new FileReader("D:\\temp\\cityData3.json"));  //创建jsonObject对象
             
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
	
//	public static void main(String[] args) {
//		try {
//			List<String> readLines = Files.readLines(new File("D:\\temp\\cityData2.json"), Charset.forName("UTF-8"));
//			 for(String str:readLines){
//				 System.out.println(str.replaceAll("\"province\": \"[\\S]*\":\\[city\": ", ""));
//			 }
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
