package com.fdmgroup.GameBlog.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
		
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path pictureUploadDir = Paths.get("./blogPost-pictures");
		String pictureUploadPath = pictureUploadDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/blogPost-pictures/**").addResourceLocations("file:/" + pictureUploadPath + "/");
	}
	
	// Multiple Languages
	@Bean(name = "localeResolver")
	public LocaleResolver getLocaleResolver() {
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		
		cookieLocaleResolver.setCookieMaxAge(60*60);
		return cookieLocaleResolver;
	}
	
	@Bean(name = "messageSource")
	public MessageSource getMessageResource() {
		ReloadableResourceBundleMessageSource messageResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		
		messageResourceBundleMessageSource.setBasename("classpath:i18n/labels");
		messageResourceBundleMessageSource.setDefaultEncoding("UTF-8");
		
		return messageResourceBundleMessageSource;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		
		localeChangeInterceptor.setParamName("lang");
		
		registry.addInterceptor(localeChangeInterceptor).addPathPatterns("/*");
	}
	
	

}
