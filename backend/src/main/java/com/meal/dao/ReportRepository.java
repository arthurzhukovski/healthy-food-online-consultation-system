package com.meal.dao;

import com.meal.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ReportRepository extends JpaRepository<ReportEntity, Integer> {

  Iterable<ReportEntity> findByUserId(int userId);

  @Query("select r " +
          "from ReportEntity r " +
          "join GroupEntity g on r.user.groupId = g.id " +
          "where g.id = :groupId")
  Iterable<ReportEntity> findByGroupIdOrderByCreatedAtDesc(@Param("groupId") int groupId);


  @Query("select r " +
          "from ReportEntity r " +
          "where r.user.id IN :usersId")
  Iterable<ReportEntity> findByUsersIdOrderByCreatedAtDesc(@Param("usersId") List<Integer> usersId);

}
