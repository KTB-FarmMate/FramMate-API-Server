package com.farmmate.bookmark.dto.response;

import com.farmmate.bookmark.entity.Bookmark;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "북마크 등록 응답")
public record BookmarkAddResponse(
	@Schema(description = "저장된 북마크 ID", example = "60f1b3b7b3b3b3b3b3b3b3b3") String bookmarkId
) {
	public static BookmarkAddResponse from(Bookmark bookmark) {
		return new BookmarkAddResponse(bookmark.getId());
	}
}
