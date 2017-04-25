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
    }

    applySettings(){
        console.log(this.newSettings);
    }



}