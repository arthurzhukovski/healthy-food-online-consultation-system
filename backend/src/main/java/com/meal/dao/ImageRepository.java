package com.meal.dao;

import com.meal.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {
  @Query("select i " +
          "from ImageEntity i " +
          "join ReportEntity r on r.imageId = i.id " +
          "where r.id = :report_id")
  ImageEntity findByReportId(@Param("report_id") int report_id);
}
