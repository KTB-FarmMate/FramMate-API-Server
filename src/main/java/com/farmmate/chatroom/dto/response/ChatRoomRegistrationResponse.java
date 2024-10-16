package com.farmmate.chatroom.dto.response;

import com.farmmate.chatroom.entity.ChatRoom;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ChatRoomRegistrationResponse(Long cropId, String cropName, Long chatRoomId) {
	public static ChatRoomRegistrationResponse from(ChatRoom chatRoom) {
		return ChatRoomRegistrationResponse.builder()
			.cropId(chatRoom.getCrop().getId())
			.cropName(chatRoom.getCrop().getName())
			.chatRoomId(chatRoom.getId())
			.build();
	}
}
