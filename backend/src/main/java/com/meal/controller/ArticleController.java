package com.meal.controller;

import com.meal.entity.ArticleEntity;
import com.meal.entity.RoleEnum;
import com.meal.security.Secured;
import com.meal.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ArticleController {

  ArticleService articleService;

  @Autowired
  public ArticleController(ArticleService articleService) {
    this.articleService = articleService;
  }

//  /*
//     GET ALL ARTICLE
//    */
//  @RequestMapping(value="/articles", method = RequestMethod.GET)
//  public ResponseEntity<Iterable<ArticleEntity>> getArticles() {
//    Iterable<ArticleEntity> articles = articleService.findAll();
//    return new ResponseEntity<Iterable<ArticleEntity>>(articles, HttpStatus.OK);
//  }

  /*
     CREATE ARTICLE
   */
  @Secured({RoleEnum.ADMIN, RoleEnum.COACH})
  @RequestMapping(value="/articles", method = RequestMethod.POST)
  public ResponseEntity<ArticleEntity> createArticle(@RequestBody ArticleEntity article) {
    ArticleEntity createdArticle =  articleService.createArticle(article);
    return new ResponseEntity<ArticleEntity>(createdArticle, HttpStatus.OK);
  }

  /*
    UPDATE ARTICLE
   */
  @Secured({RoleEnum.ADMIN, RoleEnum.COACH})
  @RequestMapping(value="/articles", method = RequestMethod.PUT)
  public ResponseEntity<ArticleEntity> updateArticle(@RequestBody ArticleEntity article) {
    ArticleEntity updatedArticle = articleService.updateArticle(article);
    return new ResponseEntity<ArticleEntity>(updatedArticle, HttpStatus.OK);
  }

  /*
    DELETE ARTICLE
   */
  @Secured({RoleEnum.ADMIN})
  @RequestMapping(value="/articles/{id}", method = RequestMethod.DELETE)
  public ResponseEntity deleteArticle(@PathVariable(value = "id") int id) {
    articleService.deleteArticle(id);
    return new ResponseEntity(HttpStatus.OK);
  }

  /*
    GET ARTICLE
   */
  @Secured({RoleEnum.ADMIN, RoleEnum.COACH, RoleEnum.USER})
  @RequestMapping(value="/articles/{id}", method = RequestMethod.GET)
  public ResponseEntity<ArticleEntity> getArticle(@PathVariable(value = "id") int id) {
    ArticleEntity article = articleService.findOne(id);
    return new ResponseEntity<ArticleEntity>(article, HttpStatus.OK);
  }

}
