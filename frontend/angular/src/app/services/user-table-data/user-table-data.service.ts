import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {UserDataItemComponent} from "../../components/user-data-item/user-data-item.component";

@Injectable()
export class UserTableDataService {
  private userDataUrl = 'http://localhost:63342/test/user-data.php?_ijt=t768n0dst057lhhusi11uskjoi';
  users: UserDataItemComponent[];
  constructor(private http:Http) { }
  public getUsers(): Observable<UserDataItemComponent[]> {
    return this.http.get(this.userDataUrl)
        .map(this.extractData)

  }

  private extractData(res: Response) {
    let body = res.json();
    return body || { };
  }

}
