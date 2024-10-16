package com.farmmate.chatroom.entity;

import java.time.LocalDateTime;

import com.farmmate.crop.entity.Crop;
import com.farmmate.member.entity.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Entity
@Getter
public class ChatRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	private Crop crop;

	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;

	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

	private Boolean isGrowing;
}
