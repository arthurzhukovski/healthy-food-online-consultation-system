package com.meal.service;

import com.meal.entity.ReportEntity;
import com.meal.entity.ReportView;
import com.meal.entity.UserEntity;
import com.meal.service.impl.model.entity.ViewerFactoryInterface;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ReportService {

  Iterable<ReportEntity> findAll();
  ReportEntity findOne(int id);
  ReportEntity createReport(ReportEntity reportEntity);
  ReportEntity updateReport(ReportEntity reportEntity);
  ReportEntity commentReport(ReportEntity reportEntity);
  void deleteReport(int id);
  Iterable<ReportEntity> getReportsByUserId(int userId);
  Iterable<ReportEntity> getReportsByGroupId(int userId);
  Iterable<ReportEntity> findByUsersId(int[] usersId);
  String getCount();
  String getMarkedCount();
  String getGood();
  String getNeutral();
  String getBad();
  void createDoc(String type,
                 HttpServletResponse response,
                 List<ReportView> entities,
                 ViewerFactoryInterface viewerFactory,
                 boolean isEncrypt);
}
