package com.meal.service.impl;

import com.meal.dao.CommentRepository;
import com.meal.dao.ReportRepository;
import com.meal.entity.CommentEntity;
import com.meal.entity.Grade;
import com.meal.entity.ReportEntity;
import com.meal.service.ReportService;
import com.meal.utils.HelpUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

  public Iterable<ReportEntity> findAll() { return reportRepository.findAll(); }

  public ReportEntity findOne(int id) {
    return reportRepository.findOne(id);
  }

  public ReportEntity createReport(ReportEntity reportEntity) {
    if(!isReportValid(reportEntity)){
      return null;
    }
    if(reportEntity.getComment() != null){
      return null;
    }
    reportEntity.setCreatedAt(new java.sql.Timestamp(dateTime.getTime()));
    return reportRepository.save(reportEntity);
  }

  public ReportEntity updateReport(ReportEntity report) {
    ReportEntity oldReport = reportRepository.findOne(report.getId());
    if(report.getComment() != null){
      CommentEntity comment = report.getComment();
      if(!isCommentValid(comment)){
        return null;
      }
      comment.setCreatedAt(new java.sql.Timestamp(dateTime.getTime()));
    }
    ReportEntity newReport = updateReportFields(oldReport, report);
    if(newReport == null){
      return null;
    }
    return reportRepository.save(newReport);
  }

  public void deleteReport(int id) {
    reportRepository.delete(id);
  }

  public Iterable<ReportEntity> findByUsersId(int[] usersId) {
    List<Integer> ids = new ArrayList<Integer>();
    for(int i=0; i<usersId.length; i++) {
      ids.add(usersId[i]);
    }
    if (ids != null && !ids.isEmpty()){
      return reportRepository.findByUsersId(ids);
    } else {
      return null;
    }
  }

  public Iterable<ReportEntity> getReportsByUserId(int userId) {
    return reportRepository.findByUserId(userId);
  }
  public Iterable<ReportEntity> getReportsByGroupId(int groupId) {
    return reportRepository.findByGroupId(groupId);
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

  private boolean isCommentValid(CommentEntity commentEntity) {
    if(commentEntity == null){
      return false;
    }

    if(HelpUtils.isNullOrEmpty(commentEntity.getText())){
      return false;
    }

    return true;
  }

  private ReportEntity updateReportFields(ReportEntity oldReport,ReportEntity report){
    if(report.getComment() == null){
      report.setComment(oldReport.getComment());
    }
    if(report.getContent() == null) {
      report.setContent(oldReport.getContent());
    }
    if(report.getGrade() == Grade.EMPTY){
      report.setGrade(oldReport.getGrade());
    }
    if(!isReportValid(report)){
      return null;
    }
    return report;
  }

}
