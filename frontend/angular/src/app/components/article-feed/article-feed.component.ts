import {Component, OnInit, ViewChild} from '@angular/core';

import { UserService, AlertService } from '../../services/index';
import { User } from '../../models/index';
import  {GroupSelectComponent} from  '../group-select/index';
import {Article} from '../../models/article';
import {ArticleService} from "../../services/article/article.service";
import {ModalComponent} from "../modal/modal.component";
import {EllipsisPipe} from  "../../pipes/ellipsis.pipe";

@Component({
    moduleId: module.id,
    selector: 'article-feed',
    templateUrl: 'article-feed.component.html',
    styleUrls: ['article-feed.component.scss'],
})

export class ArticleFeedComponent {
    @ViewChild('edit')
    public readonly editArticleModal: ModalComponent;
    @ViewChild('read')
    public readonly readArticleModal: ModalComponent;
    private currentUser: User = new User();
    private articles: Article[] = [];
    private articleToRead: Article = new Article();
    private totalAmount: number = 0;
    private articleAmountPerPage = 3;
    private currentPage = 0;
    private editedArticle: Article = new Article();
    constructor(private userService: UserService, private alertService: AlertService, private articleService: ArticleService) {
        this.refreshLocalStorage();
    }
    ngOnInit(){
        this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
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
                let msg = (error._body != '')? error._body : error;
                this.alertService.error('Ошибка. ' +msg);
            });
    }

    loadMore(){
        if (this.totalAmount > this.articles.length){
            this.articleAmountPerPage += 3;
            this.loadArticles(this.articleAmountPerPage, this.currentPage);
        }
    }

    editArticle(article){
        this.editedArticle = Object.assign({}, article);
        this.editArticleModal.show();
    }
    deleteArticle(id){
        this.articleService.delete(id).subscribe(
            data => {
                this.alertService.success('Статья успешно удалена.');
                this.loadArticles(this.articleAmountPerPage, this.currentPage);
            },
            error => {
                let msg = (error._body != '')? error._body : error;
                this.alertService.error('Ошибка. ' +msg);
            });
    }
    submitEditedArticle(){
        console.log(this.editedArticle);
        this.articleService.update(this.editedArticle).subscribe(
            data => {
                this.alertService.success('Статья обновлена.');
                this.loadArticles(this.articleAmountPerPage, this.currentPage);
                this.editArticleModal.hide();
            },
            error => {
                let msg = (error._body != '')? error._body : error;
                this.alertService.error('Ошибка. ' +msg);
            });
    }

    showArticle(article){
        this.articleToRead = Object.assign({}, article);
        this.readArticleModal.show();
    }

    refreshLocalStorage(){
        var currentUser: User = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser == null)
            return;
        this.userService.getById(currentUser.id).subscribe(
            data =>{
                localStorage.setItem('currentUser', JSON.stringify(data));
                currentUser = JSON.parse(localStorage.getItem("currentUser"));
            },
            error => {
                this.alertService.error('Не удалось загрузить информацию о пользователе. ' + error);
            });
    }
}