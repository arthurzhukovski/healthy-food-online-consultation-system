package com.meal.entity;

import com.meal.service.ArticleService;
import com.meal.service.GroupService;
import com.meal.service.UserService;

public class ArticleView {
  ArticleService service;
  UserEntity coach;

  public ArticleView(UserEntity coach, ArticleService service) {
    this.coach = coach;
    this.service = service;
  }

  public String getCount() {
    return String.valueOf(service.getCount(coach.getId()));
  }

  public String getCoach() {
    return String.valueOf(service.getCoach(coach.getId()));
  }

  public String getLastPub() {
    return String.valueOf(service.getLastPub(coach.getId()));
  }

}