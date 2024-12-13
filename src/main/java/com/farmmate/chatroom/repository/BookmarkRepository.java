package com.farmmate.chatroom.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.farmmate.chatroom.entity.Bookmark;

public interface BookmarkRepository extends MongoRepository<Bookmark, String> {
	List<Bookmark> findAllByChatRoomId(String chatRoomId);
}
