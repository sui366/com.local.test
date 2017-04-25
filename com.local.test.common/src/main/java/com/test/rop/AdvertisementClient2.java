//package com.test.rop;
//
//import org.junit.Test;
//
//import com.shunwang.rpc.appboss.client.ResourceClient;
//import com.shunwang.rpc.appboss.request.ResourceRequest;
//import com.shunwang.rpc.appboss.response.ResourceResponse;
//
//public class AdvertisementClient2 {
//
//	
//	@Test
//	public void syncData() throws Exception{
//		
//		ResourceRequest request = new ResourceRequest();
//		request.setPageNum(1);//页码；默认1
//		request.setPageSize(100);//分页大小；默认10
////		request.setStartTime(System.currentTimeMillis());//查询开始时间；可选
//		
//		ResourceClient client = new ResourceClient("http://127.0.0.1:8081/router", "10000", "ca092c6d1ce3afcb");
//		
//		ResourceResponse synccardInfo = client.synccardInfo(request);
//		synccardInfo.getLastEditTime();//更新时间
//		synccardInfo.getResources();//结果数据列表
//	}
//
//}
