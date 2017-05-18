import { Component, OnInit } from '@angular/core';

import { UserService } from '../../services/index';
import { User } from '../../models/index';
import * as $ from 'jquery';

@Component({
    moduleId: module.id,
    selector: 'navbar',
    templateUrl: 'navbar.component.html',
    styleUrls: ['navbar.component.scss'],
})

export class NavbarComponent {
    constructor() {
        document.addEventListener('click', this.hideMenu.bind(this));
    }
    private navbar = {"users":"Пользователи",
                      "main":"Главная",
                      "articles":"Статьи",
                      "login":"Вход",
                      "logout":"Выход",
                      "feed":"Моё питание",
                      "report_management":"Отчёты пользователей",
                      "settings":"Настройки",
                        "group_assignment":"Группы",
                        "messages":"Сообщения",
                    "articles_create":"Создать статью"};
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
        event.stopPropagation();
        $('.side-menu').toggleClass('opened');
    }
    hideMenu(){
        $('.side-menu').removeClass('opened');
    }
}