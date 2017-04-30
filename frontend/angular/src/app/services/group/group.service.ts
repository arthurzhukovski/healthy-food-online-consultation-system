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
        console.log(coachId);
        return this.http.get(Config.BASE_API_URL + '/groups/coach/' + coachId, UserService.jwt()).map((response: Response) => response.json());
    }

    create(group: any) {
        return this.http.put(Config.BASE_API_URL + '/groups/', group, UserService.jwt()).map((response: Response) => response.json());
    }

    update(group: any) {

    }
    delete(id: number) {

    }

}