package com.farmmate.external.ai.vo;

import java.util.List;

import com.farmmate.external.ai.dto.response.AiReportingPestsResponse;

public record CurrentReportingPestsVo(
	List<String> forecasts,
	List<String> warnings,
	List<String> advisories
) {
	public static CurrentReportingPestsVo fromResponse(AiReportingPestsResponse response) {
		List<String> forecasts = response.data().forecasts();
		List<String> warnings = response.data().warnings();
		List<String> advisories = response.data().advisories();

		return new CurrentReportingPestsVo(forecasts, warnings, advisories);
	}
}
