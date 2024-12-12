package com.farmmate.disease.dto.response;

import java.util.List;

import com.farmmate.external.ncpms.vo.DiseaseDetailVo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(title = "병해충 상세정보 응답")
public record DiseaseDetailResponse(
	@Schema(description = "화학적 방제 방법", defaultValue = "화학적 방제 방법") String chemicalPrvnbeMth, // 화학적 방제 방법
	@Schema(description = "작물명", defaultValue = "쌀") String cropName,                   // 작물명
	@Schema(description = "병해 이름(중국어)", defaultValue = "병해 이름(중국어)") String sickNameChn,             // 병해 이름(중국어)
	@Schema(description = "예방 방법", defaultValue = "예방 방법") String preventionMethod,   // 예방 방법
	@Schema(description = "병해 이름(한글)", defaultValue = "병해 이름(한글)") String sickNameKor,             // 병해 이름(한글)
	@Schema(description = "발달 조건", defaultValue = "발달 조건") String developmentCondition, // 발달 조건
	@Schema(description = "증상", defaultValue = "증상") String symptoms,                   // 증상
	@Schema(description = "기타", defaultValue = "기타") String etc,                             // 기타
	@Schema(description = "바이러스 리스트", defaultValue = "바이러스 리스트") List<String> virusList,           // 바이러스 리스트
	@Schema(description = "병해 이름(영어)", defaultValue = "병해 이름(영어)") String sickNameEng,             // 병해 이름(영어)
	@Schema(description = "생물학적 방제 방법", defaultValue = "생물학적 방제 방법") String biologyPrvnbeMth,    // 생물학적 방제 방법
	@Schema(description = "바이러스 이미지 리스트") List<String> virusImgList,     // 바이러스 이미지 리스트
	@Schema(description = "이미지 상세 정보 리스트") List<ImageDetail> imageList // 이미지 상세 정보 리스트
) {

	public static DiseaseDetailResponse fromVo(DiseaseDetailVo vo) {
		return DiseaseDetailResponse.builder()
			.chemicalPrvnbeMth(vo.chemicalPrvnbeMth())
			.cropName(vo.cropName())
			.sickNameChn(vo.sickNameChn())
			.preventionMethod(vo.preventionMethod())
			.virusImgList(vo.virusImgList())
			.sickNameKor(vo.sickNameKor())
			.developmentCondition(vo.developmentCondition())
			.symptoms(vo.symptoms())
			.etc(vo.etc())
			.virusList(vo.virusList())
			.imageList(vo.imageList().stream()
				.map(image -> new ImageDetail(image.image(), image.iemSpchcknCode(), image.iemSpchcknNm(),
					image.imageTitle()))
				.toList())
			.sickNameEng(vo.sickNameEng())
			.biologyPrvnbeMth(vo.biologyPrvnbeMth())
			.build();
	}

	@Schema(title = "이미지 상세 정보")
	public record ImageDetail(
		@Schema(description = "이미지 URL", defaultValue = "이미지 URL") String image,                     // 이미지 URL
		@Schema(description = "검사 코드", defaultValue = "검사 코드") String iemSpchcknCode,   // 검사 코드
		@Schema(description = "검사 이름", defaultValue = "검사 이름") String iemSpchcknNm,       // 검사 이름
		@Schema(description = "이미지 제목", defaultValue = "이미지 제목") String imageTitle            // 이미지 제목
	) {

	}
}
