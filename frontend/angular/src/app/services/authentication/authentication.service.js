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
require('rxjs/add/operator/map');
var AuthenticationService = (function () {
    function AuthenticationService(http) {
        this.http = http;
    }
    AuthenticationService.prototype.login = function (login, password) {
        var headers = new http_1.RequestOptions({
            headers: new http_1.Headers({ 'Content-Type': 'application/json' })
        });
        return this.http.post(Config_1.Config.BASE_API_URL + '/login', JSON.stringify({ login: login, password: password }), headers)
            .map(function (response) {
            var loginResponse = response.json();
            if (loginResponse.user && loginResponse.token) {
                localStorage.setItem('currentUser', JSON.stringify(loginResponse.user));
                localStorage.setItem('token', JSON.stringify(loginResponse.token.token));
            }
        });
    };
    AuthenticationService.prototype.logout = function () {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    };
    AuthenticationService = __decorate([
        core_1.Injectable()
    ], AuthenticationService);
    return AuthenticationService;
}());
exports.AuthenticationService = AuthenticationService;
