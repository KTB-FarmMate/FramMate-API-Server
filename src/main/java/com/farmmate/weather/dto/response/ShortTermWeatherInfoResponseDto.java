package com.farmmate.weather.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.farmmate.external.weatherstation.vo.DayForecastVO;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "단기 예보 중 일간 정보 응답")
public record ShortTermWeatherInfoResponseDto(
	@Schema(description = "예보 날짜", example = "2021-01-01") LocalDate forecastDate,
	@Schema(description = "일 최고기온(섭씨)", example = "30") Double maxTemperature,
	@Schema(description = "일 최저기온(섭씨)", example = "20") Double minTemperature,
	@Schema(description = "시간별 정보")
	List<HourForecastInfo> hourForecastInfos
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

	@Schema(title = "시간별 예보 정보")
	public record HourForecastInfo(
		@Schema(description = "예보 시간", type = "string", format = "time", example = "12:00")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
		LocalTime forecastTime,
		@Schema(description = "강수확률(%)", example = "50")
		int precipitationProbability,
		@Schema(description = "강수형태 코드(없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4))", example = "0")
		int precipitationTypeCode,
		@Schema(description = "1시간 강수량(mm)", example = "0")
		int precipitationAmount,
		@Schema(description = "습도(%)", example = "50")
		int humidity,
		@Schema(description = "1시간 산적설(cm)", example = "0")
		int snowAmount,
		@Schema(description = "하늘상태(맑음(1), 구름조금(2), 구름많음(3), 흐림(4))", example = "1")
		int skyConditionCode,
		@Schema(description = "기온(℃)", example = "20")
		int temperature
	) {
		public static HourForecastInfo from(DayForecastVO.HourForecastInfo hourForecastInfo) {
			return new HourForecastInfo(
				hourForecastInfo.fcstTime(),
				hourForecastInfo.precipitationProbability(),
				Integer.parseInt(hourForecastInfo.precipitationType().getCode()),
				hourForecastInfo.precipitationAmount(),
				hourForecastInfo.humidity(),
				hourForecastInfo.snowAmount(),
				Integer.parseInt(hourForecastInfo.skyCondition().getCode()),
				hourForecastInfo.temperature()
			);
		}
	}
}
