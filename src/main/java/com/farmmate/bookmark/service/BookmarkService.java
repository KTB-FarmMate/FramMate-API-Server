package com.farmmate.bookmark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.farmmate.bookmark.dto.request.BookmarkAddRequest;
import com.farmmate.bookmark.dto.response.BookmarkAddResponse;
import com.farmmate.bookmark.dto.response.BookmarkResponse;
import com.farmmate.bookmark.entity.Bookmark;
import com.farmmate.chatroom.entity.ChatRoom;
import com.farmmate.chatroom.repository.BookmarkRepository;
import com.farmmate.chatroom.repository.ChatRoomRepository;
import com.farmmate.global.common.util.RepositoryUtils;
import com.farmmate.global.error.exception.CustomException;
import com.farmmate.global.error.exception.ErrorCode;
import com.farmmate.member.entity.Member;
import com.farmmate.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookmarkService {
	private final BookmarkRepository bookmarkRepository;
	private final MemberRepository memberRepository;
	private final ChatRoomRepository chatRoomRepository;

	public List<BookmarkResponse> findAllBookmarks(String memberId, String threadId) {
		Member memberProxy = RepositoryUtils.getReferenceOrThrow(memberRepository, memberId,
			ErrorCode.MEMBER_NOT_FOUND);

		ChatRoom chatRoom = chatRoomRepository.findById(threadId)
			.orElseThrow(() -> new CustomException(ErrorCode.CHAT_ROOM_NOT_FOUND));

		if (!chatRoom.getMember().equals(memberProxy)) {
			throw new CustomException(ErrorCode.CHAT_ROOM_NOT_FOUND);
		}

		return bookmarkRepository.findAllByChatRoomId(threadId)
			.stream()
			.map(BookmarkResponse::from)
			.toList();
	}

	public BookmarkAddResponse addBookmark(String memberId, String threadId, BookmarkAddRequest request) {
		Member memberProxy = RepositoryUtils.getReferenceOrThrow(memberRepository, memberId,
			ErrorCode.MEMBER_NOT_FOUND);

		ChatRoom chatRoom = chatRoomRepository.findById(threadId)
			.orElseThrow(() -> new CustomException(ErrorCode.CHAT_ROOM_NOT_FOUND));

		if (!chatRoom.getMember().equals(memberProxy)) {
			throw new CustomException(ErrorCode.CHAT_ROOM_NOT_FOUND);
		}

		Bookmark bookmark = Bookmark.create(chatRoom, request);

		Bookmark savedBookmark = bookmarkRepository.save(bookmark);

		return BookmarkAddResponse.from(savedBookmark);
	}

	public void removeBookmark(String memberId, String threadId, String bookmarkId) {
		Member memberProxy = RepositoryUtils.getReferenceOrThrow(memberRepository, memberId,
			ErrorCode.MEMBER_NOT_FOUND);

		ChatRoom chatRoom = chatRoomRepository.findById(threadId)
			.orElseThrow(() -> new CustomException(ErrorCode.CHAT_ROOM_NOT_FOUND));

		if (!chatRoom.getMember().equals(memberProxy)) {
			throw new CustomException(ErrorCode.CHAT_ROOM_NOT_FOUND);
		}

		Bookmark bookmark = bookmarkRepository.findById(bookmarkId)
			.orElseThrow(() -> new CustomException(ErrorCode.BOOKMARK_NOT_FOUND));

		bookmarkRepository.delete(bookmark);
	}
}
