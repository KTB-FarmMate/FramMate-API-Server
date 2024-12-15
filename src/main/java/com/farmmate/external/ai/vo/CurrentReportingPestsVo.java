package com.farmmate.external.ai.vo;

import java.util.List;

import com.farmmate.external.ai.dto.response.AiReportingPestsResponse;

public record CurrentReportingPestsVo(
	List<String> forecasts,
	List<String> warnings,
	List<String> watches
) {
	public static CurrentReportingPestsVo fromResponse(AiReportingPestsResponse response) {
		List<String> forecasts = response.data().forecasts();
		List<String> warnings = response.data().warnings();
		List<String> watches = response.data().watches();

		return new CurrentReportingPestsVo(forecasts, warnings, watches);
	}
}
