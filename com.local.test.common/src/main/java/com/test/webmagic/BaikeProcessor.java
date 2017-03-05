//package com.test.webmagic;
//
//import java.util.List;
//
//import us.codecraft.webmagic.Page;
//import us.codecraft.webmagic.Request;
//import us.codecraft.webmagic.Site;
//import us.codecraft.webmagic.Spider;
//import us.codecraft.webmagic.processor.PageProcessor;
//
////当前爬虫为爬取百度百科上，人物分类页面下的人物信息，只是简单的例子
//public class BaikeProcessor implements PageProcessor{
//    
//    //总页数
//    private int totalPage = 10;
//    
//    //当前页数
//    private int currentPage = 1;
//    
//    //抓取网站的相关配置，包括编码、抓取间隔、重试次数等
//    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(20000).setCharset("utf-8");
//
//    
//    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
//    public void process(Page page) {
//        
//        dealPage(page);
//  
//    }
//
//    public Site getSite() {
//        return site;
//    }
//
//    //执行测试
//    public static void main(String[] args) {
//
//        Spider.create(new BaikeProcessor())
//                .addUrl("http://baike.baidu.com/fenlei/%E6%94%BF%E6%B2%BB%E4%BA%BA%E7%89%A9?limit=30&amp;index=1&amp;offset=0#gotoList")
//                //开启1个线程抓取
//                .thread(1)
//                //启动爬虫
//                .run();
//    }
//    
//    //是否列表页
//    public boolean isListPage(Page page){
//        String url=page.getUrl().toString();
//        if(!url.contains("fenlei"))
//        {
//            return false;
//        }else{
//            return true;
//        }
//    }
//    
//    public void dealPage(Page page){
//        String url = page.getUrl().toString();
//        
//        //判断是否为列表页
//        if(isListPage(page)){
//            if(currentPage < totalPage){
//                //加入下一个列表页
//                 String nexturl="http://baike.baidu.com/fenlei/%E6%94%BF%E6%B2%BB%E4%BA%BA%E7%89%A9?limit=30&amp;index="+(currentPage+1)+"&amp;offset=0#gotoList";
//                 Request request=new Request(nexturl/*.replace(" ","%20")*/);
//              
//                 page.addTargetRequest(request);
//                 currentPage++;
//                 
//                 //加入content页
//                 List<String> urls=page.getHtml().xpath("//div[@class='grid-list']/ul/li/div[@class='list']").links().all();
//                 
//                 for(String content_url:urls){
//                     Request request2=new Request(content_url/*.replace(" ","%20")*/);
//                     request2.setPriority(10000);
//                     page.addTargetRequest(request2);
//                 }
//            }
//        }else{
//            //处理content页面,这里只是简单的打印出,标题以及部分简短的简介
//            try {
//                String rawHtml=page.getRawText();
//                JXDocument document=new JXDocument(rawHtml);
//                
//                List<Object> title = document.sel("//div[@class='content']//div[@class='main-content']/dl/dd/h1/text()");
//                List<Object> description = document.sel("//div[@class='main-content']/div[@class='lemma-summary']/div[@class='para']/text()");
//                
//                if(title.size()>0) System.out.println(title.get(0)+":");
//                if(description.size()>0) System.out.println(description.get(0));
//                
//                
//            } catch (XpathSyntaxErrorException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//}
//
