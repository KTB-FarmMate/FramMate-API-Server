package com.farmmate.external.ai.vo;

import java.util.List;

import com.farmmate.external.ai.dto.response.AiCropStatusResponse;

public record CropStatusVo(List<String> recommendedActions) {
	public static CropStatusVo fromResponse(AiCropStatusResponse response) {
		List<String> recommendedActions = response.data().recommendedActions();

		return new CropStatusVo(recommendedActions);
	}
}
