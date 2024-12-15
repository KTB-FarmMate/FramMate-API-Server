package com.farmmate.external.ncpms.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 최상위 응답 객체, service 필드를 포함합니다.
 */
public record NcpmsIntegratedSearchResponse(

	@JsonProperty("service") Data data // 통합 검색 응답
) {

	/**
	 * 통합 검색 응답
	 */
	public record Data(

		@JsonProperty("buildTime") String buildTime,    // 응답시간
		@JsonProperty("displayCount") String displayCount, // 출력건수
		@JsonProperty("startPoint") int startPoint, // 시작점
		@JsonProperty("totalCount") int totalCount, // 전체건수
		@JsonProperty("list") List<DiseaseDetail> diseaseDetails // 병해충 상세정보 리스트
	) {
		@Override
		public String toString() {
			return "IntegratedSearchResponse{" +
				"buildTime='" + buildTime + '\'' +
				", displayCount='" + displayCount + '\'' +
				", startPoint=" + startPoint +
				", totalCount=" + totalCount +
				", diseaseDetails=" + diseaseDetails +
				'}';
		}

		/**
		 * 병해충 상세정보
		 */
		public record DiseaseDetail(
			@JsonProperty("oprName") String oprName,    // 영문명(학명)
			@JsonProperty("divName") String divName,        // 병해충 구분명
			@JsonProperty("divCode") String divCode,        // 병해충 구분코드
			@JsonProperty("cropName") String cropName,        // 작물명
			@JsonProperty("thumbImg") String thumbImg,        // 썸네일 이미지
			@JsonProperty("korName") String korName,        // 한글명
			@JsonProperty("cropCode") String cropCode,        // 작물코드
			@JsonProperty("detailUrl") String detailUrl    // 상세정보 URL

		) {
			@Override
			public String toString() {
				return "DiseaseDetail{" +
					"englishName='" + oprName + '\'' +
					", divName='" + divName + '\'' +
					", divCode='" + divCode + '\'' +
					", cropName='" + cropName + '\'' +
					", thumbImg='" + thumbImg + '\'' +
					", korName='" + korName + '\'' +
					", cropCode='" + cropCode + '\'' +
					", detailUrl='" + detailUrl + '\'' +
					'}';
			}
		}
	}
}
