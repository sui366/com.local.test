<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>DWR DEMO</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type="text/javascript" src="dwr/interface/MessagePush.js"></script>

<script type="text/javascript">
	//通过该方法与后台交互，确保推送时能找到指定用户  
	function onPageLoad() {
		var userId = '${userinfo.userId}';
		MessagePush.onPageLoad(userId);
	}
	//推送信息  
	function showMessage(autoMessage) {
		alert(autoMessage);
	}
</script>
<body onload="onPageLoad();dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);;">
	This is my DWR DEOM page.
	<hr>
	<br>
	<div id="DemoDiv">demo</div>
</body>
</html>