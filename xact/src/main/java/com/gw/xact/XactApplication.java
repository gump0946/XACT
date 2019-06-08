package com.gw.xact;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gw.xact.aspect.UserServiceAspect;
import com.gw.xact.common.interceptor.XactHandlerInterceptor;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan(basePackages = "com.gw.xact", annotationClass = Repository.class)
public class XactApplication implements WebMvcConfigurer {

	@Bean(name = "UserServiceAspect")
	public UserServiceAspect initUserServiceAspect() {
		return new UserServiceAspect();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry){
		InterceptorRegistration ir = registry.addInterceptor(new XactHandlerInterceptor());
		ir.addPathPatterns("/**");
	}

	public static void main(String[] args) {
		SpringApplication.run(XactApplication.class, args);
	}

}
