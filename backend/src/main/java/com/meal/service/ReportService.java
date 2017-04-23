package com.meal.service;

import com.meal.entity.CommentEntity;
import com.meal.entity.ReportEntity;

public interface ReportService {

  ReportEntity findOne(int id);
  ReportEntity createReport(ReportEntity reportEntity);
  ReportEntity updateReport(ReportEntity reportEntity);
  void deleteReport(int id);
  Iterable<ReportEntity> getReportsByUserId(int userId);

  CommentEntity findComment(int id);
  CommentEntity findCommentByReportId(int id);
  CommentEntity createComment(CommentEntity comment);
  CommentEntity updateComment(CommentEntity comment);
  void deleteComment(int id);

}
