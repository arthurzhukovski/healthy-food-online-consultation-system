package com.meal.dao;

import com.meal.entity.UserDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserDataRepository extends JpaRepository<UserDataEntity, Integer> {
}
