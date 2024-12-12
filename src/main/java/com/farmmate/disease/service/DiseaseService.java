package com.farmmate.disease.service;

import org.springframework.stereotype.Service;

import com.farmmate.disease.dto.response.DiseaseDetailResponse;
import com.farmmate.external.ncpms.service.NcpmsService;
import com.farmmate.external.ncpms.vo.DiseaseDetailVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiseaseService {
	private final NcpmsService ncpmsService;

	public DiseaseDetailResponse findPests(String cropName, String searchName) {
		DiseaseDetailVo vo = ncpmsService.findFestAndDisease(cropName, searchName);

		return DiseaseDetailResponse.fromVo(vo);
	}
}
