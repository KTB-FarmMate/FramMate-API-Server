package com.farmmate.external.ai.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.farmmate.external.ai.dto.request.ThreadCreateRequest;
import com.farmmate.external.ai.dto.response.ThreadCreateResponse;
import com.farmmate.external.ai.vo.ThreadCreateVo;

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
		ThreadCreateResponse response = webClient.post()
			.uri(uriBuilder -> uriBuilder
				.path("/members/{memberId}/threads")
				.build(memberId))
			.bodyValue(
				new ThreadCreateRequest(cropName, address, plantedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
			.retrieve()
			.bodyToMono(ThreadCreateResponse.class)
			.block();

		return ThreadCreateVo.fromResponse(response);
	}
}
