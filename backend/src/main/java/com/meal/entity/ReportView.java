package com.meal.entity;

import com.meal.service.ReportService;
import com.meal.service.UserService;

public class ReportView {
  ReportService service;

  public ReportView(ReportService service) {
    this.service = service;
  }

  public String getBad() {
    return service.getBad();
  }

  public String getGood() {
    return service.getGood();
  }
  public String getNeutral() {
    return service.getNeutral();
  }
  public String getCount() {
    return service.getCount();
  }
  public String getMarkedCount() {
    return service.getMarkedCount();
  }
}

