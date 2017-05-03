package com.meal.service.impl;

import com.meal.dao.CommentRepository;
import com.meal.dao.ImageRepository;
import com.meal.dao.ReportRepository;
import com.meal.dao.UserRepository;
import com.meal.entity.*;
import com.meal.service.Exception.ServiceException;
import com.meal.service.ReportService;
import com.meal.utils.HelpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;

import javax.persistence.Basic;
import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

  private ReportRepository reportRepository;
  private CommentRepository commentRepository;
  private UserRepository userRepository;
  private Date dateTime;

  private final int MAX_CONTENT_LENGTH = 255;
  private final int MAX_COMMENT_LENGTH = 10000;

  @Autowired
  public ReportServiceImpl(ReportRepository reportRepository, CommentRepository commentRepository,
                           UserRepository userRepository) {
    this.reportRepository = reportRepository;
    this.commentRepository = commentRepository;
    this.userRepository = userRepository;
    this.dateTime = new Date();

  }

  public Iterable<ReportEntity> findAll() { return reportRepository.findAll(); }

  public ReportEntity findOne(int id) {
    return reportRepository.findOne(id);
  }

  @Transactional
  public ReportEntity createReport(ReportEntity reportEntity) throws ServiceException {
    validateReport(reportEntity);

    if(reportEntity.getComment() != null){
      throw new ServiceException("can't have comment");
    }
    reportEntity.setCreatedAt(new java.sql.Timestamp(dateTime.getTime()));
    reportEntity.setGrade(Grade.EMPTY);
    return reportRepository.save(reportEntity);
  }

  @Transactional
  public ReportEntity updateReport(ReportEntity report) {
    ReportEntity oldReport = reportRepository.findOne(report.getId());
    Assert.notNull(report);
    Assert.notNull(oldReport);
    Assert.notNull(report.getComment());

    CommentEntity comment = report.getComment();
    validateComment(comment);
    comment.setCreatedAt(new java.sql.Timestamp(dateTime.getTime()));
    commentRepository.save(comment);

    ReportEntity newReport = updateReportFields(oldReport, report);
    Assert.notNull(newReport);
    return reportRepository.save(newReport);
  }

  @Transactional
  public ReportEntity commentReport(ReportEntity report) {
    ReportEntity oldReport = reportRepository.findOne(report.getId());

    Assert.notNull(report.getComment());
    CommentEntity comment = report.getComment();
    validateComment(comment);
    comment.setCreatedAt(new java.sql.Timestamp(dateTime.getTime()));
    commentRepository.save(comment);

    oldReport.setGrade(report.getGrade());
    oldReport.setComment(comment);
    Assert.notNull(oldReport);
    return reportRepository.save(oldReport);
  }

  @Transactional
  public void deleteReport(int id) {
    ReportEntity report = reportRepository.findOne(id);
    if(report != null && report.getComment() != null){
      commentRepository.delete(report.getComment().getId());
    }
    reportRepository.delete(id);
  }

  public Iterable<ReportEntity> findByUsersId(int[] usersId) {
    List<Integer> ids = new ArrayList<Integer>();
    for(int i=0; i<usersId.length; i++) {
      ids.add(usersId[i]);
    }
    if (ids != null && !ids.isEmpty()){
      return reportRepository.findByUsersIdOrderByCreatedAtDesc(ids);
    } else {
      return null;
    }
  }

  public Iterable<ReportEntity> getReportsByUserId(int userId) {
    return reportRepository.findByUserId(userId);
  }
  public Iterable<ReportEntity> getReportsByGroupId(int groupId) {
    return reportRepository.findByGroupIdOrderByCreatedAtDesc(groupId);
  }

  private void validateReport(ReportEntity reportEntity) throws ServiceException{
    Assert.notNull(reportEntity);
    if (HelpUtils.isNullOrEmpty(reportEntity.getContent()) || reportEntity.getContent().length() >= MAX_CONTENT_LENGTH){
      throw new ServiceException("report has invalid content");
    }
  }

  private void validateComment(CommentEntity commentEntity) throws ServiceException {
    Assert.notNull(commentEntity);
    if(HelpUtils.isNullOrEmpty(commentEntity.getText()) || commentEntity.getText().length() >= MAX_COMMENT_LENGTH){
      throw new ServiceException("comment has invalid text");
    }
  }

  private ReportEntity updateReportFields(ReportEntity oldReport,ReportEntity report) throws ServiceException{
    if(report.getComment() == null){
      report.setComment(oldReport.getComment());
    }
    if(report.getContent() == null) {
      if(report.getContent().length() >= MAX_CONTENT_LENGTH){
        throw new ServiceException("report has invalid content");
      }
      report.setContent(oldReport.getContent());
    }
    if(report.getGrade() == Grade.EMPTY){
      report.setGrade(oldReport.getGrade());
    }
    validateReport(report);
    report.setUser(oldReport.getUser());
    report.setCreatedAt(oldReport.getCreatedAt());
    return report;
  }

}
