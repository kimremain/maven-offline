package com.kimremain.mavenoffline.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.kimremain.mavenoffline.common.util.LogUtil;

@ComponentScan({ "com.kimremain.mavenoffline" })
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
public class WebConfigFactory implements WebMvcConfigurer{
//	@Value("${web.view.prefix}")
//	private String prefix;
//	@Value("${web.view.suffix}")
//	private String suffix;
	
	public static final String DEFAULT_CONTENT_TYPE = "text/html;charset=UTF-8";
	public static final String JSON_CONTENT_TYPE = "application/json;charset=UTF-8";
	public static final String TEMPLATE_LOCATION = "static/template";
	private static final String[] RESOURCE_LOCATIONS = {"classpath:/static/"};

	
	public WebConfigFactory() {
		LogUtil.printConsole("Doing WebConfigFactory ...");	
	}
	
	/*
	 * TODO : PathVariable 이메일 데이터 잘림 현상 방지하기 위해 추가하였으나 Spring 5 보안성 강화를 위해 Dprecated된 상태 
	 * 추후 다른 솔루션 검토할것
	 */
    @SuppressWarnings("deprecation")
	@Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    @SuppressWarnings("deprecation")
	@Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        matcher.setUseSuffixPatternMatch(false);
    }    
    
	@Bean 
	public RequestContextListener requestContextListener(){
	    return new RequestContextListener();
	} 
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
        .addResourceHandler("/**")
        .addResourceLocations(RESOURCE_LOCATIONS)
        .setCachePeriod(1)
        .resourceChain(true)
        .addResolver(new PathResourceResolver());
    }
    
//    @Bean
//    @Description("Thymeleaf template resolver serving HTML 5")
//    public ClassLoaderTemplateResolver templateResolver() {
//    	ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setPrefix(TEMPLATE_LOCATION);
//        templateResolver.setCacheable(false);
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("HTML5");
//        //templateResolver.setCharacterEncoding("UTF-8");
//        templateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
//
//        return templateResolver;
//    }
//
//    @Bean
//    @Description("Thymeleaf template engine with Spring integration")
//    public SpringTemplateEngine templateEngine() {
//    	SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//
//        return templateEngine;
//    }
//
//    @Bean
//    @Description("Thymeleaf view resolver")
//    public ViewResolver viewResolver() {
//    	ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine());
//        viewResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
//
//        return viewResolver;
//    }
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("/index");
//    }

    


	
}
