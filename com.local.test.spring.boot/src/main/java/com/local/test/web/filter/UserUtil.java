package com.local.test.web.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.shunwang.back.common.UserContext;
import com.shunwang.back.rpc.response.PermistionQueryResponse;
import com.shunwang.oauth2.sdk.context.OAuth2Context;
import com.shunwang.permission.core.action.pojo.Action;

public class UserUtil {

	/**
	 * 获取权限校验地址
	 */
	public String authPath(){
		return OAuth2Context.OAUTH2_URL;
	}
	
	/**
	 * 获取当前用户名
	 */
	public String currentUserName(){
		return com.shunwang.permission.core.UserContext.getInstance().getUserName();
	}
	
	/**
	 * 按钮权限校验
	 */
	public boolean permissionBtn(String urls, HttpServletRequest request){
		boolean result = false;
		if(StringUtils.isEmpty(urls)){
			return false;
		}
		PermistionQueryResponse permission = UserContext.getPermistion(request);
		if(null == permission){
			return false;
		}
		Map<String, Action> actionMap = permission.getActionsByUser();
		if (actionMap == null) {
			return false;
		}
		String[] urlArray = urls.split(",");
		for(String url : urlArray){
			if(actionMap.containsKey(url)){
				result = true;
				break;
			}
		}
		return result;
	}
	
//	/**
//	 * 左侧菜单中IDC程序监控数据 按照 IDC 要求 通过 monitor_objects 配置实现 节点的是否展现
//	 * 数据格式：产品ID_程序类型ID,产品ID_程序类型ID,产品ID_程序类型ID...
//	 */
//	@SuppressWarnings("unchecked")
//	public boolean monitorObjects(String types, HttpServletRequest request){
//		boolean result = false;
//		if(StringUtils.isEmpty(types)){
//			return false;
//		}
//		
//		List<String> sessionTypes = (List<String>)request.getSession().getAttribute(Constants.MONITOR_OBJECT_TYPES);
//		
//		String[] reqTypeStrs = types.split(",");
//		if(CollectionUtil.isNotEmpty(sessionTypes)){
//			for(String type : reqTypeStrs){
//				if(sessionTypes.contains(type)){
//					result = true;
//					break;
//				}
//			}
//		}
//		
//		return result;
//	}

}
