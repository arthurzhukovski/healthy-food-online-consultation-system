package com.meal.service.impl;

import com.meal.dao.CommentDao;
import com.meal.dao.ReportDao;
import com.meal.entity.CommentEntity;
import com.meal.entity.ReportEntity;
import com.meal.service.ReportService;

public class ReportServiceImpl implements ReportService {

  private ReportDao reportDao;
  private CommentDao commentDao;

  public ReportServiceImpl(ReportDao reportDao, CommentDao commentDao) {
    this.reportDao = reportDao;
    this.commentDao = commentDao;
  }

  public ReportEntity findOne(int id) {
    return reportDao.findOne(id);
  }

  public ReportEntity createReport(ReportEntity report) {
    return reportDao.save(report);
  }

  public ReportEntity updateReport(ReportEntity report) {
    return reportDao.save(report);
  }

  public void deleteReport(int id) {
    reportDao.delete(id);
  }

  public Iterable<ReportEntity> getReportsByUserId(int userId) {
    return null;
  }

  public CommentEntity findComment(int id) {
    return commentDao.findOne(id);
  }

  public CommentEntity findCommentByReportId(int id) {
    return null;
  }

  public CommentEntity createComment(CommentEntity comment) {
    return commentDao.save(comment);
  }

  public CommentEntity updateComment(CommentEntity comment) {
    return commentDao.save(comment);
  }

  public void deleteComment(int id) {
    commentDao.delete(id);
  }
}
