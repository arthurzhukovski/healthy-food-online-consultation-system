import { Component, OnInit } from '@angular/core';

import { UserService } from '../../services/index';
import { User } from '../../models/index';

@Component({
    moduleId: module.id,
    selector: 'navbar',
    templateUrl: 'navbar.component.html',
    styleUrls: ['navbar.component.scss'],
})

export class NavbarComponent {

    private navbar = {"users":"Пользователи", "main":"Главная", "login":"Вход", "logout":"Выход", "feed":"Прогресс"};
    private currentUser: User;

    private userIsLoggedIn(){
        this.currentUser = UserService.getCurentUser();
        if (this.currentUser){
            return true;
        }
        else {
            return false;
        }
    }
    toggleDropdown(event){
        var target = event.target || event.srcElement || event.currentTarget;
        target.classList.toggle('opened');
    }
}