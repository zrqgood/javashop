package com.enation.app.base.core.service.solution;

import java.util.ArrayList;
import java.util.List;

import com.enation.framework.context.webcontext.ThreadContextHolder;

public class InstallUtil {
	public static void putMessaage(String msg){
		if(ThreadContextHolder.getSessionContext()!=null){
		List msgList = (List)ThreadContextHolder.getSessionContext().getAttribute("installMsg");
		if(msgList==null){
			msgList = new ArrayList();
		}
		msgList.add(msg);
		ThreadContextHolder.getSessionContext().setAttribute("installMsg", msgList);
		}
	}
}
