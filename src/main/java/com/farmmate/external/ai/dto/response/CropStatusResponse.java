package com.farmmate.external.ai.dto.response;

import java.util.List;

public record CropStatusResponse(String message, Data data) {
	public record Data(List<String> recommendedActions) {
	}
}
