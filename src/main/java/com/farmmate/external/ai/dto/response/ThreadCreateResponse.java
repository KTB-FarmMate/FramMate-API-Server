package com.farmmate.external.ai.dto.response;

public record ThreadCreateResponse(
	String message,
	ChatRoomData data
) {
	public record ChatRoomData(
		String threadId
	) {
	}
}
