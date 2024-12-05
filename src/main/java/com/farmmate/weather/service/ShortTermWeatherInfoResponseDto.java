package com.farmmate.weather.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.farmmate.external.type.PrecipitationType;
import com.farmmate.external.type.SkyCondition;
import com.farmmate.external.vo.DayForecastVO;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ShortTermWeatherInfoResponseDto(
	LocalDate forecastDate,
	Double maxTemperature, // TMX - 최고기온(섭씨)
	Double minTemperature, // TMN - 최저기온(섭씨)
	@JsonProperty("hour") List<HourForecastInfo>
		hourForecastInfos    // 해당 일의 시간별 정보

) {

	public static ShortTermWeatherInfoResponseDto from(DayForecastVO vo) {
		return new ShortTermWeatherInfoResponseDto(
			vo.forecastDate(),
			vo.maxTemperature(),
			vo.minTemperature(),
			vo.hourForecastInfos().stream()
				.map(HourForecastInfo::from)
				.toList()
		);
	}

	public record HourForecastInfo(
		LocalTime forecastTime,    // 예보 시간
		int precipitationProbability,    // PCP - 강수확률(%)
		PrecipitationType precipitationType,    // PTY - 강수형태(없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4))
		int precipitationAmount,    // PCP - 1시간 강수량(mm)
		int humidity, // REH - 습도(%)
		int snowAmount, // SNO - 1시간 산적설(cm)
		SkyCondition skyCondition, // SKY - 하늘상태(맑음(1), 구름조금(2), 구름많음(3), 흐림(4))
		int temperature // TMP - 1시간 기온(℃)
	) {
		public static HourForecastInfo from(DayForecastVO.HourForecastInfo hourForecastInfo) {

			return new HourForecastInfo(
				hourForecastInfo.fcstTime(),
				hourForecastInfo.precipitationProbability(),
				hourForecastInfo.precipitationType(),
				hourForecastInfo.precipitationAmount(),
				hourForecastInfo.humidity(),
				hourForecastInfo.snowAmount(),
				hourForecastInfo.skyCondition(),
				hourForecastInfo.temperature()
			);
		}
	}
}
