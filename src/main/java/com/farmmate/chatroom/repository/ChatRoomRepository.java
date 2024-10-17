package com.farmmate.chatroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmmate.chatroom.entity.ChatRoom;
import com.farmmate.crop.entity.Crop;
import com.farmmate.member.entity.Member;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

	List<ChatRoom> findAllByMemberId(String memberId);

	boolean existsByMemberAndCrop(Member member, Crop crop);
}
