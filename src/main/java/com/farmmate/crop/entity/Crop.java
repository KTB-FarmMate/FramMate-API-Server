package com.farmmate.crop.entity;

import org.springframework.data.domain.Persistable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Crop implements Persistable<Integer> {
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

	@Override
	public boolean isNew() {
		return id == null;
	}
}
