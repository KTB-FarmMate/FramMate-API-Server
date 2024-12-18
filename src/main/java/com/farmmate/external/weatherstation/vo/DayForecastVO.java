package com.farmmate.external.weatherstation.vo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.farmmate.external.weatherstation.dto.response.WeatherStationShortTermForecastResponse.Response.Body.Items.Item;
import com.farmmate.external.weatherstation.type.PrecipitationType;
import com.farmmate.external.weatherstation.type.SkyCondition;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public record DayForecastVO(
	LocalDate forecastDate, // 예보 날짜
	Double maxTemperature, // TMX - 일 최고기온(℃)
	Double minTemperature, // TMN - 일 최저기온(℃)
	List<HourForecastInfo> hourForecastInfos    // 해당 일의 시간별 정보
) {
	public static DayForecastVO from(LocalDate forecastDate, List<Item> items) {
		//log.info("forecastDate: {}", forecastDate);
		Double maxTemperature = null;
		Double minTemperature = null;

		for (Item item : items) {
			if (item.category().equals("TMX")) {
				maxTemperature = Double.parseDouble(item.fcstValue());
			}
			if (item.category().equals("TMN")) {
				minTemperature = Double.parseDouble(item.fcstValue());
			}
		}

		Map<LocalTime, List<Item>> hourForecastItems = items.stream()
			.collect(
				Collectors.groupingBy(item -> LocalTime.parse(item.fcstTime(), DateTimeFormatter.ofPattern("HHmm"))));

		List<HourForecastInfo> hourForecastInfos = hourForecastItems.entrySet().stream()
			.map(entry -> HourForecastInfo.from(entry.getKey(), entry.getValue()))
			.sorted(Comparator.comparing(HourForecastInfo::fcstTime))
			.toList();

		return new DayForecastVO(forecastDate, maxTemperature, minTemperature, hourForecastInfos);
	}

	@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
	public record HourForecastInfo(
		LocalTime fcstTime,
		int precipitationProbability,    // PCP - 강수확률(%)
		PrecipitationType precipitationType,    // PTY - 강수형태(없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4))
		int precipitationAmount,    // PCP - 1시간 강수량(mm)
		int humidity, // REH - 습도(%)
		int snowAmount, // SNO - 1시간 산적설(cm)
		SkyCondition skyCondition, // SKY - 하늘상태(맑음(1), 구름조금(2), 구름많음(3), 흐림(4))
		int temperature // TMP - 1시간 기온(℃)
	) {
		public static HourForecastInfo from(LocalTime fcstTime, List<Item> items) {

			// log.info("fcstTime: {}", fcstTime);
			Map<String, String> itemMap = items.stream()
				.collect(Collectors.toMap(Item::category, Item::fcstValue));

			return new HourForecastInfo(
				fcstTime,
				Integer.parseInt(itemMap.getOrDefault("POP", "0")),
				PrecipitationType.fromCode(itemMap.get("PTY")),
				Integer.parseInt(itemMap.getOrDefault("PCP", "0")),
				Integer.parseInt(itemMap.get("REH")),
				Integer.parseInt(itemMap.getOrDefault("SNO", "0")),
				SkyCondition.fromCode(itemMap.get("SKY")),
				Integer.parseInt(itemMap.get("TMP"))
			);
		}
	}
}
