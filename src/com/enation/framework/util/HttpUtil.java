package com.enation.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HTTP相关的操作
 * 
 * @author Dawei
 * 
 */

public class HttpUtil {

	/**
	 * 设置cookie
	 * 
	 * @param response
	 *            应答
	 * @param cookieName
	 *            cookie名
	 * @param cookieValue
	 *            cookie值
	 * @param time
	 *            cookie生存时间
	 */
	public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue, int time) {
//		System.out.println("write :" + cookieName + "  " + cookieValue);
		try {
			if (cookieValue != null)
				cookieValue = URLEncoder.encode(cookieValue, "UTF-8");

		} catch (Exception ex) {
			ex.printStackTrace();
		//	System.out.println(ex);
		}

		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(time);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	public static void addCookie(HttpServletResponse response, String domain, String path, String cookieName, String cookieValue, int time) {
		try {
			cookieValue = URLEncoder.encode(cookieValue, "UTF-8");
		} catch (Exception ex) {
		}
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(time);
		cookie.setDomain(domain);
		cookie.setPath(path);
		response.addCookie(cookie);
		// System.out.println("write " + cookieName);
	}

	public static void addCookie1(HttpServletResponse response, String cookieName, String cookieValue, int time) {

		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(time);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	public static String getCookieValue(HttpServletRequest request, String cookieName, String domain, String path) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (domain.equals(cookies[i].getDomain()) && path.equals(cookies[i].getPath()) && cookieName.equals(cookies[i].getName())) {
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}

	/**
	 * 根据cookie名称取得cookie的值
	 * 
	 * @param HttpServletRequest
	 *            request request对象
	 * @param name
	 *            cookie名称
	 * @return string cookie的值 当取不到cookie的值时返回null
	 */
	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {

				if (cookieName.equals(cookies[i].getName())) {
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		String value = "%40g.139-341-na-1%2C183-493-na-1%3B";
		try {
			value = URLDecoder.decode(value, "UTF-8");
			System.out.println(value);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
