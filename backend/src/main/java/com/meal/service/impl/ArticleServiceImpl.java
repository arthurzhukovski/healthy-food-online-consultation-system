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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;

@Service
public class ArticleServiceImpl implements ArticleService {

  private ArticleRepository articleRepository;
  private final Date dateTime;
  private final int MAX_TITLE_LENGTH = 255;
  private final int MAX_CONTENT_LENGTH = 10000;

  public ArticleServiceImpl(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
    this.dateTime = new Date();
  }

  public ArticleEntity findOne(int id) {
    return articleRepository.findOne(id);
  }

  @Transactional
  public ArticleEntity createArticle(ArticleEntity article) throws ServiceException {
    validateArticle(article);
    article.setCreatedAt(new java.sql.Timestamp(dateTime.getTime()));
    return articleRepository.save(article);
  }

  @Transactional
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
    return articleRepository.findAll(pageRequest);
  }

  public Iterable<ArticleEntity> getArticlesByCoachId(int id) {
    return articleRepository.findByCoachIdOrderByCreatedAtDesc(id);
  }

  private ArticleEntity updateArticleFields(ArticleEntity oldArticle, ArticleEntity article) throws ServiceException {
    if(!HelpUtils.isNullOrEmpty(article.getTitle())) {
      if(article.getTitle().length() >= MAX_TITLE_LENGTH){
        throw new ServiceException("article title is invalid");
      }
      oldArticle.setTitle(article.getTitle());
    }
    if(!HelpUtils.isNullOrEmpty(article.getContent())){
      if(article.getContent().length() >= MAX_CONTENT_LENGTH){
        throw new ServiceException("article title is invalid");
      }
      oldArticle.setContent(article.getContent());
    }
    return oldArticle;
  }

  private void validateArticle(ArticleEntity article) throws ServiceException {
    Assert.notNull(article);
    if(HelpUtils.isNullOrEmpty(article.getTitle()) || article.getTitle().length() >= MAX_TITLE_LENGTH){
      throw new ServiceException("article title is invalid");
    }
    if(HelpUtils.isNullOrEmpty(article.getContent()) || article.getContent().length() >= MAX_CONTENT_LENGTH){
      throw new ServiceException("article content is invalid");
    }
  }
}
