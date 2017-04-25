"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var Config_1 = require('../../Config');
var core_1 = require('@angular/core');
var UserReportManagement = (function () {
    function UserReportManagement(reportService, groupService, userService, alertService) {
        this.reportService = reportService;
        this.groupService = groupService;
        this.userService = userService;
        this.alertService = alertService;
        this.model = {};
        this.imgPlaceholder = Config_1.Config.IMG_PLACEHOLDER;
        this.markViewClass = {
            "good": "check-circle",
            "neutral": "minus-circle",
            "bad": "times-circle"
        };
        this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    }
    UserReportManagement.prototype.ngOnInit = function () {
        this.loadAllGroupsAndReports();
    };
    UserReportManagement.prototype.rateReport = function (report) {
        var _this = this;
        this.reportService.update(report).subscribe(function (data) {
            _this.alertService.success('Report rated successfully', true);
        }, function (error) {
            _this.alertService.error(error);
        });
    };
    UserReportManagement.prototype.loadAllGroupsAndReports = function () {
        var _this = this;
        this.groupService.getAllByCoachId(this.currentUser.id)
            .subscribe(function (groups) {
            console.log(groups);
            _this.groups = groups;
            _this.userService.getAllUsersByGroups(groups).subscribe(function (users) {
                console.log(users);
                _this.users = users;
                _this.reportService.getAllByUsers(users).subscribe(function (reports) {
                    console.log(reports);
                    _this.dailyReports = reports;
                });
            });
        });
    };
    UserReportManagement.prototype.toggleContent = function (event) {
        console.log(this);
        $(this).classList.toggle('active');
        var content = $(this).siblings('.progress-line-wrapper');
        $(this).children('.fa').toggleClass('fa-angle-down');
        $(this).children('.fa').toggleClass('fa-angle-right');
        content.toggle(300);
    };
    UserReportManagement = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'user-report-feed',
            templateUrl: 'user-report-management.component.html',
            styleUrls: ['user-report-management.component.scss'],
        })
    ], UserReportManagement);
    return UserReportManagement;
}());
exports.UserReportManagement = UserReportManagement;
