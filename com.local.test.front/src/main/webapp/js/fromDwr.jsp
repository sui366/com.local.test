<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <base href="<%=basePath%>">  
    <title>My JSP 'MyJsp.jsp' starting page</title>  
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">   
    <meta http-equiv="description" content="This is my page">  
    <script type='text/javascript' src='http://localhost:8090/dwr/engine.js'></script>  
    <script type='text/javascript' src='http://localhost:8090/dwr/util.js'></script>  
    <script type='text/javascript' src='http://localhost:8090/dwr/interface/TestPush.js'></script>  
      
    <script type="text/javascript">  
      
    function testPush() {  
        var msg = document.getElementById("msgId").value;  
        TestPush.sendMessageAuto(msg,"www.yoodb.com");  
          
    }  
    </script>  
  </head>  
    
  <body>  
    id值: <input type="text" name="msgId" id="msgId" /> <br />  
    <input type="button" value="Send" onclick="testPush()"  />  
  </body>  
</html>