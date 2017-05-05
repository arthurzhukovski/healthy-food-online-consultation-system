import {Component, OnInit, ViewChild} from '@angular/core';
import {User} from "../../models/user";
import {ModalComponent} from "../modal/modal.component";
import {UserService} from "../../services/user/user.service";
import {AlertService} from "../../services/alert/alert.service";


@Component({
  selector: 'app-user-table',
  templateUrl: 'user-table.component.html',
  styleUrls: ['user-table.component.scss']
})



export class UserTableComponent implements OnInit {
  @ViewChild(ModalComponent)
  public readonly editUserModal: ModalComponent;
  private users: User[] = [];
  mode = 'Observable';
  private selectedUser: User = new User;
  constructor (private userService: UserService, private alertService: AlertService) {

  }

  ngOnInit() { this.loadUsers(); }
  loadUsers() {
    this.userService.getAll()
        .subscribe(
            users => {this.users = users;  console.log(this.users);},
            error =>  this.alertService.error('Ошибка. ' + error._body));
  }
  roleSelected(user: User){
      console.log(user);
  }
  applyChangesToUser(){
    console.log(this.selectedUser);
    this.userService.update(this.selectedUser).subscribe(
        success =>{
          this.alertService.success('Изменения сохранены.');
          this.loadUsers();
        },
        error => {
          let msg = (error._body != '')? error._body : error;
                this.alertService.error('Ошибка. ' +msg);
        }
    );
    this.editUserModal.hide();
  }
  editUser(user: User){

      this.selectedUser = Object.assign({}, user);

    this.editUserModal.show();
  }
  deleteUser(id) {
    this.userService.delete(id).subscribe(
        success => {
          this.alertService.success('Пользователь удалён.');
          this.loadUsers();
        },
        error => {
          let msg = (error._body != '')? error._body : error;
                this.alertService.error('Ошибка. ' +msg);
        }
    );
  }
}
