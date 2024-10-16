package com.farmmate.crop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Crop {
	@Id
	private Integer id;
	private String name;

	@Builder(access = AccessLevel.PRIVATE)
	private Crop(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public static Crop of(Integer id, String name) {
		return Crop.builder()
			.id(id)
			.name(name)
			.build();
	}
}
