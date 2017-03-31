import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {UserDataItemComponent} from "../../components/user-data-item/user-data-item.component";

@Injectable()
export class UserTableDataService {
  private userDataUrl = 'http://localhost:63342/test/user-data.php?_ijt=o72a624rna8drfj5mgi180lj0i';
  users: UserDataItemComponent[];
  constructor(private http:Http) { }
  public getUsers(): Observable<UserDataItemComponent[]> {
    return this.http.get(this.userDataUrl)
        .map(this.extractData)
        .catch(this.handleError)
  }

  private extractData(res: Response) {
    let body = res.json();
    return body || { };
  }
  private handleError (error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    return Observable.throw(errMsg);
  }
}
