package com.farmmate.chatroom.dto.response;

import com.farmmate.external.ai.vo.ThreadMessageSendVo;

public record MessageSendResponse(String message) {
	public static MessageSendResponse from(ThreadMessageSendVo vo) {
		return new MessageSendResponse(vo.message());
	}
}
