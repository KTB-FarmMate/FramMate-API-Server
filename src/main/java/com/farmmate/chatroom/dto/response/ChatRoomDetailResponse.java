package com.farmmate.chatroom.dto.response;

import java.util.List;

import com.farmmate.external.ai.type.Role;
import com.farmmate.external.ai.vo.ThreadDetailVo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "채팅방 상세 조회 응답")
public record ChatRoomDetailResponse(
	@Schema(description = "채팅방 ID") String threadId,
	@Schema(description = "메시지 목록") List<Message> messages
) {
	public static ChatRoomDetailResponse from(ThreadDetailVo vo) {
		List<Message> messages = vo.messages().stream()
			.map(Message::fromMessage)
			.toList();

		return new ChatRoomDetailResponse(vo.threadId(), messages);
	}
	
	@Schema(title = "메시지")
	public record Message(Role role, String text) {
		public static Message fromMessage(ThreadDetailVo.Message message) {
			return new Message(message.role(), message.text());
		}
	}
}
