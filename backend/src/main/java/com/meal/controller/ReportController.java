package com.meal.controller;

import com.meal.entity.CommentEntity;
import com.meal.entity.ReportEntity;

import com.meal.entity.RoleEnum;
import com.meal.security.Secured;
import com.meal.service.ReportService;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ReportController {

  private final ReportService reportService;

  @Autowired
  public ReportController(ReportService reportService) {
    this.reportService = reportService;
  }

  /*
   GET ALL REPORTS
  */
  @RequestMapping(value="/reports", method = RequestMethod.GET)
  public ResponseEntity<Iterable<ReportEntity>> getReports() {
    Iterable<ReportEntity> reports = reportService.findAll();
    return new ResponseEntity<Iterable<ReportEntity>>(reports, HttpStatus.OK);
  }

  /*
     CREATE REPORT
   */
  //@Secured(RoleEnum.USER)
  @RequestMapping(value="/reports", method = RequestMethod.POST)
  public ResponseEntity<ReportEntity> createReport(@RequestBody ReportEntity reportEntity) {
    ReportEntity createdReport =  reportService.createReport(reportEntity);
    return new ResponseEntity<ReportEntity>(createdReport, HttpStatus.OK);
  }

  /*
    UPDATE REPORT
   */
  @RequestMapping(value="/reports", method = RequestMethod.PUT)
  public ResponseEntity<ReportEntity> updateReport(@RequestBody ReportEntity reportEntity) {
    ReportEntity updatedReport = reportService.updateReport(reportEntity);
    return new ResponseEntity<ReportEntity>(updatedReport, HttpStatus.OK);
  }

  /*
    DELETE REPORT
   */
  @RequestMapping(value="/reports/{id}", method = RequestMethod.DELETE)
  public ResponseEntity deleteReport(@RequestParam int id) {
    reportService.deleteReport(id);
    return new ResponseEntity(HttpStatus.OK);
  }

  /*
    GET REPORT
   */
  @RequestMapping(value="/reports/{id}", method = RequestMethod.GET)
  public ResponseEntity<ReportEntity> getReport(@PathVariable(value = "id") int id) {
    ReportEntity report = reportService.findOne(id);
    return new ResponseEntity<ReportEntity>(report, HttpStatus.OK);
  }

  /*
   GET ALL USER REPORTS
  */
  @RequestMapping(value="/reports/user/{id}", method = RequestMethod.GET)
  public ResponseEntity<Iterable<ReportEntity>> getUserReports(@PathVariable(value = "id") int id) {
    Iterable<ReportEntity> reports = reportService.getReportsByUserId(id);
    return new ResponseEntity<Iterable<ReportEntity>>(reports, HttpStatus.OK);
  }

  /*
   GET ALL GROUP REPORTS
  */
  @RequestMapping(value="/reports/group/{id}", method = RequestMethod.GET)
  public ResponseEntity<Iterable<ReportEntity>> getGroupReports(@PathVariable(value = "id") int id) {
    Iterable<ReportEntity> reports = reportService.getReportsByGroupId(id);
    return new ResponseEntity<Iterable<ReportEntity>>(reports, HttpStatus.OK);
  }

  /*
     CREATE COMMENT
   */
  @RequestMapping(value="/report/{id}/comments", method = RequestMethod.POST)
  public ResponseEntity<CommentEntity> createComment(@RequestBody CommentEntity comment) {
    CommentEntity createdComment =  reportService.createComment(comment);
    return new ResponseEntity<CommentEntity>(createdComment, HttpStatus.OK);
  }

  /*
    UPDATE COMMENT
   */
  @RequestMapping(value="/report/{id}/comments", method = RequestMethod.PUT)
  public ResponseEntity<CommentEntity> updateComment(@RequestBody CommentEntity comment) {
    CommentEntity updatedComment = reportService.updateComment(comment);
    return new ResponseEntity<CommentEntity>(updatedComment, HttpStatus.OK);
  }

  /*
    DELETE COMMENT
   */
  @RequestMapping(value="/report/{id}/comments{commentId}", method = RequestMethod.DELETE)
  public ResponseEntity deleteComment(@RequestParam int commentId) {
    reportService.deleteComment(commentId);
    return new ResponseEntity(HttpStatus.OK);
  }

  /*
    GET COMMENT
   */
  @RequestMapping(value="/report/{id}/comments{commentId}", method = RequestMethod.GET)
  public ResponseEntity<CommentEntity> getComment(@RequestParam int commentId) {
    CommentEntity comment = reportService.findComment(commentId);
    return new ResponseEntity<CommentEntity>(comment, HttpStatus.OK);
  }

//  /*
//   GET ALL COACH COMMENTS
//  */
//  @RequestMapping(value="/reports/comments/coach", method = RequestMethod.GET)
//  public ResponseEntity<Iterable<CommentEntity>> getComments(int id) {
//    Iterable<CommentEntity> reports = reportService.getCommentsByReport(id);
//    return new ResponseEntity<Iterable<CommentEntity>>(reports, HttpStatus.OK);
//  }

//  /*
//   GET ALL USER COMMENTS
//  */
//  @RequestMapping(value="/reports/user/:id", method = RequestMethod.GET)
//  public ResponseEntity<Iterable<CommentEntity>> getComments(int id) {
//    try {
//      Iterable<CommentEntity> reports = commentService.getgetUserComments(id);
//      return new ResponseEntity<Iterable<CommentEntity>>(reports, HttpStatus.OK);
//    } catch(Exception e) { //TODO: exception type
//      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//  }
  
}
