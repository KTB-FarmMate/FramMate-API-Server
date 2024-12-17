package com.farmmate.external.weatherstation.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.farmmate.external.weatherstation.vo.DayForecastVO;
import com.farmmate.external.weatherstation.vo.NowCastVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherStationService {
	private static final String HOST = "apis.data.go.kr";    // 기상청 API 호스트

	private final ShortTermWeatherService shortTermWeatherService;

	private final WebClient webClient = WebClient.builder()
		.baseUrl("https://" + HOST)    // FIXME: 추후 bean으로 사용할 수 있도록하기
		.build();

	@Cacheable(value = "currentWeatherInfo", key = "#nx + #ny")
	public NowCastVo getCurrentWeatherInfo(LocalDateTime localDateTime, int nx, int ny) {
		log.info("Cache Miss: 현재 날씨 조회, nx: {}, ny: {}", nx, ny);
		return shortTermWeatherService.getCurrentWeatherInfo(webClient, localDateTime, nx, ny);
	}

	@Cacheable(value = "shortTermForeCast", key = "#nx + #ny")
	public List<DayForecastVO> getShortTermForeCast(LocalDateTime localDateTime, int nx, int ny) {
		log.info("Cache Miss: 단기 예보(3일) 조회, nx: {}, ny: {}", nx, ny);
		return shortTermWeatherService.getShortTermForeCast(webClient, localDateTime, nx, ny);
	}
}
