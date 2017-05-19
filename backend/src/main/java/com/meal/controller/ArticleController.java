package com.meal.controller;

import com.google.common.collect.Lists;
import com.meal.entity.*;
import com.meal.security.Secured;
import com.meal.service.ArticleService;
import com.meal.service.UserService;
import com.meal.service.impl.model.entity.ArticleViewFactory;
import com.meal.service.impl.model.entity.UserViewFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

@CrossOrigin
@RestController
public class ArticleController {

  ArticleService articleService;
  UserService userService;

  @Autowired
  public ArticleController(ArticleService articleService, UserService userService) {
    this.articleService = articleService;
    this.userService = userService;
  }

  @Secured({RoleEnum.ADMIN, RoleEnum.USER})
  @RequestMapping(value="/article/stat/{type}", method = RequestMethod.GET)
  public void getArticleStat(
          @PathVariable String type,
          @RequestParam("encrypt")  boolean isEncrypt,
          HttpServletResponse response) {
    List<UserEntity> a =  Lists.newArrayList(userService.findAll());
    List<ArticleView> articles = new LinkedList<>();
    for (UserEntity user:
            a) {
      articles.add(new ArticleView(user, articleService));
    }
    ArticleViewFactory factory = new ArticleViewFactory();
    articleService.createDoc(type, response, articles, factory, isEncrypt);
  }

  /*
     GET ALL ARTICLE
    */
 // @Secured({RoleEnum.ADMIN, RoleEnum.COACH, RoleEnum.USER})
  @RequestMapping(value="/articles/{pageSize}/{page}", method = RequestMethod.GET)
  public ResponseEntity<Page<ArticleEntity>> getArticles(@PathVariable int pageSize, @PathVariable int page) {
    Page<ArticleEntity> articles = articleService.findAll(page, pageSize);
    return new ResponseEntity<Page<ArticleEntity>>(articles, HttpStatus.OK);
  }

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
