package com.farmmate.weather.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmmate.weather.dto.response.CurrentWeatherInfoResponseDto;
import com.farmmate.weather.dto.response.ShortTermWeatherInfoResponseDto;
import com.farmmate.weather.service.WeatherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
@Tag(name = "날씨", description = "날씨 정보 관련 API")
public class WeatherController {
	private final WeatherService weatherService;

	@Operation(summary = "현재 날씨 조회", description = "사용자가 선택한 지역의 현재 날씨 정보를 조회합니다.")
	@GetMapping("/current")
	public CurrentWeatherInfoResponseDto getTodayWeatherInfo(@NotNull String address) {
		return weatherService.getCurrentWeatherInfo(address);
	}

	@Operation(summary = "단기 예보(3일) 조회", description = "사용자가 선택한 지역의 단기 예보(3일) 정보를 조회합니다.")
	@GetMapping("/short-term")
	public List<ShortTermWeatherInfoResponseDto> getShortTermWeatherInfo(@NotNull String address) {
		return weatherService.getShortTermWeatherInfo(address);
	}
}
