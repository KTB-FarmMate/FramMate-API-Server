package com.farmmate.chatroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmmate.chatroom.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

	List<ChatRoom> findAllByMemberId(String memberId);
}
