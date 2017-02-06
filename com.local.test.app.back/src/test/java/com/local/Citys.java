package com.local;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.io.Files;

public class Citys {
	
	public static void main(String[] args) {
//		try {
			
//			File file = new File("D:\\temp\\test5.txt");
//			Files.write("".getBytes(), file);
//			
//			List<String> list1 = Files.readLines(new File("D:\\temp\\整理后城市.txt"), Charset.forName("GBK"));
//			
//			List<String> readLines2 = Files.readLines(new File("D:\\temp\\test7.txt"), Charset.defaultCharset());
//			
//			List list2 = Lists.newArrayList();
//			for(String str:readLines2){
//				if(StringUtils.isBlank(str)){
//					continue;
//				}
//				list2.add((Citys.getChinese(str)));
//			}
//			
//			for(String str:list1){
//				if(!list2.contains(str)){
//					System.out.println(str);
//				}
//			}
//			
//			
//			
//		
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		
		
//		try {
//			
////			File file = new File("D:\\temp\\test3.txt");
////			Files.write("".getBytes(), file);
//			
//			List<String> list3 = Files.readLines(new File("D:\\temp\\test3.txt"), Charset.defaultCharset());
//			
//			List<String> list4 = Files.readLines(new File("D:\\temp\\china-cities.json"), Charset.defaultCharset());
//			
////			List<String> readLines2 = Files.readLines(new File("D:\\temp\\geoCoor.json"), Charset.defaultCharset());
//			
//			StringBuffer sBuffer = new StringBuffer();
//			List list2 = Lists.newArrayList();
//			for(String str:list4){
//				sBuffer.append(str);
//			}
//			File file = new File("D:\\temp\\test4.txt");
//			Files.write("".getBytes(), file);
////			for(String str:list3){
////				System.out.println(str);
////				String regex = "成都\\(资阳\\)";
////				String regex = "\\[\\d{1,9}.\\d{1,9},\\d{1,9}.\\d{1,9}\\],\"name\":\""+str+"(([\u4e00-\u9fa5]+))*\"";
//				String regex = "\\[\\d{1,9}.\\d{1,9},\\d{1,9}.\\d{1,9}\\],\"name\":\"[\u4e00-\u9fa5]+(([\u4e00-\u9fa5]+))*\"";
//				String result = "";
//				Matcher matcher = Pattern.compile(regex).matcher(sBuffer.toString());
//				while (matcher.find()) {
//					result= matcher.group(0);
//					if(StringUtils.isNotBlank(result))
////						System.out.println(result);
//						Files.append(result +"\n", file, Charset.defaultCharset());
//				}
////			}
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			List<String> readLines2 = Files.readLines(new File("D:\\temp\\test4.txt"), Charset.defaultCharset());
//			
//			File file = new File("D:\\temp\\test7.txt");
//			for(String str:readLines2){
//				Files.append(String.format("\"%s\":%s", getChinese(str), getAddress(str)) +"\n", file, Charset.defaultCharset());
//			}
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			cleanData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getChinese(String paramValue) {
		String regex = "([\u4e00-\u9fa5]+)";
		String str = "";
		Matcher matcher = Pattern.compile(regex).matcher(paramValue);
		while (matcher.find()) {
		str+= matcher.group(0);
		}
		return str;
		}
	
	public static String getAddress(String paramValue) {
		String regex = "\\[\\d{1,9}.\\d{1,9},\\d{1,9}.\\d{1,9}\\],";
		String str = "";
		Matcher matcher = Pattern.compile(regex).matcher(paramValue);
		while (matcher.find()) {
			str+= matcher.group(0);
		}
		return str;
	}
	
	public static void cleanData() throws Exception{
		List<String> readLines = Files.readLines(new File("C:\\Users\\xf.sui\\Desktop\\latlon_48924.txt"), Charset.defaultCharset());

		for(String str:readLines){
			String[] strs = str.split(",");
			System.out.println(String.format("\"%s\":[%s,%s],", strs[8], strs[5], strs[4]));
		}
	
	}
}
