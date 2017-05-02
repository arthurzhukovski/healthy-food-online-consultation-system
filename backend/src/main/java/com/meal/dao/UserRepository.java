package com.meal.dao;

import com.meal.entity.RoleEnum;
import com.meal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

  UserEntity findByLogin(@Param("login") String login);
  UserEntity findByEmail(@Param("email") String email);
  Iterable<UserEntity> findByGroupId(@Param("groupId") int groupId);

  @Query("select u " +
          "from UserEntity u " +
          "where u.role = :role")
  Iterable<UserEntity> findByRole(@Param("role") RoleEnum role);



}
