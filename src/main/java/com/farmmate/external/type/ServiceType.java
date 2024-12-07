package com.farmmate.external.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ServiceType {
	XML("AA001"),
	JSON("AA003");

	private final String code;
}
