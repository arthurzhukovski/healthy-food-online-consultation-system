package com.meal.service.impl;

import com.meal.dao.ArticleDao;
import com.meal.entity.ArticleEntity;
import com.meal.service.ArticleService;

public class ArticleServiceImpl implements ArticleService {

  private ArticleDao articleDao;

  public ArticleServiceImpl(ArticleDao articleDao) {
    this.articleDao = articleDao;
  }

  public ArticleEntity findOne(int id) {
    return articleDao.findOne(id);
  }

  public ArticleEntity createArticle(ArticleEntity article) {
    return articleDao.save(article);
  }

  public ArticleEntity updateArticle(ArticleEntity article) {
    return articleDao.save(article);
  }

  public void deleteArticle(int id) {
    articleDao.delete(id);
  }

  public Iterable<ArticleEntity> findByPage(int page) {
    return null;
  }

  public Iterable<ArticleEntity> getArticlesByCoachId(int id) {
    return null;
  }
}
