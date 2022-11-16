package com.kimremain.mavenoffline.common.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{


    @Autowired
    PasswordEncoder passwordEncoder;	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.debug("Call ConsoleAuthenticationProvider authenticate...");
		String id = (String)authentication.getPrincipal();    
        String password = (String)authentication.getCredentials();		
        List<GrantedAuthority> tokeRoles = new ArrayList<GrantedAuthority>();
		
//        throw new BadCredentialsException("Miss Match User");
//        throw new BadCredentialsException("Miss Match Password");

//		for(AuthRoleVO dummy :  user.getHasAuthRole()) {
//			GrantedAuthority authority = new SimpleGrantedAuthority(dummy.getNmAuth());
//			tokeRoles.add(authority);
//		}
        return new UsernamePasswordAuthenticationToken(id, password, tokeRoles);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		log.debug("Call ConsoleAuthenticationProvider supports...");
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
