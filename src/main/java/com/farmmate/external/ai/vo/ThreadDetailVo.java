package com.farmmate.external.ai.vo;

import java.util.List;

import com.farmmate.external.ai.dto.response.ThreadDetailResponse;
import com.farmmate.external.ai.type.Role;

public record ThreadDetailVo(String threadId, List<Message> messages) {
	public static ThreadDetailVo fromResponse(ThreadDetailResponse response) {
		ThreadDetailResponse.Data data = response.data();

		List<Message> messages = data.messages().stream()
			.map(Message::fromData)
			.toList();

		return new ThreadDetailVo(data.threadId(), messages);
	}

	public record Message(Role role, String text) {
		public static Message fromData(ThreadDetailResponse.Data.Message message) {
			return new Message(Role.fromDescription(message.role()), message.text());
		}
	}
}
