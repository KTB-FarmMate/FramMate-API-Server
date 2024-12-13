package com.farmmate.chatroom.entity;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Getter;

@Document(collection = "bookmark")
@Getter
public class Bookmark {
	@Id
	private String id;

	@Indexed
	private String chatRoomId;

	private String question;
	private String answer;
	private LocalDateTime addedAt;

	@Builder(access = AccessLevel.PRIVATE)
	private Bookmark(String chatRoomId, String question, String answer) {
		this.chatRoomId = chatRoomId;
		this.question = question;
		this.answer = answer;
		this.addedAt = LocalDateTime.now();
	}

	public static Bookmark create(ChatRoom chatRoom, BookmarkAddRequest request) {
		return Bookmark.builder()
			.chatRoomId(chatRoom.getId())
			.question(request.question())
			.answer(request.answer())
			.build();
	}
}
