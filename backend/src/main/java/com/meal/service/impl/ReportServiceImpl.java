package com.meal.service.impl;

import com.meal.dao.CommentRepository;
import com.meal.dao.ReportRepository;
import com.meal.entity.CommentEntity;
import com.meal.entity.Report;
import com.meal.service.ReportService;

public class ReportServiceImpl implements ReportService {

  private ReportRepository reportRepository;
  private CommentRepository commentRepository;

  public ReportServiceImpl(ReportRepository reportRepository, CommentRepository commentRepository) {
    this.reportRepository = reportRepository;
    this.commentRepository = commentRepository;
  }

  public Report findOne(int id) {
    return reportRepository.findOne(id);
  }

  public Report createReport(Report report) {
    return reportRepository.save(report);
  }

  public Report updateReport(Report report) {
    return reportRepository.save(report);
  }

  public void deleteReport(int id) {
    reportRepository.delete(id);
  }

  public Iterable<Report> getReportsByUserId(int userId) {
    return null;
  }

  public CommentEntity findComment(int id) {
    return commentRepository.findOne(id);
  }

  public CommentEntity findCommentByReportId(int id) {
    return null;
  }

  public CommentEntity createComment(CommentEntity comment) {
    return commentRepository.save(comment);
  }

  public CommentEntity updateComment(CommentEntity comment) {
    return commentRepository.save(comment);
  }

  public void deleteComment(int id) {
    commentRepository.delete(id);
  }
}
