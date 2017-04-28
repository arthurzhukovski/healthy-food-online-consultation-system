"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
var UserSettingsComponent = (function () {
    function UserSettingsComponent(userService, alertService) {
        this.userService = userService;
        this.alertService = alertService;
        this.newSettings = {};
        this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    }
    UserSettingsComponent.prototype.applySettings = function () {
        console.log(this.newSettings);
    };
    UserSettingsComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'user-settings',
            templateUrl: 'user-settings.component.html',
            styleUrls: ['user-settings.component.scss'],
        })
    ], UserSettingsComponent);
    return UserSettingsComponent;
}());
exports.UserSettingsComponent = UserSettingsComponent;
