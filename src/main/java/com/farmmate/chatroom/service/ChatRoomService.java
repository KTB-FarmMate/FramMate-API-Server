package com.farmmate.chatroom.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farmmate.chatroom.dto.request.ChatRoomRegistrationRequest;
import com.farmmate.chatroom.dto.request.ChatRoomUpdateRequest;
import com.farmmate.chatroom.dto.response.RegisteredThreadFindResponse;
import com.farmmate.chatroom.dto.response.ThreadRegisterResponse;
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
	public List<RegisteredThreadFindResponse> findRegisteredChatRooms(String memberId) {
		List<ChatRoom> chatRooms = chatRoomRepository.findAllByMemberId(memberId);

		return chatRooms.stream()
			.map(RegisteredThreadFindResponse::from)
			.collect(Collectors.toUnmodifiableList());
	}

	public ThreadRegisterResponse registerChatRoom(String memberId,
		ChatRoomRegistrationRequest request) {
		Member member = memberRepository.findById(memberId)    // FIXME: getReferenceById로 조회시에도 SELECT 쿼리가 발생함
			.orElseThrow(() -> new IllegalArgumentException("Member not found"));

		Crop crop = cropRepository.findById(request.cropId()) // FIXME: getReferenceById로 조회시에도 SELECT 쿼리가 발생함
			.orElseThrow(() -> new IllegalArgumentException("Crop not found"));

		if (chatRoomRepository.existsByMemberAndCrop(member, crop)) {
			throw new IllegalArgumentException("ChatRoom already exists");
		}

		ChatRoom newChatRoom = ChatRoom.create(crop, member, request);
		ChatRoom savedChatRoom = chatRoomRepository.save(newChatRoom);

		return ThreadRegisterResponse.from(savedChatRoom);
	}

	public void unregisterChatRoom(String memberId, String chatRoomId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new IllegalArgumentException("Member not found"));

		ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
			.orElseThrow(() -> new IllegalArgumentException("ChatRoom not found"));

		if (!chatRoom.getMember().equals(member)) {
			throw new IllegalArgumentException("ChatRoom does not belong to the member");
		}

		chatRoomRepository.delete(chatRoom);
	}

	public void updateChatRoom(String memberId, String threadId, ChatRoomUpdateRequest request) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new IllegalArgumentException("Member not found"));

		ChatRoom chatRoom = chatRoomRepository.findById(threadId)
			.orElseThrow(() -> new IllegalArgumentException("ChatRoom not found"));

		if (!chatRoom.getMember().equals(member)) {
			throw new IllegalArgumentException("ChatRoom does not belong to the member");
		}

		chatRoom.update(request);

		chatRoomRepository.save(chatRoom);
	}
}
