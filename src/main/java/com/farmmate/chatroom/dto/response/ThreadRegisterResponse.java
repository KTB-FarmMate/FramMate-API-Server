package com.farmmate.chatroom.dto.response;

import com.farmmate.chatroom.entity.ChatRoom;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "채팅방 등록 응답")
public record ThreadRegisterResponse(
	@Schema(description = "채팅방 ID(식별자)", defaultValue = "thread_Q2NO6D1Jt8Stc9g8ds7LfOY6") String threadId
) {
	public static ThreadRegisterResponse from(ChatRoom chatRoom) {
		return new ThreadRegisterResponse(chatRoom.getId());
	}
}
