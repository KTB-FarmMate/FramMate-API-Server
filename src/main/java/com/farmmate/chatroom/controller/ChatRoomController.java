package com.farmmate.chatroom.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmmate.chatroom.dto.request.ChatRoomRegistrationRequest;
import com.farmmate.chatroom.dto.request.ChatRoomUpdateRequest;
import com.farmmate.chatroom.dto.response.ChatRoomDetailResponse;
import com.farmmate.chatroom.dto.response.RegisteredThreadFindResponse;
import com.farmmate.chatroom.dto.response.ThreadRegisterResponse;
import com.farmmate.chatroom.service.ChatRoomService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Tag(name = "채팅방", description = "채팅방 관련 API")
public class ChatRoomController {
	private final ChatRoomService chatRoomService;

	@Operation(summary = "채팅방 목록 조회", description = "사용자가 등록한 채팅방 목록을 조회합니다.")
	@GetMapping("/members/{memberId}/threads")
	public List<RegisteredThreadFindResponse> findChatRooms(@PathVariable String memberId) {
		return chatRoomService.findRegisteredChatRooms(memberId);
	}

	@Operation(summary = "채팅방 등록", description = "사용자가 선택한 작물에 대한 채팅방을 등록합니다.")
	@PostMapping("/members/{memberId}/threads")
	public ResponseEntity<ThreadRegisterResponse> registerChatRoom(@PathVariable String memberId,
		@RequestBody ChatRoomRegistrationRequest request) {
		ThreadRegisterResponse response = chatRoomService.registerChatRoom(memberId, request);

		return ResponseEntity.created(null).body(response);
	}

	@Operation(summary = "채팅방 수정", description = "사용자가 등록한 채팅방을 수정합니다.")
	@PatchMapping("/members/{memberId}/threads/{threadId}")
	public ResponseEntity<Void> updateChatRoom(@PathVariable String memberId, @PathVariable String threadId,
		@RequestBody ChatRoomUpdateRequest request) {
		chatRoomService.updateChatRoom(memberId, threadId, request);

		return ResponseEntity.ok().build();
	}

	@Operation(summary = "채팅방 삭제", description = "사용자가 등록한 채팅방을 삭제합니다.")
	@DeleteMapping("/members/{memberId}/threads/{threadId}")
	public ResponseEntity<Void> unregisterChatRoom(@PathVariable String memberId, @PathVariable String threadId) {
		chatRoomService.unregisterChatRoom(memberId, threadId);

		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "채팅방 상세 조회", description = "사용자가 등록한 채팅방의 대화 내역을 조회합니다.")
	@GetMapping("/members/{memberId}/threads/{threadId}")
	public ChatRoomDetailResponse getChatRoomDetail(@PathVariable String memberId,
		@PathVariable String threadId) {
		return chatRoomService.getChatRoomDetail(memberId, threadId);
	}
}
