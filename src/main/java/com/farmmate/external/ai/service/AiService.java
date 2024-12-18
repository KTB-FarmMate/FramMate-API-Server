package com.farmmate.external.ai.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.farmmate.chatroom.dto.request.ChatRoomUpdateRequest;
import com.farmmate.chatroom.dto.request.MessageSendRequest;
import com.farmmate.external.ai.dto.request.AiThreadCreateRequest;
import com.farmmate.external.ai.dto.request.AiThreadUpdateRequest;
import com.farmmate.external.ai.dto.response.AiCropStatusResponse;
import com.farmmate.external.ai.dto.response.AiReportingPestsResponse;
import com.farmmate.external.ai.dto.response.AiThreadCreateResponse;
import com.farmmate.external.ai.dto.response.AiThreadDeleteResponse;
import com.farmmate.external.ai.dto.response.AiThreadDetailResponse;
import com.farmmate.external.ai.dto.response.AiThreadMessageSendResponse;
import com.farmmate.external.ai.vo.CropStatusVo;
import com.farmmate.external.ai.vo.CurrentReportingPestsVo;
import com.farmmate.external.ai.vo.ThreadCreateVo;
import com.farmmate.external.ai.vo.ThreadDetailVo;
import com.farmmate.external.ai.vo.ThreadMessageSendVo;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AiService {
	private WebClient webClient;
	@Value("${chatbot.api.url}")
	private String API_URL;

	@PostConstruct
	public void init() {
		DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(API_URL);
		uriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
		this.webClient = WebClient.builder()
			.uriBuilderFactory(uriBuilderFactory)
			.defaultHeader("Content-Type", "application/json")
			// .filter(logRequest())
			.build();
	}

	public ThreadCreateVo createThread(String memberId, String cropName, String address, LocalDate plantedAt) {
		AiThreadCreateResponse response = webClient.post()
			.uri(uriBuilder -> uriBuilder
				.path("/members/{memberId}/threads")
				.build(memberId))
			.bodyValue(
				new AiThreadCreateRequest(cropName, address,
					plantedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
			.retrieve()
			.bodyToMono(AiThreadCreateResponse.class)
			.block();

		return ThreadCreateVo.fromResponse(response);
	}

	public ThreadDetailVo getThreadDetail(String memberId, String threadId) {
		AiThreadDetailResponse response = webClient.get()
			.uri(uriBuilder -> uriBuilder
				.path("/members/{memberId}/threads/{threadId}")
				.build(memberId, threadId))
			.retrieve()
			.bodyToMono(AiThreadDetailResponse.class)
			.block();

		return ThreadDetailVo.fromResponse(response);
	}

	public ThreadMessageSendVo sendMessage(String memberId, String threadId, String message) {
		AiThreadMessageSendResponse response = webClient.post()
			.uri(uriBuilder -> uriBuilder
				.path("/members/{memberId}/threads/{threadId}")
				.build(memberId, threadId))
			.bodyValue(new MessageSendRequest(message))
			.retrieve()
			.bodyToMono(AiThreadMessageSendResponse.class)
			.block();

		return ThreadMessageSendVo.fromResponse(response);
	}

	public void deleteThread(String memberId, String chatRoomId) {
		AiThreadDeleteResponse response = webClient.delete()
			.uri(uriBuilder -> uriBuilder
				.path("/members/{memberId}/threads/{threadId}")
				.build(memberId, chatRoomId))
			.retrieve()
			.bodyToMono(AiThreadDeleteResponse.class)
			.doOnSuccess(res -> log.info("쓰레드 삭제 성공(Thread ID): {}", res))
			.doOnError(e -> log.error("쓰레드 삭제 에러(Thread ID): {}", e.getMessage()))
			.block();
	}

	public CropStatusVo getCropStatus(String memberId, String threadId, String cropName) {
		AiCropStatusResponse response = webClient.get()
			.uri(uriBuilder -> uriBuilder
				.path("/members/{memberId}/threads/{threadId}/status")
				.queryParam("cropName", cropName)
				.build(memberId, threadId))
			.retrieve()
			.bodyToMono(AiCropStatusResponse.class)
			.doOnSuccess(res -> log.info("작물 상태 조회 성공: {}", res))
			.doOnError(e -> log.error("작물 상태 조회 에러: {}", e.getMessage()))
			.block();

		return CropStatusVo.fromResponse(response);
	}

	public void updateThread(String memberId, String threadId, ChatRoomUpdateRequest request) {
		AiThreadUpdateRequest updateRequest = new AiThreadUpdateRequest(request.address(),
			request.plantedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

		webClient.patch()
			.uri(uriBuilder -> uriBuilder
				.path("/members/{memberId}/threads/{threadId}")
				.build(memberId, threadId))
			.bodyValue(updateRequest)
			.retrieve()
			.bodyToMono(Void.class)
			.doOnSuccess(res -> log.info("쓰레드 수정 성공(Thread ID): {}", threadId))
			.doOnError(e -> log.error("쓰레드 수정 에러(Thread ID): {}", e.getMessage()))
			.block();
	}

	public CurrentReportingPestsVo getCurrentReportingPests(String cropName) {
		AiReportingPestsResponse response = webClient.get()
			.uri(uriBuilder -> uriBuilder
				.path("/pests")
				.queryParam("cropName", cropName)
				.build())
			.retrieve()
			.bodyToMono(AiReportingPestsResponse.class)
			.doOnSuccess(res -> log.info("해충 정보 조회 성공: {}", res))
			.doOnError(e -> {
				log.error("해충 정보 조회 에러: {}", e.getMessage());
				throw new IllegalArgumentException("외부  " + e.getMessage());
			})
			.block();

		return CurrentReportingPestsVo.fromResponse(response);
	}
}
