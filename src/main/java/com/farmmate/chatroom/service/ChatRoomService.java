package com.farmmate.chatroom.service;

import java.util.List;

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
import com.farmmate.global.error.exception.CustomException;
import com.farmmate.global.error.exception.ErrorCode;
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
			.toList();
	}

	public ThreadRegisterResponse registerChatRoom(String memberId,
		ChatRoomRegistrationRequest request) {
		Member member = memberRepository.getReferenceById(memberId);   // FIXME: getReferenceById로 조회시에도 SELECT 쿼리가 발생

		Crop crop = cropRepository.findById(request.cropId()) // FIXME: getReferenceById로 조회시에도 SELECT 쿼리가 발생함
			.orElseThrow(() -> new CustomException(ErrorCode.CROP_NOT_FOUND));

		if (chatRoomRepository.existsByMemberAndCrop(member, crop)) {
			throw new CustomException(ErrorCode.CHAT_ROOM_ALREADY_EXISTS);
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
