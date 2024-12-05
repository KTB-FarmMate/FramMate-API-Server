package com.farmmate.external.type;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CurrentPrecipitationType {
	NO_PRECIPITATION("0", "없음"),
	RAIN("1", "비"),
	RAIN_SNOW("2", "비/눈"),
	SNOW("3", "눈"),
	RAINDROP("5", "빗방울"),
	RAINDROP_SNOWFLAKE("6", "빗방울/눈날림"),
	SNOWFLAKE("7", "눈날림");

	private static final Map<String, CurrentPrecipitationType> currentPrecipitationMap = Collections.unmodifiableMap(
		Stream.of(values())
			.collect(Collectors.toMap(CurrentPrecipitationType::getCode, Function.identity())));
	private final String code;
	private final String description;

	public static CurrentPrecipitationType fromCode(String code) {
		return currentPrecipitationMap.get(code);
	}
}
