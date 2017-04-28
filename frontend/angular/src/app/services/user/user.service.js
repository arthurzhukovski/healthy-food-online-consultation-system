"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var Config_1 = require('../../Config');
var core_1 = require('@angular/core');
var http_1 = require('@angular/http');
var UserService = (function () {
    function UserService(http) {
        this.http = http;
    }
    UserService.prototype.getAll = function () {
        return this.http.get(Config_1.Config.BASE_API_URL + '/users', UserService.jwt()).map(function (response) { return JSON.parse('[{"groupId":"2", "id":7,"name":"Lenny","surname":"Carl","email":"lennyCarl@yandex.ru","confirmed":0,"registeredAt":1492355585000,"stage":0,"role":"USER","login":"Roman"},{"groupId":"3","id":11,"name":"123","surname":"123","email":"123","confirmed":0,"registeredAt":1492783626000,"stage":0,"role":"USER","login":"123"}]'); });
        //return this.http.get(Config.BASE_API_URL + '/users', UserService.jwt()).map((response: Response) => response.json());
    };
    UserService.prototype.getById = function (id) {
        return this.http.get('/api/users/' + id, UserService.jwt()).map(function (response) { return response.json(); });
    };
    UserService.prototype.create = function (user) {
        var headers = new http_1.RequestOptions({
            headers: new http_1.Headers({ 'Content-Type': 'application/json' })
        });
        var response = this.http.post(Config_1.Config.BASE_API_URL + '/users', user, headers).map(function (response) { return response.json(); });
        console.dir(response);
        console.dir(user);
        return response;
    };
    UserService.prototype.getAllUsersByGroups = function (groups) {
        var groupIds = [];
        for (var i = 0; i < groups.length; i++)
            groupIds.push(groups[i].id);
        return this.http.get('/', UserService.jwt()).map(function (response) {
            return (JSON.parse('[{"id":"1","groupId":"1","name":"Имя 1", "surname": "Фамилия 1", "email": "test@mail.test"},' +
                '{"id":"2","groupId":"2","name":"Имя 2", "surname": "Фамилия 2", "email": "test@mail.test"},' +
                '{"id":"3","groupId":"3","name":"Имя 3", "surname": "Фамилия 3", "email": "test@mail.test"}]'));
        });
        //return this.http.post(Config.BASE_API_URL +'/users/groups', groupIds,  UserService.jwt()).map((response: Response) => response.json());
    };
    UserService.prototype.update = function (user) {
        return this.http.put('/api/users/' + user.id, user, UserService.jwt()).map(function (response) { return response.json(); });
    };
    UserService.prototype.delete = function (id) {
        return this.http.delete('/api/users/' + id, UserService.jwt()).map(function (response) { return response.json(); });
    };
    UserService.jwt = function () {
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        var token = JSON.parse(localStorage.getItem('token'));
        if (currentUser && token) {
            var headers = new http_1.Headers({ 'Authorization': 'Bearer ' + token, 'Content-Type': 'application/json' });
            return new http_1.RequestOptions({ headers: headers });
        }
    };
    UserService.getCurentUser = function () {
        var user = localStorage.getItem('currentUser');
        if (user) {
            return JSON.parse(user);
        }
        else {
            return null;
        }
    };
    UserService = __decorate([
        core_1.Injectable()
    ], UserService);
    return UserService;
}());
exports.UserService = UserService;
