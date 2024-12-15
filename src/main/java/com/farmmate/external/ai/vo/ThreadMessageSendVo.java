package com.farmmate.external.ai.vo;

import com.farmmate.external.ai.dto.response.AiThreadMessageSendResponse;

public record ThreadMessageSendVo(String message) {
	public static ThreadMessageSendVo fromResponse(AiThreadMessageSendResponse response) {
		return new ThreadMessageSendVo(response.data().text());
	}
}
