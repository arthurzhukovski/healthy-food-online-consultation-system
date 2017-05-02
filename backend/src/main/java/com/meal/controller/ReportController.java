package com.meal.controller;

import com.meal.entity.CommentEntity;
import com.meal.entity.ReportEntity;

import com.meal.entity.RoleEnum;
import com.meal.entity.UserEntity;
import com.meal.security.Secured;
import com.meal.service.ReportService;
import com.meal.service.UserService;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@CrossOrigin
@RestController
public class ReportController {

  private final ReportService reportService;
  private final UserService userService;

  @Autowired
  public ReportController(ReportService reportService, UserService userService) {
    this.reportService = reportService;
    this.userService = userService;
  }

  /*
   GET ALL REPORTS
  */
  @Secured({RoleEnum.ADMIN})
  @RequestMapping(value="/reports", method = RequestMethod.GET)
  public ResponseEntity<Iterable<ReportEntity>> getReports() {
    Iterable<ReportEntity> reports = reportService.findAll();
    return new ResponseEntity<Iterable<ReportEntity>>(reports, HttpStatus.OK);
  }

  /*
     CREATE REPORT
   */
  @Secured({RoleEnum.USER})
  @RequestMapping(value="/reports", method = RequestMethod.POST)
  public ResponseEntity<ReportEntity> createReport(@RequestBody ReportEntity reportEntity) {
    ReportEntity createdReport =  reportService.createReport(reportEntity);
    return new ResponseEntity<ReportEntity>(createdReport, HttpStatus.OK);
  }

  /*
    UPDATE REPORT
   */
  @Secured({RoleEnum.ADMIN})
  @RequestMapping(value="/reports", method = RequestMethod.PUT)
  public ResponseEntity<ReportEntity> updateReport(@RequestBody ReportEntity reportEntity) {
    ReportEntity updatedReport = reportService.updateReport(reportEntity);
    return new ResponseEntity<ReportEntity>(updatedReport, HttpStatus.OK);
  }

  /*
    COMMENT AND RATE REPORT
   */
  @Secured({RoleEnum.ADMIN, RoleEnum.COACH})
  @RequestMapping(value="/reports/comment", method = RequestMethod.PUT)
  public ResponseEntity<ReportEntity> commentReport(@RequestBody ReportEntity reportEntity) {
    ReportEntity updatedReport = reportService.commentReport(reportEntity);
    return new ResponseEntity<ReportEntity>(updatedReport, HttpStatus.OK);
  }

  /*
    DELETE REPORT
   */
  @Secured({RoleEnum.ADMIN})
  @RequestMapping(value="/reports/{id}", method = RequestMethod.DELETE)
  public ResponseEntity deleteReport(@PathVariable(value = "id") int id) {
    reportService.deleteReport(id);
    return new ResponseEntity(HttpStatus.OK);
  }

  /*
    GET REPORT
   */
  @Secured({RoleEnum.ADMIN, RoleEnum.COACH})
  @RequestMapping(value="/reports/{id}", method = RequestMethod.GET)
  public ResponseEntity<ReportEntity> getReport(@PathVariable(value = "id") int id) {
    ReportEntity report = reportService.findOne(id);
    return new ResponseEntity<ReportEntity>(report, HttpStatus.OK);
  }

  /*
   GET ALL USER REPORTS
  */
  @Secured({RoleEnum.USER, RoleEnum.ADMIN, RoleEnum.COACH})
  @RequestMapping(value="/reports/user/{id}", method = RequestMethod.GET)
  public ResponseEntity<Iterable<ReportEntity>> getUserReports(@PathVariable(value = "id") int id,
                                                               @RequestAttribute("user") UserEntity currentUser) {
    userService.hasPermission(id, currentUser, RoleEnum.USER);
    Iterable<ReportEntity> reports = reportService.getReportsByUserId(id);
    return new ResponseEntity<Iterable<ReportEntity>>(reports, HttpStatus.OK);
  }

  /*
   GET ALL GROUP REPORTS
  */
  @Secured({RoleEnum.ADMIN, RoleEnum.COACH})
  @RequestMapping(value="/reports/group/{id}", method = RequestMethod.GET)
  public ResponseEntity<Iterable<ReportEntity>> getGroupReports(@PathVariable(value = "id") int id) {
    Iterable<ReportEntity> reports = reportService.getReportsByGroupId(id);
    return new ResponseEntity<Iterable<ReportEntity>>(reports, HttpStatus.OK);
  }

  /*
   GET REPORTS BY USERS ID
  */
  @Secured({RoleEnum.ADMIN, RoleEnum.COACH})
  @RequestMapping(value="/reports/users", method = RequestMethod.POST)
  public ResponseEntity<Iterable<ReportEntity>> getReportsByUsersId(@RequestBody int[] usersId) {
    Iterable<ReportEntity> reports = reportService.findByUsersId((usersId));
    return new ResponseEntity<Iterable<ReportEntity>>(reports, HttpStatus.OK);
  }
  
}
