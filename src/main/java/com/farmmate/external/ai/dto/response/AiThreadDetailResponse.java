package com.farmmate.external.ai.dto.response;

import java.util.List;

import com.farmmate.external.ai.type.Role;

public record AiThreadDetailResponse(
	String message,
	Data data
) {
	public record Data(String threadId, List<Message> messages) {
		public record Message(Role role, String text) {
		}
	}
}
