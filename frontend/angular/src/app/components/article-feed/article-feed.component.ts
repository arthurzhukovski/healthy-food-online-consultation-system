import { Component, OnInit } from '@angular/core';

import { UserService, AlertService } from '../../services/index';
import { User } from '../../models/index';
import  {GroupSelectComponent} from  '../group-select/index';
import {Article} from '../../models/article';
import {ArticleService} from "../../services/article/article.service";

@Component({
    moduleId: module.id,
    selector: 'group-assignment',
    templateUrl: 'article-feed.component.html',
    styleUrls: ['article-feed.component.scss']
})

export class ArticleFeedComponent {
    private articles: Article[] = [];
    constructor(private userService: UserService, private alertService: AlertService, private articleService: ArticleService) {

    }
    ngOnInit(){
        this.loadArticles();
    }
    private loadArticles() {
        this.articleService.getAll(1, 10).subscribe(
            data => {
                console.log(data);
                this.articles = data;
            },
            error => {
                this.alertService.error(error);
            });
    }


}