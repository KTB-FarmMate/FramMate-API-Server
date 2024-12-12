package com.farmmate.weather.dto.response;

import com.farmmate.external.weatherstation.vo.NowCastVo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "현재 날씨 정보 응답")
public record CurrentWeatherInfoResponseDto(
	@Schema(description = "강수형태", defaultValue = "없음") String precipitationType,    // PTY - 강수형태 코드
	@Schema(description = "습도", defaultValue = "50") int humidity,                   // REH - 습도
	@Schema(description = "하늘 상태", defaultValue = "맑음") String skyCondition,       // SKY - 하늘 상태 코드
	@Schema(description = "기온", defaultValue = "20") double temperature,             // T1H - 기온(섭씨)
	@Schema(description = "1시간 강수량", defaultValue = "0") int precipitation   // RN1 - 1시간 강수량
) {

	public static CurrentWeatherInfoResponseDto from(NowCastVo vo) {
		return new CurrentWeatherInfoResponseDto(vo.precipitationType().getDescription(), vo.humidity(),
			vo.skyCondition().getDescription(),
			vo.temperature(), vo.precipitation());
	}
}
