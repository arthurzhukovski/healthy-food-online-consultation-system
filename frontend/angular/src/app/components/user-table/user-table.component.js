"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
var user_table_data_service_1 = require("../../services/user-table-data/user-table-data.service");
var UserTableComponent = (function () {
    function UserTableComponent(userDataService) {
        this.userDataService = userDataService;
        this.mode = 'Observable';
    }
    UserTableComponent.prototype.ngOnInit = function () { this.getUsers(); };
    UserTableComponent.prototype.getUsers = function () {
        var _this = this;
        this.userDataService.getUsers()
            .subscribe(function (users) { return _this.users = users; }, function (error) { return _this.errorMessage = error; });
    };
    UserTableComponent = __decorate([
        core_1.Component({
            selector: 'app-user-table',
            providers: [user_table_data_service_1.UserTableDataService],
            templateUrl: 'user-table.component.html',
            styleUrls: ['user-table.component.scss']
        })
    ], UserTableComponent);
    return UserTableComponent;
}());
exports.UserTableComponent = UserTableComponent;
