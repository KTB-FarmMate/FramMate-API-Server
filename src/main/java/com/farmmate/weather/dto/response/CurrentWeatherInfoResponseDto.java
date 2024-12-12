package com.farmmate.weather.dto.response;

import com.farmmate.external.weatherstation.vo.NowCastVo;

public record CurrentWeatherInfoResponseDto(String precipitationType,    // PTY - 강수형태 코드
											int humidity,
											String skyCondition,
											double temperature, // T1H - 기온(섭씨)
											int precipitation   // RN1 - 1시간 강수량
) {

	public static CurrentWeatherInfoResponseDto from(NowCastVo vo) {
		return new CurrentWeatherInfoResponseDto(vo.precipitationType().getDescription(), vo.humidity(),
			vo.skyCondition().getDescription(),
			vo.temperature(), vo.precipitation());
	}
}
