package com.enation.framework.context.webcontext;

import java.util.Set;

import javax.servlet.http.HttpSession;

public interface WebSessionContext<T> {

	public static String sessionAttributeKey = "EOPSessionKey";

	public abstract HttpSession getSession();

	public abstract void setSession(HttpSession session);

	public abstract void invalidateSession();

	public abstract void setAttribute(String name, T value);

	public abstract T getAttribute(String name);

	public abstract Set<T> getAttributeNames();

	public abstract void removeAttribute(String name);
	
	public abstract void destory();

}