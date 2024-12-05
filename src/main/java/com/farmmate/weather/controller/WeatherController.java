package com.farmmate.weather.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmmate.weather.dto.response.CurrentWeatherInfoResponseDto;
import com.farmmate.weather.service.ShortTermWeatherInfoResponseDto;
import com.farmmate.weather.service.WeatherService;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherController {
	private final WeatherService weatherService;

	@RequestMapping("/current")
	public CurrentWeatherInfoResponseDto getTodayWeatherInfo(@NotNull String address) {
		return weatherService.getCurrentWeatherInfo(address);
	}

	@RequestMapping("/short-term")
	public List<ShortTermWeatherInfoResponseDto> getShortTermWeatherInfo(@NotNull String address) {
		return weatherService.getShortTermWeatherInfo(address);
	}
}
