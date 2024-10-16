package com.farmmate.crop.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.farmmate.crop.entity.Crop;

public interface CropRepository extends JpaRepository<Crop, Integer> {
}
