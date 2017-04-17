package com.meal.service;

import com.meal.entity.CommentEntity;
import com.meal.entity.Report;

public interface ReportService {

  Report findOne(int id);
  Report createReport(Report report);
  Report updateReport(Report report);
  void deleteReport(int id);
  Iterable<Report> getReportsByUserId(int userId);

  CommentEntity findComment(int id);
  CommentEntity findCommentByReportId(int id);
  CommentEntity createComment(CommentEntity comment);
  CommentEntity updateComment(CommentEntity comment);
  void deleteComment(int id);

}
