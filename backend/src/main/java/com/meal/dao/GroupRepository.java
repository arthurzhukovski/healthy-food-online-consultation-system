package com.meal.dao;

import com.meal.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<GroupEntity, Integer> {


  @Query("select g " +
          "from GroupEntity g " +
          "join UserEntity u on u.groupId = g.id " +
          "where u.id IN :usersId")
  Iterable<GroupEntity> findGroupsByUsersId(@Param("usersId") List<Integer> usersId);
}
