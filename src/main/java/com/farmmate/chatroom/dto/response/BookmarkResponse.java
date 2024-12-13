package com.farmmate.chatroom.dto.response;

import java.time.LocalDateTime;

import com.farmmate.chatroom.entity.Bookmark;

public record BookmarkResponse(String question, String answer, LocalDateTime addedAt) {
	public static BookmarkResponse from(Bookmark bookmark) {
		return new BookmarkResponse(bookmark.getQuestion(), bookmark.getAnswer(), bookmark.getAddedAt());
	}
}
