package com.kimremain.mavenoffline.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
		.authorizeRequests().anyRequest().permitAll();
	}
	
	/*
	 * Security 커뮤니티 권장 BCryptPasswordEncoder 사용
	 */	
	@Bean
    public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
