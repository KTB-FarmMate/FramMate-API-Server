package com.farmmate.external.ai.dto.response;

public record AiThreadMessageSendResponse(String message, Data data) {

	public record Data(String theadId, String text) {

	}
}
