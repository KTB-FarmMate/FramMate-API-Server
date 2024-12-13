package com.farmmate.chatroom.dto.response;

import com.farmmate.chatroom.entity.Bookmark;

public record BookmarkAddResponse(String bookmarkId) {
	public static BookmarkAddResponse from(Bookmark bookmark) {
		return new BookmarkAddResponse(bookmark.getId());
	}
}
