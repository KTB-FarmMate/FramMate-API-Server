package com.farmmate.external.ncpms.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ServiceCode {
	DISEASE_SEARCH("SVC01"), // 병 검색 서비스
	DISEASE_DETAIL("SVC05"),     // 병 상세정보 서비스
	PATHOGEN_SEARCH("SVC02"),    // 병원체 검색 서비스
	PATHOGEN_DETAIL("SVC06"),    // 병원체 상세정보 서비스
	PEST_SEARCH("SVC03"),        // 해충 검색 서비스
	PEST_DETAIL("SVC07"),        // 해충 상세정보 서비스
	INSECT_SEARCH("SVC04"),      // 곤충 검색 서비스
	INSECT_DETAIL("SVC08"),      // 곤충 상세정보 서비스
	WEED_SEARCH("SVC09"),        // 잡초 검색 서비스
	WEED_DETAIL("SVC10"),        // 잡초 상세정보 서비스
	INTEGRATED_SEARCH("SVC16");  // 통합검색 서비스

	private final String code;
}
