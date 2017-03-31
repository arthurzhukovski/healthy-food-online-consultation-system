import { Component, OnInit } from '@angular/core';
import {UserTableDataService} from "../../services/user-table-data/user-table-data.service";
import {UserDataItemComponent} from "../user-data-item/user-data-item.component";


@Component({
  selector: 'app-user-table',
  providers: [UserTableDataService],
  templateUrl: 'user-table.component.html',
  styleUrls: ['user-table.component.scss']
})



export class UserTableComponent implements OnInit {
  errorMessage: string;
  users: UserDataItemComponent[];
  mode = 'Observable';

  constructor (private userDataService: UserTableDataService) {}

  ngOnInit() { this.getUsers(); }
  getUsers() {
    this.userDataService.getUsers()
        .subscribe(

            users => this.users = users,
            error =>  this.errorMessage = <any>error);
    console.log(this.users);
  }
}
