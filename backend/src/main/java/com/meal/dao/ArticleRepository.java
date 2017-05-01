package com.meal.dao;

import com.meal.entity.ArticleEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {

  Iterable<ArticleEntity> findByCoachId(@Param("coach_id")int coach_id);
}
