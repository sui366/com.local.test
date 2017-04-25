package com.test.rop;

import java.util.Calendar;

import org.junit.Test;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import com.rop.client.CompositeResponse;
import com.shunwang.appBoss.interfaces.api.msg.appStore.SmallCartoonAddRequest;
import com.shunwang.appBoss.interfaces.api.msg.appStore.SmallCartoonAddResponse;
import com.shunwang.appBoss.interfaces.api.util.ErrorResponseHelper;
import com.shunwang.appBoss.interfaces.sdk.ConnectHandler;
import com.shunwang.appBoss.interfaces.sdk.appStore.AdvertisementHandler;

public class AdvertisementClient {

//	public static final String SERVER_URL = "http://127.0.0.1:8088/router";
//	public static final String SERVER_URL = "http://127.0.0.1:8080/interfaces-server/router";
//	public static final String SERVER_URL = "http://192.168.9.194:8084/router";
//	public static final String APP_KEY = "10000";
//	public static final String APP_SECRET = "ca092c6d1ce3afcb";
//	private DefaultRopClient ropClient = new DefaultRopClient(SERVER_URL, APP_KEY, APP_SECRET);

	{
//		ropClient.setMessageFormat(MessageFormat.json);

	}

	/**
	 * 
	 * @Title: addSmallCartoon
	 * @Description: TODO 新增小动画
	 * @param SmallCartoonAddRequest
	 *            请求参数
	 * @return
	 * @throws Exception
	 * @return: SmallCartoonAddResponse 返回参数
	 */
//	@Test
//	public void addSmallCartoon() throws Exception {
//		SmallCartoonAddRequest request = new SmallCartoonAddRequest();
//
//		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.DATE, -1);
//		request.setBeginDate(calendar.getTime().getTime());
//		calendar = Calendar.getInstance();
//		calendar.add(Calendar.DATE, -1);
//		request.setEndDate(calendar.getTime().getTime());
//		request.setAdId(1119);
//		request.setImagePath("中国q1");
//		request.setImgWidth(123);
//		request.setPlaySpeed(345);
//		request.setResourceId(4);
////		ropClient.addRopConvertor(new DateConverter());
//		SmallCartoonAddResponse response = null;
//		CompositeResponse<?> composite = ropClient.buildClientRequest().post(request, SmallCartoonAddResponse.class, "appStore.addSmallCartoon", "1.0");
//		if (composite.isSuccessful()) {
//			response = (SmallCartoonAddResponse) composite.getSuccessResponse();
//
//			if (response.isSuccess()) {
//				// System.out.println(String.format("错误码：%s；错误信息:%s",
//				// response.getCode(), response.getMessage()));
//				System.out.println("执行成功");
//			} else {
//				System.out.println(String.format("错误码：%s；错误信息:%s", response.getCode(), response.getMessage()));
//			}
//		} else {
//			System.out.println(ErrorResponseHelper.getFormatStr(composite.getErrorResponse()));
//		}
//	}
	
	@Test
	public void addSmallCartoon2() throws Exception{
		
		SmallCartoonAddRequest request = new SmallCartoonAddRequest();

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		request.setBeginDate(calendar.getTime().getTime());
		calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		request.setEndDate(calendar.getTime().getTime());
		request.setAdId(111234);
		request.setImagePath("http://9xi.xcm.icafe28.net/v9images/2017-04-20/16_1492665651425.png");
		request.setImgWidth(123);
		request.setPlaySpeed(345);
		request.setResourceId(4);
//		ropClient.addRopConvertor(new DateConverter());
		SmallCartoonAddResponse response = null;
		
		ConnectHandler connectHandler = new ConnectHandler();
		connectHandler.setServerUrl("http://127.0.0.1:8088/router");
		connectHandler.setAppKey("10000");
		connectHandler.setAppSecret("ca092c6d1ce3afcb");
		connectHandler.setClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
		connectHandler.init();
		
		AdvertisementHandler advertisementHandler = new AdvertisementHandler();
		advertisementHandler.setConnectHandler(connectHandler);
		
		CompositeResponse<?> composite = advertisementHandler.addSmallCartoon(request);
		
		if (composite.isSuccessful()) {
			response = (SmallCartoonAddResponse) composite.getSuccessResponse();

			if (response.isSuccess()) {
				System.out.println("执行成功");
			} else {
				System.out.println(String.format("错误码：%s；错误信息:%s", response.getCode(), response.getMessage()));
			}
		} else {
			System.out.println(ErrorResponseHelper.getFormatStr(composite.getErrorResponse()));
		}
	}

}
