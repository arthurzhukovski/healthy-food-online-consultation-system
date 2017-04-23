package com.meal.dao;

import com.meal.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReportRepository extends JpaRepository<ReportEntity, Integer> {

  Iterable<ReportEntity> findByUserId(@Param("userId") int userId);


//  @Query("select r.id, r.content, r.created_at, " +
//          "r.user_id from mealdb.group g join member m on g.id = m.group_id join " +
//          "user u on u.id = m.id join report r on u.id = r.user_id where g.id = :groupId")
//  Iterable<ReportEntity> findByGroupId(@Param("groupId") int groupId);
  
}
