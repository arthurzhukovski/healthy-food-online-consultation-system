"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
var ArticleFeedComponent = (function () {
    function ArticleFeedComponent(userService, alertService) {
        this.userService = userService;
        this.alertService = alertService;
        this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    }
    ArticleFeedComponent.prototype.ngOnInit = function () {
        this.loadUsers();
    };
    ArticleFeedComponent.prototype.loadUsers = function () {
        var _this = this;
        this.userService.getAll().subscribe(function (data) {
            console.log(data);
            _this.users = data;
        }, function (error) {
            _this.alertService.error(error);
        });
    };
    ArticleFeedComponent.prototype.onSelectNotification = function (selectedGroup, user) {
        user.shadowGroup = selectedGroup;
    };
    ArticleFeedComponent.prototype.onGroupSubmit = function (user) {
        var userWithNewGroup = Object.assign({}, user);
        userWithNewGroup.groupId = userWithNewGroup.shadowGroup;
        delete userWithNewGroup.shadowGroup;
        this.applyNewGroup(userWithNewGroup);
    };
    ArticleFeedComponent.prototype.applyNewGroup = function (userWithNewGroup) {
        var _this = this;
        this.userService.update(userWithNewGroup).subscribe(function (data) {
            _this.alertService.success('User updated successfully', true);
            _this.loadUsers();
        }, function (error) {
            _this.alertService.error(error);
        });
    };
    ArticleFeedComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'group-assignment',
            templateUrl: 'article-feed.component.html',
            styleUrls: ['article-feed.component.scss']
        })
    ], ArticleFeedComponent);
    return ArticleFeedComponent;
}());
exports.ArticleFeedComponent = ArticleFeedComponent;
