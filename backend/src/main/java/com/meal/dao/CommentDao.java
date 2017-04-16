package com.meal.dao;

import com.meal.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<CommentEntity, Integer> {
}
