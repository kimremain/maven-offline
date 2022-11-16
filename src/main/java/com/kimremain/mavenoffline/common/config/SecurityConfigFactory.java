package com.kimremain.mavenoffline.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.kimremain.mavenoffline.common.handler.CustomAccessDeniedHandler;
import com.kimremain.mavenoffline.common.handler.CustomAuthenticationFailureHandler;
import com.kimremain.mavenoffline.common.handler.CustomAuthenticationSuccessHandler;
import com.kimremain.mavenoffline.common.provider.CustomAuthenticationProvider;
import com.kimremain.mavenoffline.common.util.LogUtil;

@Configuration
public class SecurityConfigFactory extends WebSecurityConfigurerAdapter  {

	
	public SecurityConfigFactory() {
		LogUtil.printConsole("Doing SecurityConfigFactory ...");	
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.httpBasic().disable()
		.csrf().disable()
		.cors().disable()
		.authorizeRequests()
		.anyRequest().access("@SecurityFilter.check(authentication,request)");
		http.formLogin()
        .loginPage("/sys/login") //로그인 페이지
        .usernameParameter("id") //로그인 페이지의 id param
        .passwordParameter("password") //로그인 페이지의 패스워드 param
        .successHandler(customAuthenticationSuccessHandler())	//커스텀 로그인성공 핸들러
        .failureHandler(customAuthenticationFailureHandler())	//커스텀 로그인실패 핸들러
        .permitAll(); //로그인 페이지의 접근 권한은 모두 허용한다.
		http.authenticationProvider(customAuthenticationProvider()); //커스텀 인증 프로바이더
		http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
	}
	
	/*
	 * Security 커뮤니티 권장 BCryptPasswordEncoder 사용
	 */	
	@Bean
    public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * ACL 처리
	 */	
	@Bean(name="SecurityFilter")
	public SecurityFilter customSecurityFilter() {
		return new SecurityFilter();
	}
	
	/*
	 * 인증 검증 처리
	 * ID/패스워드 인증을 담당한다.
	 */
	@Bean
	public AuthenticationProvider customAuthenticationProvider() { 
		return new CustomAuthenticationProvider(); 
	}	
	
	/*
	 * 로그인 성공 처리
	 * 정상적으로 인증이 성공할 경우 후처리를 담당한다.
	 */
	@Bean 
	public AuthenticationSuccessHandler customAuthenticationSuccessHandler() { 
		return new CustomAuthenticationSuccessHandler(); 
	}
	
	/*
	 * 로그인 실패 처리
	 * 계정 불일치등의 이유로 인증에 실패하는 요청에 대한 처리를 담당한다.
	 */
	@Bean 
	public AuthenticationFailureHandler customAuthenticationFailureHandler() { 
		return new CustomAuthenticationFailureHandler(); 
	}
	
	/*
	 * 접근 거부 처리
	 * 사이트제어 또는 권한부족으로 액세스가 거부되는 요청에 대한 처리를 담당한다.
	 */
	@Bean
	public AccessDeniedHandler customAccessDeniedHandler() {
		return new CustomAccessDeniedHandler(); 
	}
}
