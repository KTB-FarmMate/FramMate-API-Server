package com.farmmate.chatroom.dto.response;

import java.util.List;

import com.farmmate.external.ai.vo.CropStatusVo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "작물 대시보드 조회 응답")
public record CropStatusResponse(@Schema(description = "현재 추천 할 일") List<String> recommendedActions) {
	public static CropStatusResponse from(CropStatusVo vo) {
		List<String> recommendedActions = vo.recommendedActions();

		return new CropStatusResponse(recommendedActions);
	}
}
