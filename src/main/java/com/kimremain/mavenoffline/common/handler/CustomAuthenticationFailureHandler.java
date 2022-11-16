package com.kimremain.mavenoffline.common.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, 
    									final HttpServletResponse response, 
    									final AuthenticationException exception) throws IOException, ServletException {
    	log.debug("do ConsoleAuthenticationFailureHandler onAuthenticationFailure...");
    }
}
