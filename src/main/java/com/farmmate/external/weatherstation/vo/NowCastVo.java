package com.farmmate.external.weatherstation.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.farmmate.external.weatherstation.dto.response.WeatherStationNowCastResponse;
import com.farmmate.external.weatherstation.type.PrecipitationType;
import com.farmmate.external.weatherstation.type.SkyCondition;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.NonNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public record NowCastVo(
	PrecipitationType precipitationType,    // PTY - 강수형태 코드
	int humidity,    // REH - 습도
	@NonNull SkyCondition skyCondition,    // SKY - 하늘상태 코드
	double temperature, // T1H - 기온(섭씨)
	int precipitation // RN1 - 1시간 강수량(mm)
) {
	public static NowCastVo from(WeatherStationNowCastResponse response) {
		List<WeatherStationNowCastResponse.Response.Body.Items.Item> items = response.response()
			.body()
			.items()
			.itemElements();
		Map<String, String> dataMap = new HashMap<>();

		for (WeatherStationNowCastResponse.Response.Body.Items.Item item : items) {
			dataMap.put(item.category(), item.obsrValue());
		}

		return new NowCastVo(
			PrecipitationType.fromCode(dataMap.get("PTY")),
			Integer.parseInt(dataMap.getOrDefault("REH", "0")),
			SkyCondition.fromCode(dataMap.getOrDefault("SKY", "1")),
			Double.parseDouble(dataMap.getOrDefault("T1H", "0.0")),
			Integer.parseInt(dataMap.getOrDefault("RN1", "0"))
		);
	}

	@Override
	public String toString() {
		return "CurrentWeatherInfoVO{" +
			"precipitationType='" + precipitationType.getDescription() + '\'' +
			", humidity='" + humidity + '\'' +
			", sky='" + skyCondition.getDescription() + '\'' +
			", temperature='" + temperature + '\'' +
			", precipitation='" + precipitation + '\'' +
			'}';
	}
}


