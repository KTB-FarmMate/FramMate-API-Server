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
public enum SkyCondition {
	CLEAR("1", "맑음"),
	PARTLY_CLOUDY("2", "구름 조금"),
	MOSTLY_CLOUDY("3", "구름 많음"),
	CLOUDY("4", "흐림");

	private static final Map<String, SkyCondition> skyConditionMap = Collections.unmodifiableMap(
		Stream.of(values())
			.collect(Collectors.toMap(SkyCondition::getCode, Function.identity())));
	private final String code;
	private final String description;

	public static SkyCondition fromCode(String code) {
		return skyConditionMap.get(code);
	}

}
