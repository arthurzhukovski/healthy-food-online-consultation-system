package com.meal.service.impl;

import com.meal.dao.ArticleRepository;;
import com.meal.dao.UserRepository;
import com.meal.entity.ArticleEntity;
import com.meal.entity.ArticleView;
import com.meal.entity.UserEntity;
import com.meal.service.ArticleService;
import com.meal.service.Exception.ServiceException;
import com.meal.service.impl.model.entity.ViewerFactoryInterface;
import com.meal.utils.HelpUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

  private ArticleRepository articleRepository;
  private UserRepository userRepository;
  private final Date dateTime;
  private final int MAX_TITLE_LENGTH = 255;
  private final int MAX_CONTENT_LENGTH = 1000000;


  @Override
  public String getCount(int id) {
    return String.valueOf(userRepository.findCoachArticles(id));
  }

  @Override
  public String getCoach(int id) {
    return String.valueOf(userRepository.findOne(id).getLogin());
  }


  @Override
  public String getLastPub(int id) {
    List<Timestamp> t = userRepository.findLastReport(id);
    if(t.isEmpty()) {
      return "отсутствует";
    } else {
      return String.valueOf(t.get(0));
    }
  }


  public void createDoc(String type,
                        HttpServletResponse response,
                        List<ArticleView> entities,
                        ViewerFactoryInterface viewerFactory,
                        boolean isEncrypt) {

    DocumentBuilder<ArticleView> documentBuilder = new DocumentBuilder<>()
            .setModelViewer(viewerFactory.create())
            .setDocumentType(DocumentType.of(type))
            .setProtectedFromCopy(isEncrypt);

    try {
      documentBuilder.writeToResponse(entities, response);
    } catch(Exception ex) {
      new ServiceException(ex);
    }
  }

  public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository) {
    this.articleRepository = articleRepository;
    this.userRepository = userRepository;
    this.dateTime = new Date();
  }

  public ArticleEntity findOne(int id) {
    if(id < 0){
      throw new ServiceException("invalid id");
    }
    return articleRepository.findOne(id);
  }

  @Transactional
  public ArticleEntity createArticle(ArticleEntity article) throws ServiceException {
    validateArticle(article);
    article.setCreatedAt(new java.sql.Timestamp(dateTime.getTime()));
    try {
      return articleRepository.save(article);
    } catch (Exception e) {
      throw new ServiceException(e);
    }
  }

  @Transactional
  public ArticleEntity updateArticle(ArticleEntity article) {
    Assert.notNull(article);
    ArticleEntity oldArticle = articleRepository.findOne(article.getId());
    Assert.notNull(oldArticle);
    updateArticleFields(oldArticle, article);
    try {
      return articleRepository.save(article);
    } catch (Exception e) {
      throw new ServiceException(e);
    }

  }

  public void deleteArticle(int id) {
    if(id < 0){
      throw new ServiceException("invalid id");
    }
    try {
      articleRepository.delete(id);
    } catch (Throwable e) {
      throw new ServiceException("Bad Request");
    }

  }
//////////////
////////////////////////
  public Page<ArticleEntity> findAll(int page, int pageSize) {
    if(page < 0 || pageSize <= 0) {
      throw new ServiceException("invalid page parametres");
    }
    PageRequest pageRequest = new PageRequest(page, pageSize, Sort.Direction.DESC, "createdAt");
    try {
      return articleRepository.findAll(pageRequest);
    } catch (Exception e) {
      throw new ServiceException(e);
    }
  }

  public Iterable<ArticleEntity> getArticlesByCoachId(int id) {
    if(id < 0){
      throw new ServiceException("invalid coach id");
    }
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
