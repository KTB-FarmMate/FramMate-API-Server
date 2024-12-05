package com.farmmate.external.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.farmmate.external.dto.response.NowCastResponse;
import com.farmmate.external.dto.response.ShortTermForecastResponse;
import com.farmmate.external.dto.response.ShortTermForecastResponse.Response.Body.Items.Item;
import com.farmmate.external.vo.NowCastVo;
import com.farmmate.external.vo.ShortTermForeCastVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WeatherStationService {
	private static final String HOST = "apis.data.go.kr";    // 단기예보 조회
	private static final String CURRENT_WEATHER_INFO_PATH = "1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";    // 초단기실황 조회
	private final WebClient webClient = WebClient.builder()
		.baseUrl("https://" + HOST)
		.build();

	@Value("${weather-station.apikey}")
	private String API_KEY;

	// 초단기실황 조회
	public NowCastVo getCurrentWeatherInfo(LocalDateTime dateTime, int nx, int ny) {
		String requestBaseDate = dateTime.toLocalDate().toString().replaceAll("-", "");
		String requestBaseTime = dateTime.toLocalTime().toString().replaceAll(":", "").substring(0, 2) + "00";

		NowCastResponse response = webClient.get()
			.uri(uriBuilder -> uriBuilder
				.path(CURRENT_WEATHER_INFO_PATH)
				.queryParam("pageNo", 1)
				.queryParam("numOfRows", 10)
				.queryParam("base_date", requestBaseDate)
				.queryParam("base_time", requestBaseTime)
				.queryParam("dataType", "JSON")
				.queryParam("nx", nx)
				.queryParam("ny", ny)
				.queryParam("ServiceKey", API_KEY)
				.build())
			.retrieve()
			.bodyToMono(NowCastResponse.class)
			.doOnError(e -> {
				log.error(e.getMessage());
				throw new RuntimeException("Failed to get short term weather info", e);
			})
			.block();

		NowCastVo vo = NowCastVo.from(response);

		return vo;
	}
}
