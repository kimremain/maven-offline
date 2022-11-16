package com.kimremain.mavenoffline.common.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityFilter {

	public boolean check(Authentication auth, HttpServletRequest request) throws Exception {
		log.debug("call SecurrityFilter check...");
		boolean result = true;
		
		return result;
	}
}
