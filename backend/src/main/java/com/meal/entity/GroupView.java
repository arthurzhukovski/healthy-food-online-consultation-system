package com.meal.entity;

import com.meal.service.GroupService;
import com.meal.service.UserService;

public class GroupView {
  GroupService service;
  GroupEntity group;

  public GroupView(GroupEntity group, GroupService service) {
    this.group = group;
    this.service = service;
  }

  public String getId() {
    return String.valueOf(group.getId());
  }
  public String getUsers() {
    return String.valueOf(service.getUsers(group.getId()));
  }
  public String getReports() {
    return String.valueOf(service.getReports(group.getId()));
  }
  public String getCoach() {
    return String.valueOf(service.getCoach(group.getId()));
  }
}