import {Config} from '../../Config';
import { Injectable } from '@angular/core';
import { Http, Headers, Response, RequestOptions } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'

@Injectable()
export class AuthenticationService {
    constructor(private http: Http) { }

    login(login: string, password: string) {
        let headers = new RequestOptions({
            headers: new Headers({ 'Content-Type': 'application/json'})
        });
        return this.http.post(Config.BASE_API_URL + '/login', JSON.stringify({ login: login, password: password }), headers)
            .map((response: Response) => {

                let loginResponse = response.json();
                if (loginResponse.user && loginResponse.token) {

                    localStorage.setItem('currentUser', JSON.stringify(loginResponse.user));
                    localStorage.setItem('token', JSON.stringify(loginResponse.token.token));
                }
            });
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }
}