"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
var index_1 = require('../../services/index');
var NavbarComponent = (function () {
    function NavbarComponent() {
        this.navbar = { "users": "Пользователи",
            "main": "Главная",
            "login": "Вход",
            "logout": "Выход",
            "feed": "Моё питание",
            "report_management": "Отчёты пользователей",
            "settings": "Настройки",
            "group_assignment": "Группы" };
    }
    NavbarComponent.prototype.userIsLoggedIn = function () {
        this.currentUser = index_1.UserService.getCurentUser();
        if (this.currentUser) {
            return true;
        }
        else {
            return false;
        }
    };
    NavbarComponent.prototype.toggleDropdown = function (event) {
        var target = event.target || event.srcElement || event.currentTarget;
        target.classList.toggle('opened');
    };
    NavbarComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'navbar',
            templateUrl: 'navbar.component.html',
            styleUrls: ['navbar.component.scss'],
        })
    ], NavbarComponent);
    return NavbarComponent;
}());
exports.NavbarComponent = NavbarComponent;
