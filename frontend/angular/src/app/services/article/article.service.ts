import {Config} from '../../Config';
import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { UserService } from '../index';
import { Group } from '../../models/index';

@Injectable()
export class GroupService {
    constructor(private http: Http) { }

    getAll() {
        return this.http.get('/', UserService.jwt()).map(
            (response: Response) =>
                (
                    JSON.parse('[{"id":"1", "title": "Hello world", "content": "Content of the article.", "authorName": "Автор Дня", "createdAt": "0000-00-00"},' +
                        '{"id":"2", "title": "Second article", "content": "Content of the second article.", "authorName": "Автор Дня", "createdAt": "0000-00-00"},' +
                        '{"id":"3", "title": "Another one", "content": "Content of the third article.", "authorName": "Автор Дня", "createdAt": "2017-01-01"}]')
                )
        );
        //return this.http.get(Config.BASE_API_URL + '/groups', UserService.jwt()).map((response: Response) => response.json());
    }

    create(article: any) {
        return this.http.get('/', UserService.jwt()).map(
            (response: Response) =>
                (
                    JSON.parse('{"status": "ok"}')
                )
        );
    }

    update(article: any) {

    }
    delete(id: number) {

    }

}