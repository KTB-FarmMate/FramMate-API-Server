package com.farmmate.chatroom.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmmate.chatroom.dto.request.ChatRoomRegistrationRequest;
import com.farmmate.chatroom.dto.response.ChatRoomRegistrationResponse;
import com.farmmate.chatroom.service.ChatRoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class ChatRoomController {
	private final ChatRoomService chatRoomService;

	@GetMapping("{memberId}/chat-rooms")
	public List<ChatRoomRegistrationResponse> findChatRooms(@PathVariable String memberId) {
		return chatRoomService.findRegsisteredChatRooms(memberId);
	}

	@PostMapping("{memberId}/chat-rooms/{cropId}")
	public ResponseEntity<Void> registerChatRoom(@PathVariable String memberId, @PathVariable Integer cropId,
		@RequestBody ChatRoomRegistrationRequest request) {
		chatRoomService.registerChatRoom(memberId, cropId, request);

		return ResponseEntity.created(null).build();
	}

	@DeleteMapping("{memberId}/chat-rooms/{chatRoomId}")
	public ResponseEntity<Void> unregisterChatRoom(@PathVariable String memberId, @PathVariable Integer chatRoomId) {
		chatRoomService.unregisterChatRoom(memberId, chatRoomId);

		return ResponseEntity.noContent().build();
	}
}
