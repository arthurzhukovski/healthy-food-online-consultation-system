import {Config} from '../../Config';
import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { UserService } from '../index';
import { Group } from '../../models/index';

@Injectable()
export class ArticleService {
    constructor(private http: Http) { }

    getAll(pageSize, pageNumber) {
        return this.http.get(Config.BASE_API_URL + '/articles/' + pageSize + '/' + pageNumber, UserService.jwt()).map((response: Response) => response.json());
    }

    create(article: any) {
        return this.http.post(Config.BASE_API_URL + '/articles/', article,  UserService.jwt()).map((response: Response) => response.json());
    }

    update(article: any) {
        return this.http.put(Config.BASE_API_URL + '/articles/', article,  UserService.jwt()).map((response: Response) => response.json());
    }
    delete(id: number) {
        return this.http.delete(Config.BASE_API_URL + '/articles/' + id,  UserService.jwt()).map((response: Response) => response.status);
    }

}