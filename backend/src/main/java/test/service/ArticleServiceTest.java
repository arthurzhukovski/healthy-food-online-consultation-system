package test.service;

import com.meal.dao.ArticleRepository;
import com.meal.entity.ArticleEntity;
import com.meal.service.Exception.ServiceException;
import com.meal.service.UserService;
import com.meal.service.impl.ArticleServiceImpl;
import com.meal.service.impl.AuthServiceImpl;
import org.junit.Test;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class ArticleServiceTest {

  @Test
  public void createArticle_articleIsNull_exceptionThrown() {
    ArticleRepository ArticleRepositoryStub = mock(ArticleRepository.class);

    ArticleServiceImpl underTest
            = new ArticleServiceImpl(ArticleRepositoryStub);

    assertThatThrownBy(() -> underTest.createArticle(null)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void createArticle_articleTitleIsNull_exceptionThrown() {
    ArticleRepository ArticleRepositoryStub = mock(ArticleRepository.class);

    ArticleServiceImpl underTest
            = new ArticleServiceImpl(ArticleRepositoryStub);

    assertThatThrownBy(() -> underTest.createArticle(new ArticleEntity())).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createArticle_articleTitleIsEmpty_exceptionThrown() {
    ArticleRepository ArticleRepositoryStub = mock(ArticleRepository.class);

    ArticleServiceImpl underTest
            = new ArticleServiceImpl(ArticleRepositoryStub);

    ArticleEntity article = new ArticleEntity();
    article.setTitle("");
    assertThatThrownBy(() -> underTest.createArticle(article)).isInstanceOf(ServiceException.class);
  }


  @Test
  public void createArticle_articleTitleIsMore_exceptionThrown() {
    ArticleRepository ArticleRepositoryStub = mock(ArticleRepository.class);

    ArticleServiceImpl underTest
            = new ArticleServiceImpl(ArticleRepositoryStub);

    ArticleEntity article = new ArticleEntity();
    article.setTitle("");
    assertThatThrownBy(() -> underTest.createArticle(article)).isInstanceOf(ServiceException.class);
  }


  @Test
  public void createArticle_articleContentIsNull_exceptionThrown() {
    ArticleRepository ArticleRepositoryStub = mock(ArticleRepository.class);

    ArticleServiceImpl underTest
            = new ArticleServiceImpl(ArticleRepositoryStub);

    ArticleEntity article = new ArticleEntity();
    article.setTitle("title");
    assertThatThrownBy(() -> underTest.createArticle(article)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createArticle_articleContentIsEmpty_exceptionThrown() {
    ArticleRepository ArticleRepositoryStub = mock(ArticleRepository.class);

    ArticleServiceImpl underTest
            = new ArticleServiceImpl(ArticleRepositoryStub);

    ArticleEntity article = new ArticleEntity();
    article.setTitle("title");
    article.setContent("");
    assertThatThrownBy(() -> underTest.createArticle(article)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void createArticle_articleContentIsMore_exceptionThrown() {
    ArticleRepository ArticleRepositoryStub = mock(ArticleRepository.class);

    ArticleServiceImpl underTest
            = new ArticleServiceImpl(ArticleRepositoryStub);

    ArticleEntity article = new ArticleEntity();
    article.setTitle("title");
    article.setContent("");
    assertThatThrownBy(() -> underTest.createArticle(article)).isInstanceOf(ServiceException.class);
  }


  @Test
  public void updateArticle_articleIsNull_exceptionThrown() {
    ArticleRepository ArticleRepositoryStub = mock(ArticleRepository.class);

    ArticleServiceImpl underTest
            = new ArticleServiceImpl(ArticleRepositoryStub);

    assertThatThrownBy(() -> underTest.updateArticle(null)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void findAll_pageIsLessThanZero_exceptionThrown() {
    ArticleRepository ArticleRepositoryStub = mock(ArticleRepository.class);

    ArticleServiceImpl underTest
            = new ArticleServiceImpl(ArticleRepositoryStub);

    assertThatThrownBy(() -> underTest.findAll(-1, 1)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void findAll_pageSizeIsLessThanZero_exceptionThrown() {
    ArticleRepository ArticleRepositoryStub = mock(ArticleRepository.class);

    ArticleServiceImpl underTest
            = new ArticleServiceImpl(ArticleRepositoryStub);

    assertThatThrownBy(() -> underTest.findAll(1, -1)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void findOne_articleIdIsLessThanZero_exceptionThrown() {
    ArticleRepository ArticleRepositoryStub = mock(ArticleRepository.class);

    ArticleServiceImpl underTest
            = new ArticleServiceImpl(ArticleRepositoryStub);

    assertThatThrownBy(() -> underTest.findOne(-1)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void deleteArticle_articleIdIsLessThanZero_exceptionThrown() {
    ArticleRepository ArticleRepositoryStub = mock(ArticleRepository.class);

    ArticleServiceImpl underTest
            = new ArticleServiceImpl(ArticleRepositoryStub);

    assertThatThrownBy(() -> underTest.deleteArticle(-1)).isInstanceOf(ServiceException.class);
  }

  @Test
  public void getArticlesByCoachId_coachIdIsLessThanZero_exceptionThrown() {
    ArticleRepository ArticleRepositoryStub = mock(ArticleRepository.class);

    ArticleServiceImpl underTest
            = new ArticleServiceImpl(ArticleRepositoryStub);

    assertThatThrownBy(() -> underTest.getArticlesByCoachId(-1)).isInstanceOf(ServiceException.class);
  }



}