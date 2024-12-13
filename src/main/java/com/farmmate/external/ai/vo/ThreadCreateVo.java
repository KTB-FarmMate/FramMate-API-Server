package com.farmmate.external.ai.vo;

import com.farmmate.external.ai.dto.response.ThreadCreateResponse;

public record ThreadCreateVo(String threadId) {
	public static ThreadCreateVo fromResponse(ThreadCreateResponse response) {
		return new ThreadCreateVo(response.data().threadId());
	}
}
