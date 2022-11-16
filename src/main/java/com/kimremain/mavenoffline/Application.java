package com.kimremain.mavenoffline;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.kimremain.mavenoffline.common.util.LogUtil;

@EnableScheduling
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
	}
	
	@Override 
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class); 
	}

}
