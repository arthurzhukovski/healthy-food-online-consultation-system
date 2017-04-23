package com.meal.dao;

import com.meal.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReportRepository extends JpaRepository<ReportEntity, Integer> {

  Iterable<ReportEntity> findByUserId(int userId);

  @Query("select r " +
          "from ReportEntity r " +
          "join MemberEntity m on r.userId = m.userId " +
          "join GroupEntity g on m.groupId = g.id " +
          "where g.id = :groupId")

  Iterable<ReportEntity> findByGroupId(@Param("groupId") int groupId);
  
}
