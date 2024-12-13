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
import com.farmmate.external.ai.service.AiService;
import com.farmmate.external.ai.vo.ThreadCreateVo;
import com.farmmate.global.common.util.RepositoryUtils;
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
	private final AiService aiService;

	@Transactional(readOnly = true)
	public List<RegisteredThreadFindResponse> findRegisteredChatRooms(String memberId) {
		List<ChatRoom> chatRooms = chatRoomRepository.findAllByMemberId(memberId);

		return chatRooms.stream()
			.map(RegisteredThreadFindResponse::from)
			.toList();
	}

	public ThreadRegisterResponse registerChatRoom(String memberId,
		ChatRoomRegistrationRequest request) {
		Member memberProxy = RepositoryUtils.getReferenceOrThrow(memberRepository, memberId,
			ErrorCode.MEMBER_NOT_FOUND);
		Crop crop = cropRepository.findById(request.cropId())
			.orElseThrow(() -> new CustomException(ErrorCode.CROP_NOT_FOUND));

		if (chatRoomRepository.existsByMemberAndCrop(memberProxy, crop)) {
			throw new CustomException(ErrorCode.CHAT_ROOM_ALREADY_EXISTS);
		}

		ThreadCreateVo vo = aiService.createThread(memberId, crop.getName(), request.address(), request.plantedAt());
		ChatRoom chatRoom = ChatRoom.create(crop, memberProxy, vo, request);
		ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);

		return ThreadRegisterResponse.from(savedChatRoom);
	}

	public void unregisterChatRoom(String memberId, String chatRoomId) {
		Member memberProxy = RepositoryUtils.getReferenceOrThrow(memberRepository, memberId,
			ErrorCode.MEMBER_NOT_FOUND);

		ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
			.orElseThrow(() -> new CustomException(ErrorCode.CHAT_ROOM_NOT_FOUND));

		if (!chatRoom.getMember().equals(memberProxy)) {
			throw new CustomException(ErrorCode.CHAT_ROOM_NOT_FOUND);
		}

		chatRoomRepository.delete(chatRoom);
	}

	public void updateChatRoom(String memberId, String threadId, ChatRoomUpdateRequest request) {
		Member memberProxy = RepositoryUtils.getReferenceOrThrow(memberRepository, memberId,
			ErrorCode.MEMBER_NOT_FOUND);

		ChatRoom chatRoom = chatRoomRepository.findById(threadId)
			.orElseThrow(() -> new CustomException(ErrorCode.CHAT_ROOM_NOT_FOUND));

		if (!chatRoom.getMember().equals(memberProxy)) {
			throw new CustomException(ErrorCode.CHAT_ROOM_NOT_FOUND);
		}

		chatRoom.update(request);

		chatRoomRepository.save(chatRoom);
	}
}
