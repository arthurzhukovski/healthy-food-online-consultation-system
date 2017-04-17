package com.meal.dao;

import com.meal.entity.UserDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserDataEntity, Integer> {
}
