package com.farmmate.pest.dto.response;

import java.util.List;

import com.farmmate.external.ai.vo.CurrentReportingPestsVo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "작물의 발효중인 병해충 응답")
public record CurrentReportingPestsResponse(
	@Schema(description = "예보", defaultValue = "예보 중인 병해충 목록") List<String> foreCasts, // 예보
	@Schema(description = "주의", defaultValue = "주의 중인 병해충 목록") List<String> watches, // 주의
	@Schema(description = "경고", defaultValue = "경고 중인 병해충 목록") List<String> warnings // 경고
) {
	public static CurrentReportingPestsResponse fromVo(CurrentReportingPestsVo vo) {
		return new CurrentReportingPestsResponse(vo.forecasts(), vo.watches(), vo.warnings());
	}
}
