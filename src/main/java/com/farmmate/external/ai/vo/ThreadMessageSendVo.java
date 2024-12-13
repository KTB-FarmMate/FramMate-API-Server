package com.farmmate.external.ai.vo;

import com.farmmate.external.ai.dto.response.ThreadMessageSendResponse;

public record ThreadMessageSendVo(String message) {
	public static ThreadMessageSendVo fromResponse(ThreadMessageSendResponse response) {
		return new ThreadMessageSendVo(response.data().text());
	}
}
