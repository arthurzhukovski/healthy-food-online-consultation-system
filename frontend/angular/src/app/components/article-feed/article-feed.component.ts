import { Component, OnInit } from '@angular/core';

import { UserService, AlertService } from '../../services/index';
import { User } from '../../models/index';
import  {GroupSelectComponent} from  '../group-select/index';
import {Article} from '../../models/article';
import {ArticleService} from "../../services/article/article.service";

@Component({
    moduleId: module.id,
    selector: 'article-feed',
    templateUrl: 'article-feed.component.html',
    styleUrls: ['article-feed.component.scss']
})

export class ArticleFeedComponent {
    private articles: Article[] = [];
    private totalAmount: number = 0;
    private articleAmountPerPage = 3;
    private currentPage = 0;
    constructor(private userService: UserService, private alertService: AlertService, private articleService: ArticleService) {

    }
    ngOnInit(){
        this.loadArticles(this.articleAmountPerPage, this.currentPage);
    }
    private loadArticles(amount, page) {
        this.articleService.getAll(amount, page).subscribe(
            data => {
                console.log(data);
                this.articles = data.content;
                this.totalAmount = data.totalElements;
            },
            error => {
                this.alertService.error(error);
            });
    }

    loadMore(){
        if (this.totalAmount > this.articles.length){
            this.articleAmountPerPage += 3;
            this.loadArticles(this.articleAmountPerPage, this.currentPage);
        }
    }

}