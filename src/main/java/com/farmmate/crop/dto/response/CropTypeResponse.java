package com.farmmate.crop.dto.response;

import com.farmmate.crop.entity.Crop;

import lombok.Builder;

@Builder
public record CropTypeResponse(Integer cropId, String cropName) {
	public static CropTypeResponse from(Crop crop) {
		return CropTypeResponse.builder()
			.cropId(crop.getId())
			.cropName(crop.getName())
			.build();
	}
}
