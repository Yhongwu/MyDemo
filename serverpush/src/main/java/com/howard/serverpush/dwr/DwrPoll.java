package com.howard.serverpush.dwr;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;

public class DwrPoll {
	@SuppressWarnings("deprecation")
	public void sendMsg(String msg) {
		WebContext webContext = new WebContextFactory().get();
		//HttpSession httpSession = webContext.getSession();
	    //ScriptSession scriptSession = event.getSession();
		HttpServletRequest request = webContext.getHttpServletRequest();
		Collection<ScriptSession> scriptSession = webContext.getScriptSessionsByPage(request.getContextPath()+"/dwrpollshow.jsp");
		Util util = new Util(scriptSession);  
		ScriptBuffer sb = new ScriptBuffer();  
        sb.appendScript("show(");  
        sb.appendData(msg);  
        sb.appendScript(")");  
        //推送  
        util.addScript(sb);  
        
        
	}
}
