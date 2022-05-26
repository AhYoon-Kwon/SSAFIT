package com.ssafy.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.web.interceptor.JWTInterceptor;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	
	@Autowired
	private JWTInterceptor jwtInterceptor;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
		.allowedMethods("GET","POST","PUT","DELETE")
		.maxAge(6000);
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
	}
	
	//로그인 안해도 접근 가능한 url 추가함
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/video/all")
		.excludePathPatterns("/video/part")
		.excludePathPatterns("/video/recommended")
		.excludePathPatterns("/user/change-pw/**")
		.excludePathPatterns("/user/join/**")
		.excludePathPatterns("/user/login")
		.excludePathPatterns("/user/find-id");
	}
}
