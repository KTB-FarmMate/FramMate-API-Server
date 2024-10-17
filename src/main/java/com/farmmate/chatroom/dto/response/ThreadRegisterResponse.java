package com.farmmate.chatroom.dto.response;

import com.farmmate.chatroom.entity.ChatRoom;

public record ThreadRegisterResponse(String threadId) {
	public static ThreadRegisterResponse from(ChatRoom chatRoom) {
		return new ThreadRegisterResponse(chatRoom.getId());
	}
}
