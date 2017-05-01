import {Config} from '../../Config';
import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

import { User } from '../../models/index';

@Injectable()
export class UserService {
    constructor(private http: Http) { }

    getAll() {
        return this.http.get(Config.BASE_API_URL + '/users', UserService.jwt()).map((response: Response) => response.json());
        //return this.http.get(Config.BASE_API_URL + '/users', UserService.jwt()).map((response: Response) => response.json());
    }

    getById(id: number) {
        return this.http.get('/api/users/' + id, UserService.jwt()).map((response: Response) => response.json());
    }

    create(user: User) {
        let headers = new RequestOptions({
                                            headers: new Headers({ 'Content-Type': 'application/json'})
                                         });
        var response = this.http.post(Config.BASE_API_URL + '/users', user, headers).map((response: Response) => response.json());
        console.dir(response);
        console.dir(user);
        return response;
    }
    getCoaches() {
        return this.http.get(Config.BASE_API_URL + '/users/coach', UserService.jwt()).map((response: Response) => response.json());
    }

    getAllUsersByGroups(groups: any) {
        var groupIds = [];
        for (var i=0; i < groups.length; i++)
            groupIds.push(groups[i].id);

        return this.http.post(Config.BASE_API_URL +'/groups/users', groupIds,  UserService.jwt()).map((response: Response) => response.json());
    }
    update(user: User) {
        return this.http.put(Config.BASE_API_URL +'/users/', user, UserService.jwt()).map((response: Response) => response.json());
    }

    delete(id: number) {
        return this.http.delete('/api/users/' + id, UserService.jwt()).map((response: Response) => response.json());
    }

    public static jwt(noContentType:boolean=false) {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let token = JSON.parse(localStorage.getItem('token'));

        if (currentUser && token) {
            let headers;
            if (noContentType)
                headers = new Headers({ 'Authorization': 'Bearer ' + token });
            else
                headers = new Headers({ 'Authorization': 'Bearer ' + token, 'Content-Type':'application/json'});
            return new RequestOptions({ headers: headers });
        }
    }

    public static getCurentUser():User{
        let user = localStorage.getItem('currentUser');
        if (user) {
            return JSON.parse(user);
        } else
        {
            return null;
        }
    }


}