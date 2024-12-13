package com.farmmate.external.ai.vo;

import java.util.List;

import com.farmmate.external.ai.dto.response.CropStatusResponse;

public record CropStatusVo(List<String> recommendedActions) {
	public static CropStatusVo fromResponse(CropStatusResponse response) {
		List<String> recommendedActions = response.data().recommendedActions();

		return new CropStatusVo(recommendedActions);
	}
}
