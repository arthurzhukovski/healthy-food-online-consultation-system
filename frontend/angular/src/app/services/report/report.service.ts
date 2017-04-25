import {Config} from '../../Config';
import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { UserService } from '../index';

import { Report } from '../../models/index';

@Injectable()
export class ReportService {
    constructor(private http: Http) { }

    getAll() {
        return this.http.get(Config.BASE_API_URL + '/reports', UserService.jwt()).map((response: Response) => response.json());
    }

    getById(id: number) {
        return this.http.get(Config.BASE_API_URL + 'reports/' + id, UserService.jwt()).map((response: Response) => response.json());
    }

    getAllByUserId(userId: number) {
        return this.http.get('/' /*+ userId*/, UserService.jwt()).map(
            (response: Response) =>
                (
                    JSON.parse('[{"id":"1", "userId": "1", "content": "Я покушал", "grade": "good", "comment": "Хорошо", "createdAt": "0000-00-00"},' +
                                '{"id":"2", "userId": "1", "content": "Я покушал", "grade": "good", "comment": "Молодец!", "createdAt": "0000-00-00"},' +
                                '{"id":"3", "userId": "1", "content": "Я покушал", "grade": "empty", "comment": "", "createdAt": "0000-00-00"}]')
                )
        );
        //return this.http.get(Config.BASE_API_URL + '/reports/user/' + userId, UserService.jwt()).map((response: Response) => response.json());
    }
    getAllByUsers(users: any) {
        var userIds = [];
        for (var i=0; i < users.length; i++)
            userIds.push(users[i].id);
        return this.http.get('/' /*+ userId*/, UserService.jwt()).map(
            (response: Response) =>
                (
                    JSON.parse('[{"id":"1", "userId": "1", "content": "Я покушал", "grade": "good", "comment": "Хорошо", "createdAt": "0000-00-00"},' +
                        '{"id":"2", "userId": "2", "content": "Я покушал", "grade": "good", "comment": "Молодец!", "createdAt": "0000-00-00"},' +
                        '{"id":"3", "userId": "3", "content": "Я покушал", "grade": "empty", "comment": "", "createdAt": "0000-00-00"}]')
                )
        );
        //return this.http.post(Config.BASE_API_URL +'/reports/groups', groupIds,  UserService.jwt()).map((response: Response) => response.json());
    }

    create(report: any) {
        return this.http.get('/' , UserService.jwt()).map(
            (response: Response) =>
                (
                    JSON.parse('{"status": "OK"}')
                )
        );
        //return this.http.post(Config.BASE_API_URL + '/reports/', report, UserService.jwt()).map((response: Response) => response.json());
    }
    update(report: any) {
        return this.http.put(Config.BASE_API_URL + '/reports/' + report.id, report, UserService.jwt()).map((response: Response) => response.json());
    }
    delete(id: number) {
        return this.http.delete(Config.BASE_API_URL + '/reports/' + id, UserService.jwt()).map((response: Response) => response.json());
    }


}