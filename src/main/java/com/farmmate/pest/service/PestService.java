package com.farmmate.pest.service;

import org.springframework.stereotype.Service;

import com.farmmate.crop.entity.Crop;
import com.farmmate.crop.repository.CropRepository;
import com.farmmate.external.ai.service.AiService;
import com.farmmate.external.ai.vo.CurrentReportingPestsVo;
import com.farmmate.external.ncpms.service.NcpmsService;
import com.farmmate.external.ncpms.vo.PestDetailVo;
import com.farmmate.pest.dto.response.CurrentReportingPestsResponse;
import com.farmmate.pest.dto.response.PestDetailResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PestService {
	private final CropRepository cropRepository;
	private final NcpmsService ncpmsService;
	private final AiService aiService;

	public PestDetailResponse findPests(String cropName, String searchName) {
		PestDetailVo vo = ncpmsService.searchPestDetail(cropName, searchName);

		return PestDetailResponse.fromVo(vo);
	}

	public CurrentReportingPestsResponse getCurrentReportingPests(Integer cropId) {
		Crop crop = cropRepository.findById(cropId)
			.orElseThrow(() -> new IllegalArgumentException("해당 작물이 존재하지 않습니다."));
		CurrentReportingPestsVo vo = aiService.getCurrentReportingPests(crop.getName());

		return CurrentReportingPestsResponse.fromVo(vo);
	}
}
