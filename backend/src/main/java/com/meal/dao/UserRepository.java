package com.meal.dao;

import com.meal.entity.RoleEnum;
import com.meal.entity.UserEntity;
import org.apache.catalina.User;
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

  @Query("select count(u) " +
          "from UserEntity u " +
          "where u.role = 'ADMIN'")
  int findAdminsCount();

  @Query("select count(r) " +
          "from UserEntity u " +
          "join ReportEntity r " +
          "on u.id = r.user.id " +
          "where u.id = :id and " +
          "r.grade = GOOD"
          )
  int getGoodMarksCount(@Param("id") int id);

  @Query("select count(r) " +
          "from UserEntity u " +
          "join ReportEntity r " +
          "on u.id = r.user.id " +
          "where u.id = :id and " +
          "r.grade = BAD"
  )
  int getBadMarksCount(@Param("id") int id);

  @Query("select count(c) " +
          "from UserEntity u " +
          "join CommentEntity c " +
          "on u.id = c.coach.id " +
          "where u.id = :id"
  )
  int getMarksCount(@Param("id") int id);

  @Query("select count(c) " +
          "from CommentEntity c " +
          "join ReportEntity r " +
          "on c.id = r.comment.id " +
          "where c.coach.id = :id and " +
          "r.grade = GOOD"
  )
  int getGoodMarks(@Param("id") int id);


  @Query("select count(g) " +
          "from GroupEntity g " +
          "where g.coach.id = :id"
  )
  int getGroupsCount(@Param("id") int id);

  @Query("select count(u) " +
          "from GroupEntity g " +
          "join UserEntity u " +
          "on u.groupId = g.id" +
          "where g.coach.id = :id"
  )
  int getUsersCount(@Param("id") int id);
}
