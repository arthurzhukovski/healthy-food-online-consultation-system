package com.meal.entity;

import com.meal.service.ArticleService;
import com.meal.service.GroupService;

public class ArticleView {
  ArticleService service;
  ArticleEntity article;

  public GroupView(ArticleEntity article, ArticleService service) {
    this.article = article;
    this.service = service;
  }

  public String getCount() {
    return String.valueOf(service.getCount());
  }