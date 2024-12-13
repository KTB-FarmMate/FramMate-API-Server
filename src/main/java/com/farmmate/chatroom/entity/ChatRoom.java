package com.farmmate.chatroom.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.farmmate.chatroom.dto.request.ChatRoomRegistrationRequest;
import com.farmmate.chatroom.dto.request.ChatRoomUpdateRequest;
import com.farmmate.crop.entity.Crop;
import com.farmmate.external.ai.vo.ThreadCreateVo;
import com.farmmate.member.entity.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {
	@Id
	@Column(columnDefinition = "char(31)")
	private String id;

	@OneToOne(fetch = FetchType.LAZY)
	private Crop crop;

	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@Column(nullable = false)
	private LocalDate plantedAt;

	@Column(nullable = false)
	private String address;

	@Builder(access = AccessLevel.PRIVATE)
	private ChatRoom(String id, Crop crop, Member member, String address, LocalDate plantedAt) {
		this.id = id;
		this.crop = crop;
		this.member = member;
		this.address = address;
		this.plantedAt = plantedAt;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	public static ChatRoom create(Crop crop, Member member, ThreadCreateVo vo, ChatRoomRegistrationRequest request) {
		return ChatRoom.builder()
			.id(vo.threadId())
			.crop(crop)
			.member(member)
			.plantedAt(request.plantedAt())
			.address(request.address())
			.build();
	}

	public void update(ChatRoomUpdateRequest request) {
		if (request.address() != null) {
			this.address = request.address();
		}
		if (request.plantedAt() != null) {
			this.plantedAt = request.plantedAt();
		}

		this.updatedAt = LocalDateTime.now();
	}
}
