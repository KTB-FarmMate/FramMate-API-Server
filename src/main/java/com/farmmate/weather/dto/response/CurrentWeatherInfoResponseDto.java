package com.farmmate.weather.dto.response;

import com.farmmate.external.weatherstation.vo.NowCastVo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(title = "현재 날씨 정보 응답")
@Builder
public record CurrentWeatherInfoResponseDto(
	@Schema(description = "강수형태 코드(없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4))", example = "0") int precipitationTypeCode,
	// PTY - 강수형태 코드
	@Schema(description = "습도", defaultValue = "50") int humidity,                   // REH - 습도
	@Schema(description = "하늘상태(맑음(1), 구름조금(2), 구름많음(3), 흐림(4))", example = "1") int skyConditionCode,
	// SKY - 하늘 상태 코드
	@Schema(description = "기온", defaultValue = "20") double temperature,             // T1H - 기온(섭씨)
	@Schema(description = "1시간 강수량", defaultValue = "0") int precipitation   // RN1 - 1시간 강수량
) {

	public static CurrentWeatherInfoResponseDto from(NowCastVo vo) {
		return CurrentWeatherInfoResponseDto.builder()
			.precipitationTypeCode(Integer.parseInt(vo.precipitationType().getCode()))
			.humidity(vo.humidity())
			.skyConditionCode(Integer.parseInt(vo.skyCondition().getCode()))
			.temperature(vo.temperature())
			.precipitation(vo.precipitation())
			.build();
	}
}
