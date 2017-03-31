import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-data-item',
  templateUrl: 'user-data-item.component.html',
  styleUrls: ['user-data-item.component.scss']
})
export class UserDataItemComponent implements OnInit {
  public name;
  public surname;
  public id;
  public login;
  public password;
  public stage;
  public email;
  constructor() { }

  ngOnInit() {
  }

}
