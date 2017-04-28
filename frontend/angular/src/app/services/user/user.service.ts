import {Config} from '../../Config';
import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

import { User } from '../../models/index';

@Injectable()
export class UserService {
    constructor(private http: Http) { }

    getAll() {
        return this.http.get(Config.BASE_API_URL + '/users', UserService.jwt()).map((response: Response) => JSON.parse('[{"groupId":"2", "id":7,"name":"Lenny","surname":"Carl","email":"lennyCarl@yandex.ru","confirmed":0,"registeredAt":1492355585000,"stage":0,"role":"USER","login":"Roman"},{"groupId":"3","id":11,"name":"123","surname":"123","email":"123","confirmed":0,"registeredAt":1492783626000,"stage":0,"role":"USER","login":"123"}]') );
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
    getAllUsersByGroups(groups: any) {
        var groupIds = [];
        for (var i=0; i < groups.length; i++)
            groupIds.push(groups[i].id);
        return this.http.get('/', UserService.jwt()).map(
            (response: Response) =>
                (
                    JSON.parse('[{"id":"1","groupId":"1","name":"Имя 1", "surname": "Фамилия 1", "email": "test@mail.test"},' +
                        '{"id":"2","groupId":"2","name":"Имя 2", "surname": "Фамилия 2", "email": "test@mail.test"},' +
                        '{"id":"3","groupId":"3","name":"Имя 3", "surname": "Фамилия 3", "email": "test@mail.test"}]')
                )
        );
        //return this.http.post(Config.BASE_API_URL +'/users/groups', groupIds,  UserService.jwt()).map((response: Response) => response.json());
    }
    update(user: User) {
        return this.http.put('/api/users/' + user.id, user, UserService.jwt()).map((response: Response) => response.json());
    }

    delete(id: number) {
        return this.http.delete('/api/users/' + id, UserService.jwt()).map((response: Response) => response.json());
    }

    public static jwt() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let token = JSON.parse(localStorage.getItem('token'));

        if (currentUser && token) {
            let headers = new Headers({ 'Authorization': 'Bearer ' + token, 'Content-Type':'application/json' });
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