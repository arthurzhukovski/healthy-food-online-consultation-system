import {Config} from '../../Config';
import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { UserService } from '../index';
import { Group } from '../../models/index';

@Injectable()
export class MessageService {
    constructor(private http: Http) { }

    getIncoming(userId: number) {
        return this.http.get(Config.BASE_API_URL + '/messages/incoming/' + userId, UserService.jwt()).map((response: Response) => response.json());
    }

    getOutgoing(userId: number) {
    return this.http.get(Config.BASE_API_URL + '/messages/outgoing/' + userId, UserService.jwt()).map((response: Response) => response.json());
}

create(message: any) {
    return this.http.post(Config.BASE_API_URL + '/messages/', message, UserService.jwt()).map((response: Response) => {
       if (response.status === 200)
           return JSON.parse('{"status":"OK"}');
    });
}
}