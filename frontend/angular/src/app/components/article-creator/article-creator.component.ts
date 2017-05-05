import { Component, OnInit } from '@angular/core';

import { UserService, AlertService } from '../../services/index';
import { User } from '../../models/index';
import {ArticleService} from "../../services/article/article.service";
import {Article} from "../../models/article";

@Component({
    moduleId: module.id,
    selector: 'article-creator',
    templateUrl: 'article-creator.component.html',
    styleUrls: ['article-creator.component.scss']
})

export class ArticleCreatorComponent {
    private newArticle: Article = new Article();
    private currentUser: User;
    constructor(private userService: UserService, private alertService: AlertService, private articleService: ArticleService) {
        this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    }
    ngOnInit(){
        this.newArticle = this.createEmptyArticleWithAuthorId();
    }
    createArticle() {
        console.log(this.newArticle);
        this.articleService.create(this.newArticle).subscribe(
            data => {
                this.alertService.success('Статья добавлена.', true);
                this.newArticle = this.createEmptyArticleWithAuthorId();
            },
            error => {
                this.alertService.error('Не удалось создать статью' + error._body);
            });
    }

    createEmptyArticleWithAuthorId(){
        var article: Article = new Article();
        article.coach.id = this.currentUser.id;
        return article;
    }

}