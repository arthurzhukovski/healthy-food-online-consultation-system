package com.meal.service.impl;

import com.meal.dao.CommentRepository;
import com.meal.dao.ReportRepository;
import com.meal.entity.CommentEntity;
import com.meal.entity.ReportEntity;
import com.meal.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReportServiceImpl implements ReportService {

  private ReportRepository reportRepository;
  private CommentRepository commentRepository;
  private Date dateTime;

  public ReportServiceImpl(ReportRepository reportRepository, CommentRepository commentRepository) {
    this.reportRepository = reportRepository;
    this.commentRepository = commentRepository;
    this.dateTime = new Date();
  }

  public ReportEntity findOne(int id) {
    return reportRepository.findOne(id);
  }

  public ReportEntity createReport(ReportEntity reportEntity) {
    if(!isReportValid(reportEntity)){
      return null;
    }
    reportEntity.setCreatedAt(new java.sql.Timestamp(dateTime.getTime()));
    return reportRepository.save(reportEntity);
  }

  public ReportEntity updateReport(ReportEntity reportEntity) {
    return reportRepository.save(reportEntity);
  }

  public void deleteReport(int id) {
    reportRepository.delete(id);
  }

  public Iterable<ReportEntity> getReportsByUserId(int userId) {
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

  private boolean isReportValid(ReportEntity reportEntity){
    if(reportEntity == null){
      return false;
    }

    if (reportEntity.getContent().isEmpty()){
      return false;
    }

    return true;
  }
}
