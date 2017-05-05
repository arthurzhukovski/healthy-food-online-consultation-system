import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AlertService, UserService } from '../../services/index';
import {User} from "../../models/user";

@Component({
    moduleId: module.id,
    templateUrl: 'register.component.html'
})

export class RegisterComponent {
    model: User = new User();
    loading = false;

    constructor(
        private router: Router,
        private userService: UserService,
        private alertService: AlertService) { }

    register() {
        this.loading = true;
        this.userService.create(this.model)
            .subscribe(
                data => {
                    this.alertService.success('Registration successful', true);
                    this.router.navigate(['/login']);
                },
                error => {
                    this.alertService.error('Ошибка. ' + error._body);
                    this.loading = false;
                });
    }
}
