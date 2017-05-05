package com.meal.dao;

import com.meal.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {

  Iterable<ArticleEntity> findByCoachIdOrderByCreatedAtDesc(@Param("coach_id")int coach_id);
  Page<ArticleEntity> findAllByOrderByCreatedAtDesc(Pageable pageRequest);
}
