package com.meal.dao;

import com.meal.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupDao extends JpaRepository<GroupEntity, Integer> {
}
