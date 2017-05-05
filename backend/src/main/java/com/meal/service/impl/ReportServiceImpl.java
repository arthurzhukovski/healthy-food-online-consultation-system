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
    if(id < 0){
      throw new ServiceException("invalid id");
    }
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
    try {
      return reportRepository.save(reportEntity);
    } catch (Throwable e) {
      throw new ServiceException("Bad Request");
    }

  }

  @Transactional
  public ReportEntity updateReport(ReportEntity report) {
    Assert.notNull(report);
    ReportEntity oldReport = reportRepository.findOne(report.getId());
    Assert.notNull(oldReport);
    Assert.notNull(report.getComment());

    CommentEntity comment = report.getComment();
    validateComment(comment);
    comment.setCreatedAt(new java.sql.Timestamp(dateTime.getTime()));
    try {
      commentRepository.save(comment);
    } catch (Throwable e) {
      throw new ServiceException("Bad Request");
    }


    ReportEntity newReport = updateReportFields(oldReport, report);
    Assert.notNull(newReport);
    try {
      return reportRepository.save(newReport);
    } catch (Throwable e) {
      throw new ServiceException("Bad Request");
    }

  }

  @Transactional
  public ReportEntity commentReport(ReportEntity report) {
    Assert.notNull(report);
    Assert.notNull(report.getComment());
    CommentEntity comment = report.getComment();
    validateComment(comment);
    try {
      ReportEntity oldReport = reportRepository.findOne(report.getId());
      comment.setCreatedAt(new java.sql.Timestamp(dateTime.getTime()));

      commentRepository.save(comment);

      oldReport.setGrade(report.getGrade());
      oldReport.setComment(comment);
      Assert.notNull(oldReport);
      return reportRepository.save(oldReport);
    } catch (Throwable e) {
      throw new ServiceException("Bad Request");
    }
  }

  @Transactional
  public void deleteReport(int id) {
    if(id < 0){
      throw new ServiceException("invalid report id");
    }
    ReportEntity report = reportRepository.findOne(id);
    if(report != null && report.getComment() != null){
      commentRepository.delete(report.getComment().getId());
    }
    try {
      reportRepository.delete(id);
    } catch (Throwable e) {
      throw new ServiceException("Bad Request");
    }

  }

  public Iterable<ReportEntity> findByUsersId(int[] usersId) {
    if(usersId == null || usersId.length == 0){
      throw new ServiceException("invalid userIds");
    }
    List<Integer> ids = new ArrayList<Integer>();
    for(int i=0; i<usersId.length; i++) {
      ids.add(usersId[i]);
    }
    if (ids != null && !ids.isEmpty()){
      return reportRepository.findByUsersIdOrderByCreatedAtDesc(ids);
    } else {
      throw new ServiceException("invalid user ids");
    }
  }

  public Iterable<ReportEntity> getReportsByUserId(int userId) {
    if(userId < 0){
      throw new ServiceException("invalid user id");
    }
    return reportRepository.findByUserId(userId);
  }
  public Iterable<ReportEntity> getReportsByGroupId(int groupId) {
    if(groupId < 0){
      throw new ServiceException("invalid group id");
    }
    return reportRepository.findByGroupIdOrderByCreatedAtDesc(groupId);
  }

  private void validateReport(ReportEntity reportEntity) throws ServiceException, IllegalArgumentException{
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
