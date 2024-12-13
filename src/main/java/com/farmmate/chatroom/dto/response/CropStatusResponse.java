package com.farmmate.chatroom.dto.response;

import java.util.List;

import com.farmmate.external.ai.vo.CropStatusVo;

public record CropStatusResponse(List<String> recommendedActions) {
	public static CropStatusResponse from(CropStatusVo vo) {
		List<String> recommendedActions = vo.recommendedActions();

		return new CropStatusResponse(recommendedActions);
	}
}
