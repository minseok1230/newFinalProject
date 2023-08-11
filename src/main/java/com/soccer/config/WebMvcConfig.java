package com.soccer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.soccer.common.FileManagerService;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	
	/* 주소에 처음 들어오자마자 설정해줌 */
	
//	@Autowired
//	private PermissionInterceptor interceptor;
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry
//		.addInterceptor(interceptor)
//		.addPathPatterns("/**")
//		.excludePathPatterns("/static/**", "/error", "/favicon.ico", "/user/sign_out")
//		;
//	}
	
	// 웹 이미지 path와 서버에 업로드 된 이미지와 매핑 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/images/**") // 웹 이미지 path http://localhost/images/aaaa_5432156/sun.png
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH); // 실제 파일 위치 
	}
}
