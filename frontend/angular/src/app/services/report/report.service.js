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
var ReportService = (function () {
    function ReportService(http) {
        this.http = http;
    }
    ReportService.prototype.getAll = function () {
        return this.http.get(Config_1.Config.BASE_API_URL + '/reports', index_1.UserService.jwt()).map(function (response) { return response.json(); });
    };
    ReportService.prototype.getById = function (id) {
        return this.http.get(Config_1.Config.BASE_API_URL + 'reports/' + id, index_1.UserService.jwt()).map(function (response) { return response.json(); });
    };
    ReportService.prototype.getAllByUserId = function (userId) {
        return this.http.get('/' /*+ userId*/, index_1.UserService.jwt()).map(function (response) {
            return (JSON.parse('[{"id":"1", "userId": "1", "content": "Я покушал", "grade": "good", "comment": "Хорошо", "createdAt": "0000-00-00"},' +
                '{"id":"2", "userId": "1", "content": "Я покушал", "grade": "good", "comment": "Молодец!", "createdAt": "0000-00-00"},' +
                '{"id":"3", "userId": "1", "content": "Я покушал", "grade": "empty", "comment": "", "createdAt": "0000-00-00"}]'));
        });
        //return this.http.get(Config.BASE_API_URL + '/reports/user/' + userId, UserService.jwt()).map((response: Response) => response.json());
    };
    ReportService.prototype.getAllByUsers = function (users) {
        var userIds = [];
        for (var i = 0; i < users.length; i++)
            userIds.push(users[i].id);
        return this.http.get('/' /*+ userId*/, index_1.UserService.jwt()).map(function (response) {
            return (JSON.parse('[{"id":"1", "userId": "1", "content": "Я покушал", "grade": "good", "comment": "Хорошо", "createdAt": "0000-00-00"},' +
                '{"id":"2", "userId": "2", "content": "Я покушал", "grade": "good", "comment": "Молодец!", "createdAt": "0000-00-00"},' +
                '{"id":"3", "userId": "3", "content": "Я покушал", "grade": "empty", "comment": "", "createdAt": "0000-00-00"}]'));
        });
        //return this.http.post(Config.BASE_API_URL +'/reports/groups', groupIds,  UserService.jwt()).map((response: Response) => response.json());
    };
    ReportService.prototype.create = function (report) {
        return this.http.get('/', index_1.UserService.jwt()).map(function (response) {
            return (JSON.parse('{"status": "OK"}'));
        });
        //return this.http.post(Config.BASE_API_URL + '/reports/', report, UserService.jwt()).map((response: Response) => response.json());
    };
    ReportService.prototype.update = function (report) {
        return this.http.put('/', index_1.UserService.jwt()).map(function (response) { return JSON.parse('success'); });
        //return this.http.put(Config.BASE_API_URL + '/reports/' + report.id, report, UserService.jwt()).map((response: Response) => response.json());
    };
    ReportService.prototype.delete = function (id) {
        return this.http.delete(Config_1.Config.BASE_API_URL + '/reports/' + id, index_1.UserService.jwt()).map(function (response) { return response.json(); });
    };
    ReportService = __decorate([
        core_1.Injectable()
    ], ReportService);
    return ReportService;
}());
exports.ReportService = ReportService;
