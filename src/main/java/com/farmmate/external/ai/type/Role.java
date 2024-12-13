package com.farmmate.external.ai.type;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
	USER("user"),
	ASSISTANT("assistant");

	private static final Map<String, Role> map = Arrays.stream(values())
		.collect(Collectors.toMap(Role::getDescription, Function.identity()));
	private final String description;

	public static Role fromDescription(String description) {
		return map.get(description);
	}
}
