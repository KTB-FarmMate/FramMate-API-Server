package com.farmmate.bookmark.dto.request;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "북마크 등록 요청")
public record BookmarkAddRequest(
	@Schema(title = "질문", example = "어떤 작물을 심었나요?") String question,
	@Schema(title = "답변", example = "당근을 심었습니다.") String answer,
	@Schema(title = "채팅 시간", example = "2021-08-01T00:00:00") LocalDateTime chattedAt
) {
}
