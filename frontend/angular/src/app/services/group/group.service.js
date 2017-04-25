"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var Config_1 = require('../../Config');
var core_1 = require('@angular/core');
var index_1 = require('../index');
var GroupService = (function () {
    function GroupService(http) {
        this.http = http;
    }
    GroupService.prototype.getAll = function () {
        return this.http.get(Config_1.Config.BASE_API_URL + '/groups', index_1.UserService.jwt()).map(function (response) { return response.json(); });
    };
    GroupService.prototype.getById = function (id) {
        return this.http.get(Config_1.Config.BASE_API_URL + '/groups/' + id, index_1.UserService.jwt()).map(function (response) { return response.json(); });
    };
    GroupService.prototype.getAllByCoachId = function (coachId) {
        return this.http.get('/', index_1.UserService.jwt()).map(function (response) {
            return (JSON.parse('[{"id":"1", "coachId": "1", "stage": "1", "active": "1", "createdAt": "0000-00-00"},' +
                '{"id":"2", "coachId": "1", "stage": "2", "active": "1", "createdAt": "0000-00-00"},' +
                '{"id":"3", "coachId": "1", "stage": "1", "active": "1", "createdAt": "0000-00-00"}]'));
        });
        //return this.http.get(Config.BASE_API_URL + '/groups/coach/' + coachId, UserService.jwt()).map((response: Response) => response.json());
    };
    GroupService.prototype.create = function (group) {
    };
    GroupService.prototype.update = function (group) {
    };
    GroupService.prototype.delete = function (id) {
    };
    GroupService = __decorate([
        core_1.Injectable()
    ], GroupService);
    return GroupService;
}());
exports.GroupService = GroupService;
