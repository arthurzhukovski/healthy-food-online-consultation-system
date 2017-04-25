import {Config} from '../../Config';
import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { UserService } from '../index';
import { Group } from '../../models/index';

@Injectable()
export class GroupService {
    constructor(private http: Http) { }

    getAll() {
        return this.http.get(Config.BASE_API_URL + '/groups', UserService.jwt()).map((response: Response) => response.json());
    }

    getById(id: number) {
        return this.http.get(Config.BASE_API_URL + '/groups/' + id, UserService.jwt()).map((response: Response) => response.json());
    }

    getAllByCoachId(coachId: number) {
        return this.http.get('/', UserService.jwt()).map(
            (response: Response) =>
                (
                    JSON.parse('[{"id":"1", "coachId": "1", "stage": "1", "active": "1", "createdAt": "0000-00-00"},' +
                                '{"id":"2", "coachId": "1", "stage": "2", "active": "1", "createdAt": "0000-00-00"},' +
                                '{"id":"3", "coachId": "1", "stage": "1", "active": "1", "createdAt": "0000-00-00"}]')
                )
        );
        //return this.http.get(Config.BASE_API_URL + '/groups/coach/' + coachId, UserService.jwt()).map((response: Response) => response.json());
    }

    create(group: any) {

    }

    update(group: any) {

    }
    delete(id: number) {

    }

}