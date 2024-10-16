package com.farmmate.chatroom.entity;

import java.time.LocalDateTime;

import com.farmmate.chatroom.dto.request.ChatRoomRegistrationRequest;
import com.farmmate.crop.entity.Crop;
import com.farmmate.member.entity.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

	@OneToOne(fetch = FetchType.LAZY)
	private Crop crop;

	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private Boolean isGrowing;

	private String address;

	@Builder(access = AccessLevel.PRIVATE)
	private ChatRoom(Crop crop, Member member, Boolean isGrowing, String address) {
		this.crop = crop;
		this.member = member;
		this.isGrowing = isGrowing;
		this.address = address;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	public static ChatRoom create(Crop crop, Member member, ChatRoomRegistrationRequest request) {
		return ChatRoom.builder()
			.crop(crop)
			.member(member)
			.isGrowing(true)
			.address(request.address())
			.build();
	}
}
