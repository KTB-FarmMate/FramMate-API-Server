package com.farmmate.external.ncpms.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 병해충 상세정보 응답
 */
public record NcpmsPestDetailResponse(

	@JsonProperty("service") Data data // 병해충 상세정보 응답

) {
	public record Data(
		@JsonProperty("chemicalPrvnbeMth") String chemicalPrvnbeMth, // 화학적 방제 방법
		@JsonProperty("cropName") String cropName,                   // 작물명
		@JsonProperty("sickNameChn") String sickNameChn,             // 병해 이름(중국어)
		@JsonProperty("preventionMethod") String preventionMethod,   // 예방 방법
		@JsonProperty("virusImgList") List<String> virusImgList,     // 바이러스 이미지 리스트
		@JsonProperty("sickNameKor") String sickNameKor,             // 병해 이름(한글)
		@JsonProperty("developmentCondition") String developmentCondition, // 발달 조건
		@JsonProperty("symptoms") String symptoms,                   // 증상
		@JsonProperty("etc") String etc,                             // 기타
		@JsonProperty("virusList") List<String> virusList,           // 바이러스 리스트
		@JsonProperty("imageList") List<ImageDetail> imageList,      // 이미지 상세 정보 리스트
		@JsonProperty("sickNameEng") String sickNameEng,             // 병해 이름(영어)
		@JsonProperty("biologyPrvnbeMth") String biologyPrvnbeMth    // 생물학적 방제 방법
	) {
		/**
		 * 이미지 상세 정보
		 */
		public record ImageDetail(
			@JsonProperty("image") String image,                     // 이미지 URL
			@JsonProperty("iemSpchcknCode") String iemSpchcknCode,   // 검사 코드
			@JsonProperty("iemSpchcknNm") String iemSpchcknNm,       // 검사 이름
			@JsonProperty("imageTitle") String imageTitle            // 이미지 제목
		) {
		}
	}
}
