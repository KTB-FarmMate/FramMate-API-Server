package com.farmmate.external.ai.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AiReportingPestsResponse(
	String message,
	Data data
) {
	public record Data(
		@JsonProperty("watch") List<String> watches,
		@JsonProperty("warning") List<String> warnings,
		@JsonProperty("forecast") List<String> forecasts
	) {

	}
}
