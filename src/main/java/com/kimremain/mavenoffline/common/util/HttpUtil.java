package com.kimremain.mavenoffline.common.util;

import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kimremain.mavenoffline.common.constant.URIConstant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpUtil {

	public static String getRequestUrlNoQueryString(HttpServletRequest request) {
		String url = getHostUrl(request);
		if(!request.getRequestURI().equals(URIConstant.URI_MAIN)) {
			url = url + request.getRequestURI();
		}
		return url;		
	}
	
	public static String getRequestUrl(HttpServletRequest request) {
		String url = getHostUrl(request);
		if(!request.getRequestURI().equals(URIConstant.URI_MAIN)) {
			url = url + request.getRequestURI();
		}
		if(request.getQueryString() != null && request.getQueryString().getBytes().length > 0) {
			url = url + "?" + request.getQueryString();
		}
		return url;		
	}
	
	public static String getHostUrl(HttpServletRequest request) {
//		log.debug("host:" + request.getRemoteHost());
//		log.debug("getHostUrl A:" + request.getRequestURL().toString());
//		log.debug("request.getRequestURI():" + request.getRequestURI());
//		log.debug("request.getQueryString():" + request.getQueryString());
		String url = request.getRequestURL().toString(); 
		if(!request.getRequestURI().equals(URIConstant.URI_MAIN)) {
			url = request.getRequestURL().toString().replace(request.getRequestURI(), "");
		}
		if (url.endsWith(URIConstant.URI_MAIN)) {
			url = url.substring(0, url.length() - 1);
		}
//		log.debug("getHostUrl B:" + url);
		return url;
	}
	
	public static String getClientIP() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null) {
			ip = req.getRemoteAddr();
		}
		if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
			ip = "127.0.0.1";
		}
		return ip;
	}
	
	public static String getSessionId(HttpServletRequest httpServletRequest ) {
		HttpSession session = httpServletRequest.getSession();
		return session.getId();
	}
	
	public static String getLocaleString() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		Locale locale = RequestContextUtils.getLocale(req);
		return locale.getLanguage();
	}

	public static Locale getLocale() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		Locale locale = RequestContextUtils.getLocale(req);
		return locale;
	}
	
	public static String getRefererURL(HttpServletRequest request, HttpServletResponse response) {
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest == null) {
            return request.getSession().getServletContext().getContextPath();
        }
        return savedRequest.getRedirectUrl();		
	}
	
	public static String getRequestURI(HttpServletRequest request) {
		return request.getRequestURI();
	}

	public static String getRequestURI(ServletRequest request) {
		return getRequestURI((HttpServletRequest) request);
	}
	
	public static String getRequestURI() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		return getRequestURI((HttpServletRequest) request);
	}	

	public static String getRequestMethod(HttpServletRequest request) {
		return request.getMethod();
	}

	public static String getRequestMethod(ServletRequest request) {
		return getRequestMethod((HttpServletRequest) request);
	}

	public static String getRequestQueryString(HttpServletRequest request) {
		String queryString = request.getQueryString();
		return queryString;
	}

	public static String getRequestQueryString(ServletRequest request) {
		return getRequestQueryString((HttpServletRequest) request);
	}

	public static void printRequestParams(HttpServletRequest request) {
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			log.debug("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
		}

		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String paramName = params.nextElement();
			log.debug("Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
		}
	}
	
}
