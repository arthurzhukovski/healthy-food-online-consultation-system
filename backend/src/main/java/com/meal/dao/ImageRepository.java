package com.meal.dao;

import com.meal.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {
  ImageEntity findByReportId(@Param("report_id") int report_id);
}
