package com.meal.dao;

import com.meal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<UserEntity, Integer> {

  List<UserEntity> findFirstByLogin(String login);

}
