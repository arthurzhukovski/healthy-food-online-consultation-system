package com.meal.service;

import com.meal.entity.ReportEntity;

public interface ReportService {

  Iterable<ReportEntity> findAll();
  ReportEntity findOne(int id);
  ReportEntity createReport(ReportEntity reportEntity);
  ReportEntity updateReport(ReportEntity reportEntity);
  void deleteReport(int id);
  Iterable<ReportEntity> getReportsByUserId(int userId);
  Iterable<ReportEntity> getReportsByGroupId(int userId);
  Iterable<ReportEntity> findByUsersId(int[] usersId);
}
