package com.farmmate.external.weatherstation.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.farmmate.external.weatherstation.dto.response.NowCastResponse;
import com.farmmate.external.weatherstation.type.PrecipitationType;
import com.farmmate.external.weatherstation.type.SkyCondition;

import lombok.NonNull;

public record NowCastVo(
	PrecipitationType precipitationType,    // PTY - 강수형태 코드
	int humidity,    // REH - 습도
	@NonNull SkyCondition skyCondition,    // SKY - 하늘상태 코드
	double temperature, // T1H - 기온(섭씨)
	int precipitation // RN1 - 1시간 강수량(mm)
) {
	public static NowCastVo from(NowCastResponse response) {
		List<NowCastResponse.Response.Body.Items.Item> items = response.response()
			.body()
			.items()
			.itemElements();
		Map<String, String> dataMap = new HashMap<>();

		for (NowCastResponse.Response.Body.Items.Item item : items) {
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


