package com.farmmate.external.ai.dto.response;

import java.util.List;

public record AiReportingPestsResponse(
	String message,
	Data data
) {
	public record Data(
		List<String> advisories,
		List<String> warnings,
		List<String> forecasts
	) {

	}
}
