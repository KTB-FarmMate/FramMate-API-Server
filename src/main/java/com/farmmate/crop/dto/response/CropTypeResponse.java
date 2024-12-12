package com.farmmate.crop.dto.response;

import com.farmmate.crop.entity.Crop;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "서버에 등록되어 있는 작물 응답")
public record CropTypeResponse(
	@Schema(description = "작물 ID(식별자)", defaultValue = "1") Integer cropId,
	@Schema(description = "작물 이름", defaultValue = "쌀") String cropName) {
	public static CropTypeResponse from(Crop crop) {
		return CropTypeResponse.builder()
			.cropId(crop.getId())
			.cropName(crop.getName())
			.build();
	}
}
