package com.meal.dao;

import com.meal.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportDao extends JpaRepository<ReportEntity, Integer> {
}
