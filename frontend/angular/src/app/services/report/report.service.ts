import {Config} from '../../Config';
import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

import { Report } from '../../models/index';

@Injectable()
export class ReportService {
    constructor(private http: Http) { }

    getAll() {
        return this.http.get('/api/users', this.jwt()).map((response: Response) => response.json());
    }

    getById(id: number) {
        return this.http.get('/api/users/' + id, this.jwt()).map((response: Response) => response.json());
    }

    getAllByUserId(userId: number) {
        return this.http.get('/' /*+ userId*/, this.jwt()).map(
            (response: Response) =>
                (
                    JSON.parse('[{"id":"1", "userId": "1", "content": "Я покушал", "grade": "good", "comment": "Хорошо", "createdAt": "0000-00-00"},' +
                                '{"id":"2", "userId": "1", "content": "Я покушал", "grade": "good", "comment": "Молодец!", "createdAt": "0000-00-00"}]')
                )
        );
        //return this.http.get(Config.BASE_API_URL + '/reports/user/' + userId, this.jwt()).map((response: Response) => response.json());
    }

    create(report: Report) {
        return this.http.post('/api/users', report, this.jwt()).map((response: Response) => response.json());
    }
    update(report: Report) {
        return this.http.put('/api/users/' + report.id, report, this.jwt()).map((response: Response) => response.json());
    }
    delete(id: number) {
        return this.http.delete('/api/users/' + id, this.jwt()).map((response: Response) => response.json());
    }

    private jwt() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let token = JSON.parse(localStorage.getItem('token'));

        if (currentUser && token) {
            let headers = new Headers({ 'Authorization': 'Bearer ' + token, 'Content-Type':'application/json' });
            return new RequestOptions({ headers: headers });
        }
    }

}