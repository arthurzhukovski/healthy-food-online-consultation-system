package com.meal.dao;

import com.meal.entity.UserDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDataDao extends JpaRepository<UserDataEntity, Integer> {
}
