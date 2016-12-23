package com.local.controller.js;

import javax.servlet.ServletException;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContextFactory;

public class MessagePush {
	public void onPageLoad(String userId) {  
 	   ScriptSession scriptSession = WebContextFactory.get().getScriptSession();  
	    scriptSession.setAttribute(userId, userId);  
	     DwrScriptSessionManagerUtil dwrScriptSessionManagerUtil = new DwrScriptSessionManagerUtil();  
	      try {  
	             dwrScriptSessionManagerUtil.init();  
	             System.out.println("cacaca");     
	      } catch (ServletException e) {  
	             e.printStackTrace();  
	       }  
	}  
}
