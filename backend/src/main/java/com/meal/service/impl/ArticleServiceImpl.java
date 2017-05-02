package com.meal.service.impl;

import com.meal.dao.ArticleRepository;;
import com.meal.entity.ArticleEntity;
import com.meal.service.ArticleService;
import com.meal.service.Exception.ServiceException;
import com.meal.utils.HelpUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

  private ArticleRepository articleRepository;
  private final Date dateTime;

  public ArticleServiceImpl(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
    this.dateTime = new Date();
  }

  public ArticleEntity findOne(int id) {
    return articleRepository.findOne(id);
  }

  public ArticleEntity createArticle(ArticleEntity article) throws ServiceException {
    validateArticle(article);
    article.setCreatedAt(new java.sql.Timestamp(dateTime.getTime()));
    return articleRepository.save(article);
  }

  public ArticleEntity updateArticle(ArticleEntity article) {
    Assert.notNull(article);
    ArticleEntity oldArticle = articleRepository.findOne(article.getId());
    Assert.notNull(oldArticle);
    updateArticleFields(oldArticle, article);
    return articleRepository.save(article);
  }

  public void deleteArticle(int id) {
    articleRepository.delete(id);
  }

  public Page<ArticleEntity> findAll(int page, int pageSize) {
    PageRequest pageRequest = new PageRequest(page, pageSize, Sort.Direction.DESC, "createdAt");
    Page<ArticleEntity> articles = articleRepository.findAll(pageRequest);
    List<ArticleEntity> a = articles.getContent();
    return articles;
  }

  public Iterable<ArticleEntity> getArticlesByCoachId(int id) {
    return articleRepository.findByCoachIdOrderByCreatedAtDesc(id);
  }

  private ArticleEntity updateArticleFields(ArticleEntity oldArticle, ArticleEntity article) {
    if(!HelpUtils.isNullOrEmpty(article.getTitle())) {
      oldArticle.setTitle(article.getTitle());
    }
    if(!HelpUtils.isNullOrEmpty(article.getContent())){
      oldArticle.setContent(article.getContent());
    }
    return oldArticle;
  }

  private void validateArticle(ArticleEntity article) throws ServiceException {
    if(article == null){
      throw new ServiceException("article can't be null");
    }
    Assert.notNull(article.getCoach());
    if(HelpUtils.isNullOrEmpty(article.getTitle())){
      throw new ServiceException("article title is invalid");
    }
    if(HelpUtils.isNullOrEmpty(article.getContent())){
      throw new ServiceException("article content is invalid");
    }
  }
}
