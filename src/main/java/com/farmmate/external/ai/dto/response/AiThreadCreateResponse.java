package com.farmmate.external.ai.dto.response;

public record AiThreadCreateResponse(
	String message,
	ChatRoomData data
) {
	public record ChatRoomData(
		String threadId
	) {
	}
}
