package com.farmmate.chatroom.dto.response;

import com.farmmate.chatroom.entity.ChatRoom;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record RegisteredThreadFindResponse(Integer cropId, String cropName, String chatRoomId) {
	public static RegisteredThreadFindResponse from(ChatRoom chatRoom) {
		return RegisteredThreadFindResponse.builder()
			.cropId(chatRoom.getCrop().getId())
			.cropName(chatRoom.getCrop().getName())
			.chatRoomId(chatRoom.getId())
			.build();
	}
}
