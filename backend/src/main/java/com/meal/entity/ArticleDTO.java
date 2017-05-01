package com.meal.entity;

public class ArticleDTO {

  private Iterable<ArticleEntity> article;
  private int currentPage;
  private int pageCount;


  public Iterable<ArticleEntity> getArticle() {
    return article;
  }

  public void setArticle(Iterable<ArticleEntity> article) {
    this.article = article;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public int getPageCount() {
    return pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }

}
