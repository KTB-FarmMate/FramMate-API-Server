package com.farmmate.chatroom.dto.response;

import com.farmmate.chatroom.entity.ChatRoom;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
@Schema(title = "등록된 채팅방 응답")
public record RegisteredThreadFindResponse(
	@Schema(description = "작물 ID(식별자)", defaultValue = "1")
	Integer cropId,
	@Schema(description = "작물 이름", defaultValue = "쌀")
	String cropName,
	@Schema(description = "채팅방 ID(식별자)", defaultValue = "thread_Q2NO6D1Jt8Stc9g8ds7LfOY6")
	String chatRoomId
) {
	public static RegisteredThreadFindResponse from(ChatRoom chatRoom) {
		return RegisteredThreadFindResponse.builder()
			.cropId(chatRoom.getCrop().getId())
			.cropName(chatRoom.getCrop().getName())
			.chatRoomId(chatRoom.getId())
			.build();
	}
}
