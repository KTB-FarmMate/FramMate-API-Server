package com.farmmate.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
	private static final String[] ORIGINS = {
		"http://localhost:3000",
		"https://farmmate.net"
	};

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // 모든 경로에 대해 CORS 설정
					.allowedOrigins(ORIGINS) // 허용할 출처
					.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
					.allowedHeaders("*") // 모든 헤더 허용
					.allowCredentials(true); // 쿠키 허용 여부
			}
		};
	}
}
