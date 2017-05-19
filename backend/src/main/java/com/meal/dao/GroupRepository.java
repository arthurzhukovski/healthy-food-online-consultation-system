package com.meal.dao;

import com.meal.entity.GroupEntity;
import com.meal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<GroupEntity, Integer> {


  @Query("select u " +
          "from UserEntity u " +
          "join GroupEntity g on u.groupId = g.id " +
          "where g.id IN :groupsId")
  Iterable<UserEntity> findUsersByGroupsId(@Param("groupsId") List<Integer> groupsId);
  Iterable<GroupEntity> findByCoachId(@Param("coachId") int coachId);

  @Query("select count(u) " +
          "from UserEntity u " +
          "join GroupEntity g on u.groupId = g.id " +
          "where g.id = :id")
  int getUsers(@Param("id") int id);

  @Query("select count(r) " +
          "from GroupEntity g " +
          "join UserEntity u on u.groupId = g.id " +
          "join ReportEntity r on r.user.id = u.id " +
          "where g.id = :id")
  int getReports(@Param("id") int id);

  @Query("select u.login " +
          "from GroupEntity g " +
          "join UserEntity u on u.id = g.coach.id " +
          "where g.id = :id")
  String getCoach(@Param("id") int id);
}
