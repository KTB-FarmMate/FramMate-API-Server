package com.farmmate.chatroom.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farmmate.chatroom.dto.response.ChatRoomRegistrationResponse;
import com.farmmate.chatroom.entity.ChatRoom;
import com.farmmate.chatroom.repository.ChatRoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomService {
	private final ChatRoomRepository chatRoomRepository;

	@Transactional(readOnly = true)
	public List<ChatRoomRegistrationResponse> findRegsisteredChatRooms(String memberId) {
		List<ChatRoom> chatRooms = chatRoomRepository.findAllByMemberId(memberId);

		return chatRooms.stream()
			.map(ChatRoomRegistrationResponse::from)
			.collect(Collectors.toUnmodifiableList());
	}
}
