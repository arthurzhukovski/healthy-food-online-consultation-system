package com.meal.dao;

import com.meal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

//  @Query("select u from UserEntity u where u.login = :login")
  UserEntity findByLogin(@Param("login")String login);

}
