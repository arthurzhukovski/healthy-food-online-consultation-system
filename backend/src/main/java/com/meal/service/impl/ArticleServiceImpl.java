package com.meal.service.impl;

import com.meal.dao.ArticleRepository;
import com.meal.entity.ArticleEntity;
import com.meal.service.ArticleService;

public class ArticleServiceImpl implements ArticleService {

  private ArticleRepository articleRepository;

  public ArticleServiceImpl(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  public ArticleEntity findOne(int id) {
    return articleRepository.findOne(id);
  }

  public ArticleEntity createArticle(ArticleEntity article) {
    return articleRepository.save(article);
  }

  public ArticleEntity updateArticle(ArticleEntity article) {
    return articleRepository.save(article);
  }

  public void deleteArticle(int id) {
    articleRepository.delete(id);
  }

  public Iterable<ArticleEntity> findByPage(int page) {
    return null;
  }

  public Iterable<ArticleEntity> getArticlesByCoachId(int id) {
    return null;
  }
}
