package com.farmmate.chatroom.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farmmate.chatroom.dto.request.ChatRoomRegistrationRequest;
import com.farmmate.chatroom.dto.response.ChatRoomRegistrationResponse;
import com.farmmate.chatroom.entity.ChatRoom;
import com.farmmate.chatroom.repository.ChatRoomRepository;
import com.farmmate.crop.entity.Crop;
import com.farmmate.crop.repository.CropRepository;
import com.farmmate.member.entity.Member;
import com.farmmate.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomService {
	private final ChatRoomRepository chatRoomRepository;
	private final MemberRepository memberRepository;
	private final CropRepository cropRepository;

	@Transactional(readOnly = true)
	public List<ChatRoomRegistrationResponse> findRegsisteredChatRooms(String memberId) {
		List<ChatRoom> chatRooms = chatRoomRepository.findAllByMemberId(memberId);

		return chatRooms.stream()
			.map(ChatRoomRegistrationResponse::from)
			.collect(Collectors.toUnmodifiableList());
	}

	public void registerChatRoom(String memberId, Integer cropId, ChatRoomRegistrationRequest request) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new IllegalArgumentException("Member not found"));

		Crop crop = cropRepository.findById(cropId)
			.orElseThrow(() -> new IllegalArgumentException("Crop not found"));

		if (chatRoomRepository.existsByMemberAndCrop(member, crop)) {
			throw new IllegalArgumentException("ChatRoom already exists");
		}

		ChatRoom chatRoom = ChatRoom.create(crop, member, request);

		chatRoomRepository.save(chatRoom);
	}
}
