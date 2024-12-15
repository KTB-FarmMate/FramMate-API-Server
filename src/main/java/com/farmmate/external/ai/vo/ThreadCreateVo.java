package com.farmmate.external.ai.vo;

import com.farmmate.external.ai.dto.response.AiThreadCreateResponse;

public record ThreadCreateVo(String threadId) {
	public static ThreadCreateVo fromResponse(AiThreadCreateResponse response) {
		return new ThreadCreateVo(response.data().threadId());
	}
}
