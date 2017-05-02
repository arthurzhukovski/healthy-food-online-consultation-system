import { Component, OnInit } from '@angular/core';

import { UserService, AlertService } from '../../services/index';
import { User } from '../../models/index';

@Component({
    moduleId: module.id,
    selector: 'user-settings',
    templateUrl: 'user-settings.component.html',
    styleUrls: ['user-settings.component.scss'],
})

export class UserSettingsComponent {
    private newSettings: any = {};
    private currentUser: User;
    constructor(private userService: UserService, private alertService: AlertService) {
        this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
        this.newSettings =  Object.assign(this.currentUser, {});
    }

    applySettings(){
        console.log(this.newSettings);
        this.userService.update(this.newSettings).subscribe(
            success =>{
                this.alertService.success('Изменения сохранены.');
                this.refreshLocalStorage();
            },
            error => {
                this.alertService.error('Не удалось применить изменения. ' + error);
            }
        );
    }

    refreshLocalStorage(){
        this.userService.getById(this.currentUser.id).subscribe(
            data =>{
                localStorage.setItem('currentUser', JSON.stringify(data));
                this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
                this.newSettings =  Object.assign(this.currentUser, {});
            },
            error => {
                this.alertService.error('Не удалось загрузить информацию о пользователе. ' + error);
            });
    }



}