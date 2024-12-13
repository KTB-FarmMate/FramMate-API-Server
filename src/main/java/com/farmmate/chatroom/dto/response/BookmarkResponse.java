package com.farmmate.chatroom.dto.response;

import java.time.LocalDateTime;

import com.farmmate.chatroom.entity.Bookmark;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record BookmarkResponse(String bookmarkId, String question, String answer, LocalDateTime addedAt) {
	public static BookmarkResponse from(Bookmark bookmark) {
		return BookmarkResponse.builder()
			.bookmarkId(bookmark.getId())
			.question(bookmark.getQuestion())
			.answer(bookmark.getAnswer())
			.addedAt(bookmark.getAddedAt())
			.build();
	}
}
