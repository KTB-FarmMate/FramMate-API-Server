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
import com.farmmate.chatroom.dto.response.RegisteredThreadFindResponse;
import com.farmmate.chatroom.dto.response.ThreadRegisterResponse;
import com.farmmate.chatroom.service.ChatRoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class ChatRoomController {
	private final ChatRoomService chatRoomService;

	@GetMapping("{memberId}/chat-rooms")
	public List<RegisteredThreadFindResponse> findChatRooms(@PathVariable String memberId) {
		return chatRoomService.findRegisteredChatRooms(memberId);
	}

	@PostMapping("{memberId}/chat-rooms/{cropId}")
	public ResponseEntity<ThreadRegisterResponse> registerChatRoom(@PathVariable String memberId,
		@PathVariable Integer cropId,
		@RequestBody ChatRoomRegistrationRequest request) {
		ThreadRegisterResponse response = chatRoomService.registerChatRoom(memberId, cropId, request);

		return ResponseEntity.created(null).body(response);
	}

	@DeleteMapping("{memberId}/chat-rooms/{chatRoomId}")
	public ResponseEntity<Void> unregisterChatRoom(@PathVariable String memberId, @PathVariable String chatRoomId) {
		chatRoomService.unregisterChatRoom(memberId, chatRoomId);

		return ResponseEntity.noContent().build();
	}
}
