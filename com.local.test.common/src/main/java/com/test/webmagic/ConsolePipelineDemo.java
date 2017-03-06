//package com.test.webmagic;
//
//import us.codecraft.webmagic.Spider;
//import us.codecraft.webmagic.pipeline.JsonFilePipeline;
//
//public class ConsolePipelineDemo {
//	public static void main(String[] args) {
//	    Spider.create(new GithubRepoPageProcessor())
//	            //从"https://github.com/code4craft"开始抓
//	            .addUrl("http://www.dytt8.net/index.htm")
//	            .addPipeline(new JsonFilePipeline("D:\\webmagic\\"))
////	            .addPipeline(new ConsolePipeline())
//	            //开启5个线程抓取
//	            .thread(2)
//	            //启动爬虫 
//	            .run();
//	}
//}
