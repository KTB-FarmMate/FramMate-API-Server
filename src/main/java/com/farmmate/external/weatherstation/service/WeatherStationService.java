package com.farmmate.external.weatherstation.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.farmmate.external.weatherstation.dto.response.NowCastResponse;
import com.farmmate.external.weatherstation.dto.response.ShortTermForecastResponse;
import com.farmmate.external.weatherstation.dto.response.ShortTermForecastResponse.Response.Body.Items.Item;
import com.farmmate.external.weatherstation.vo.DayForecastVO;
import com.farmmate.external.weatherstation.vo.NowCastVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WeatherStationService {
	private static final String HOST = "apis.data.go.kr";    // 단기예보 조회
	private static final String CURRENT_WEATHER_INFO_PATH = "1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";    // 초단기실황 조회
	private static final String SHORT_TERM_FORECAST_PATH = "1360000/VilageFcstInfoService_2.0/getVilageFcst";    // 초단기예보 조회
	private final WebClient webClient = WebClient.builder()
		.baseUrl("https://" + HOST)
		.build();

	@Value("${weather-station.apikey}")
	private String API_KEY;

	private static List<LocalDateTime> extendBaseTimes(LocalDateTime baseTime) {
		List<LocalDateTime> extendedTimes = new ArrayList<>();
		List<LocalTime> times = List.of(LocalTime.of(2, 0), LocalTime.of(5, 0), LocalTime.of(8, 0), LocalTime.of(11, 0),
			LocalTime.of(14, 0), LocalTime.of(17, 0), LocalTime.of(20, 0), LocalTime.of(23, 0));

		for (LocalTime time : times) {
			// 현재 날짜
			extendedTimes.add(baseTime.with(time));
			// 이전 날짜
			extendedTimes.add(baseTime.minusDays(1).with(time));
		}

		return extendedTimes;
	}

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

		assert response != null;

		return NowCastVo.from(response);
	}

	// 단기예보 조회 (3일간 날씨 조회)
	public List<DayForecastVO> getShortTermForeCast(LocalDateTime dateTime, int nx, int ny) {
		LocalDateTime baseTime = extendBaseTimes(dateTime).stream()
			.filter(time -> time.isBefore(dateTime))
			.max(LocalDateTime::compareTo)
			.orElse(dateTime);    // 05시에 발표하여 3시간 단위로 발표되므로, 해당 주기의 시간을 찾아줘야함
		String requestBaseDate = baseTime.toLocalDate().toString().replaceAll("-", "");
		String requestBaseTime = baseTime.toLocalTime().toString().replaceAll(":", "").substring(0, 2) + "00";

		// 현재 시간에서 가장 가까운 이전 시간으로 조회

		ShortTermForecastResponse response = webClient.get()
			.uri(uriBuilder -> uriBuilder
				.path(SHORT_TERM_FORECAST_PATH)
				.queryParam("pageNo", 1)
				.queryParam("numOfRows", 1000)
				.queryParam("base_date", requestBaseDate)
				.queryParam("base_time", requestBaseTime)
				.queryParam("dataType", "JSON")
				.queryParam("nx", nx)
				.queryParam("ny", ny)
				.queryParam("ServiceKey", API_KEY)
				.build())
			.retrieve()
			.bodyToMono(ShortTermForecastResponse.class)
			.doOnError(e -> {
				log.error(e.getMessage());
				throw new RuntimeException("Failed to get short term weather info", e);
			})
			.block();

		assert response != null;

		List<Item> items = response.response()
			.body()
			.items()
			.itemElements();

		// 각 일별로 묶는다
		Map<LocalDate, List<Item>> groupedItems = items.stream()
			.filter(item -> NumberUtils.isCreatable(item.fcstValue()))    // 예보 값이 한글인 경우도 있으므로 필터링
			.collect(Collectors.groupingBy(
				item -> LocalDate.parse(item.fcstDate(), DateTimeFormatter.ofPattern("yyyyMMdd"))));

		// 각 일별에 대한 정보로 가공한다.

		return groupedItems.entrySet().stream()
			.map(entry -> DayForecastVO.from(entry.getKey(), entry.getValue()))
			.toList();
	}
}
