package com.meal.service;

import com.meal.entity.ArticleEntity;
import com.meal.entity.ArticleView;
import com.meal.entity.UserEntity;
import com.meal.service.Exception.ServiceException;
import com.meal.service.impl.model.entity.ViewerFactoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public interface ArticleService {

  Page<ArticleEntity> findAll(int page, int pageSize);
  ArticleEntity findOne(int id);
  ArticleEntity createArticle(ArticleEntity topic) throws ServiceException;
  ArticleEntity updateArticle(ArticleEntity topic);
  void deleteArticle(int id);

  String getCount(int id);
  String getCountPerMonth(int id);
  String getLastPub(int id);
  void createDoc(String type,
                 HttpServletResponse response,
                 List<ArticleView> entities,
                 ViewerFactoryInterface viewerFactory,
                 boolean isEncrypt);

  Iterable<ArticleEntity> getArticlesByCoachId(int id);

}
