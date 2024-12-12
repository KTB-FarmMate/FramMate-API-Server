package com.farmmate.weather.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.farmmate.external.weatherstation.type.PrecipitationType;
import com.farmmate.external.weatherstation.type.SkyCondition;
import com.farmmate.external.weatherstation.vo.DayForecastVO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "단기 예보 중 일간 정보 응답")
public record ShortTermWeatherInfoResponseDto(
	@Schema(description = "예보 날짜", defaultValue = "2021-01-01") LocalDate forecastDate,
	@Schema(description = "일 최고기온(섭씨)", defaultValue = "30") Double maxTemperature, // TMX - 최고기온(섭씨)
	@Schema(description = "일 최저기온(섭씨)", defaultValue = "20") Double minTemperature, // TMN - 최저기온(섭씨)
	@Schema(description = "시간별 정보", defaultValue = "시간별 정보") List<HourForecastInfo> hourForecastInfos    // 해당 일의 시간별 정보

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

	@Schema(description = "시간별 정보")
	public record HourForecastInfo(
		@Schema(description = "예보 시간", defaultValue = "12:00")
		LocalTime forecastTime,    // 예보 시간
		@Schema(description = "강수확률(%)", defaultValue = "50")
		int precipitationProbability,    // PCP - 강수확률(%)
		@Schema(description = "강수형태(없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4))", defaultValue = "없음")
		PrecipitationType precipitationType,    // PTY - 강수형태(없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4))
		@Schema(description = "1시간 강수량(mm)", defaultValue = "0")
		int precipitationAmount,    // PCP - 1시간 강수량(mm)
		@Schema(description = "습도(%)", defaultValue = "50")
		int humidity, // REH - 습도(%)
		@Schema(description = "1시간 산적설(cm)", defaultValue = "0")
		int snowAmount, // SNO - 1시간 산적설(cm)
		@Schema(description = "하늘상태(맑음(1), 구름조금(2), 구름많음(3), 흐림(4))", defaultValue = "맑음")
		SkyCondition skyCondition, // SKY - 하늘상태(맑음(1), 구름조금(2), 구름많음(3), 흐림(4))
		@Schema(description = "기온(℃)", defaultValue = "20")
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
