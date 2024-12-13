package com.farmmate.chatroom.dto.response;

import java.util.List;

import com.farmmate.external.ai.type.Role;
import com.farmmate.external.ai.vo.ThreadDetailVo;

public record ChatRoomDetailResponse(String threadId, List<Message> messages) {
	public static ChatRoomDetailResponse from(ThreadDetailVo vo) {
		List<Message> messages = vo.messages().stream()
			.map(Message::fromMessage)
			.toList();

		return new ChatRoomDetailResponse(vo.threadId(), messages);
	}

	public record Message(Role role, String text) {
		public static Message fromMessage(ThreadDetailVo.Message message) {
			return new Message(message.role(), message.text());
		}
	}
}
