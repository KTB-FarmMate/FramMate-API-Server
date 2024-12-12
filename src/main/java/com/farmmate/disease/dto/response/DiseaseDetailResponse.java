package com.farmmate.disease.dto.response;

import java.util.List;

import com.farmmate.external.ncpms.vo.DiseaseDetailVo;

import lombok.Builder;

@Builder
public record DiseaseDetailResponse(
	String chemicalPrvnbeMth, // 화학적 방제 방법
	String cropName,                   // 작물명
	String sickNameChn,             // 병해 이름(중국어)
	String preventionMethod,   // 예방 방법
	List<String> virusImgList,     // 바이러스 이미지 리스트
	String sickNameKor,             // 병해 이름(한글)
	String developmentCondition, // 발달 조건
	String symptoms,                   // 증상
	String etc,                             // 기타
	List<String> virusList,           // 바이러스 리스트
	List<ImageDetail> imageList,      // 이미지 상세 정보 리스트
	String sickNameEng,             // 병해 이름(영어)
	String biologyPrvnbeMth    // 생물학적 방제 방법
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

	public record ImageDetail(
		String image,                     // 이미지 URL
		String iemSpchcknCode,   // 검사 코드
		String iemSpchcknNm,       // 검사 이름
		String imageTitle            // 이미지 제목
	) {

	}
}
