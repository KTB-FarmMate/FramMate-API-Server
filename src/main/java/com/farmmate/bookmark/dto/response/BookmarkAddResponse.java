package com.farmmate.bookmark.dto.response;

import com.farmmate.bookmark.entity.Bookmark;

public record BookmarkAddResponse(String bookmarkId) {
	public static BookmarkAddResponse from(Bookmark bookmark) {
		return new BookmarkAddResponse(bookmark.getId());
	}
}
