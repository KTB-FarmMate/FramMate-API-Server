package com.farmmate.external.ai.dto.response;

import java.util.List;

public record ThreadDetailResponse(
	String message,
	Data data
) {
	public record Data(String threadId, List<Message> messages) {
		public record Message(String role, String text) {
		}
	}
}
