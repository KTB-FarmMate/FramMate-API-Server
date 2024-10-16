package com.farmmate.crop.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import com.farmmate.crop.entity.Crop;

@DataJpaTest
@ComponentScan(basePackages = "com.farmmate.crop")
class CropRepositoryTest {
	@Autowired
	private CropRepository cropRepository;

	@Test
	void 작물_종류_조회() {
		// given
		String[] names = {"콩", "쌀", "감자"};

		List<Crop> crops = new ArrayList<>();

		for (int i = 0; i < names.length; i++) {
			Crop crop = Crop.of(i + 1L, names[i]);

			crops.add(crop);
		}
		cropRepository.saveAll(crops);

		// when
		List<Crop> savedCrops = cropRepository.findAll();

		// then
		assertThat(savedCrops).hasSize(3); // 총 3개의 작물이 저장되어야 함

		// ID 검증
		assertThat(savedCrops).extracting(Crop::getId)
			.containsExactlyInAnyOrder(1L, 2L, 3L); // 저장된 작물의 ID 검증

		// 이름 검증
		assertThat(savedCrops).extracting(Crop::getName)
			.containsExactlyInAnyOrder("콩", "쌀", "감자"); // 저장된 작물의 이름 검증
	}

}