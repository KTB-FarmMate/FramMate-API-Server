package com.farmmate.chatroom.dto.response;

import com.farmmate.external.ai.vo.ThreadMessageSendVo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "메시지 전송 응답")
public record MessageSendResponse(@Schema(description = "AI측 메시지 답장") String message) {
	public static MessageSendResponse from(ThreadMessageSendVo vo) {
		return new MessageSendResponse(vo.message());
	}
}
