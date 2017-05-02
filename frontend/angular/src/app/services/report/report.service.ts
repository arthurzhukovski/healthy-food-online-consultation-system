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
        return this.http.get(Config.BASE_API_URL + '/reports/user/' + userId, UserService.jwt()).map((response: Response) => response.json());
    }
    getAllByUsers(users: any) {
        var userIds = [];
        for (var i=0; i < users.length; i++)
            userIds.push(users[i].id);
        return this.http.post(Config.BASE_API_URL +'/reports/users', userIds,  UserService.jwt()).map((response: Response) => response.json());
    }

    uploadImage(imageFile: any){
        var data = new FormData();
        data.append('file', imageFile);
        return this.http.post(Config.BASE_API_URL +'/report/image', data,  UserService.jwt(true)).map((response: Response) => response.json());
    }
    create(reportData: any) {
        let reportObj = {user: {id: reportData.userId}, content: reportData.text, imageId:reportData.imageId};
        console.log(reportObj);
        return this.http.post(Config.BASE_API_URL + '/reports/', reportObj, UserService.jwt()).map((response: Response) => response.json());
    }
    update(report: any) {
        console.log(report);
        return this.http.put(Config.BASE_API_URL + '/reports/', report, UserService.jwt()).map((response: Response) => response.json());
    }
    rateReport(report: any) {
        console.log(report);
        return this.http.put(Config.BASE_API_URL + '/reports/comment', report, UserService.jwt()).map((response: Response) => response.json());
    }

    delete(id: number) {
        return this.http.delete(Config.BASE_API_URL + '/reports/' + id, UserService.jwt()).map((response: Response) => response.json());
    }


}