package com.farmmate.bookmark.dto.response;

import java.time.LocalDateTime;

import com.farmmate.bookmark.entity.Bookmark;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record BookmarkResponse(String bookmarkId, String question, String answer, LocalDateTime chattedAt,
							   LocalDateTime addedAt) {
	public static BookmarkResponse from(Bookmark bookmark) {
		return BookmarkResponse.builder()
			.bookmarkId(bookmark.getId())
			.question(bookmark.getQuestion())
			.answer(bookmark.getAnswer())
			.chattedAt(bookmark.getChattedAt())
			.addedAt(bookmark.getAddedAt())
			.build();
	}
}
