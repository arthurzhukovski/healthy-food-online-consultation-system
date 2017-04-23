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
        return this.http.post('http://192.168.43.148:8080/login', JSON.stringify({ login: login, password: password }), headers)
            .map((response: Response) => {
                // login successful if there's a jwt token in the response
                let loginResponse = response.json();
                if (loginResponse.user && loginResponse.token) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
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