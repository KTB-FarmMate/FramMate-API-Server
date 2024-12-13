package com.farmmate.bookmark.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.farmmate.bookmark.dto.request.BookmarkAddRequest;
import com.farmmate.bookmark.dto.response.BookmarkAddResponse;
import com.farmmate.bookmark.dto.response.BookmarkResponse;
import com.farmmate.bookmark.service.BookmarkService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "북마크", description = "북마크 관련 API")
@RestController
@RequiredArgsConstructor
public class BookmarkController {
	private final BookmarkService bookmarkService;

	@Operation(summary = "북마크 조회", description = "채팅방의 사용자가 저장한 북마크를 조회합니다.")
	@GetMapping("/members/{memberId}/threads/{threadId}/bookmarks")
	public List<BookmarkResponse> findBookmarks(@PathVariable String memberId, @PathVariable String threadId) {
		return bookmarkService.findAllBookmarks(memberId, threadId);
	}

	@Operation(summary = "북마크 등록", description = "채팅방의 사용자가 북마크를 등록합니다.")
	@PostMapping("/members/{memberId}/threads/{threadId}/bookmarks")
	public BookmarkAddResponse addBookmark(@PathVariable String memberId, @PathVariable String threadId,
		@RequestBody BookmarkAddRequest request) {
		return bookmarkService.addBookmark(memberId, threadId, request);
	}

	@Operation(summary = "북마크 삭제", description = "채팅방의 사용자가 북마크를 삭제합니다.")
	@DeleteMapping("/members/{memberId}/threads/{threadId}/bookmarks/{bookmarkId}")
	public ResponseEntity<Void> removeBookmark(@PathVariable String memberId, @PathVariable String threadId,
		@PathVariable String bookmarkId) {
		bookmarkService.removeBookmark(memberId, threadId, bookmarkId);

		return ResponseEntity.noContent().build();
	}
}
