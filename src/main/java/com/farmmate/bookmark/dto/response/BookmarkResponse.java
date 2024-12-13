package com.farmmate.bookmark.dto.response;

import java.time.LocalDateTime;

import com.farmmate.bookmark.entity.Bookmark;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Schema(title = "북마크 응답")
@Builder(access = AccessLevel.PRIVATE)
public record BookmarkResponse(
	@Schema(title = "북마크 ID", example = "60f1b3b7b3b3b3b3b3b3b3b3") String bookmarkId,
	@Schema(title = "질문", example = "작물이 병충해에 걸렸을 때 어떻게 대처해야 하나요?") String question,
	@Schema(title = "답변", example = "병충해에 걸린 작물은 즉시 분리하여 처리해야 합니다.") String answer,
	@Schema(title = "채팅 시각", example = "2021-07-16T10:15:30") LocalDateTime chattedAt,
	@Schema(title = "북마크 추가 시각", example = "2021-07-16T10:15:30") LocalDateTime addedAt
) {
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
