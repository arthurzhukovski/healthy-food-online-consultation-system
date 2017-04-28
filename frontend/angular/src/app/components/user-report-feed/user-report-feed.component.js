"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var Config_1 = require('../../Config');
var core_1 = require('@angular/core');
var UserReportFeedComponent = (function () {
    function UserReportFeedComponent(reportService, alertService) {
        this.reportService = reportService;
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
    UserReportFeedComponent.prototype.ngOnInit = function () {
        this.loadAllReports();
        this.model.userId = this.currentUser.id;
    };
    UserReportFeedComponent.prototype.addReport = function () {
        var _this = this;
        this.reportService.create(this.model).subscribe(function (data) {
            _this.alertService.success('Upload successful', true);
        }, function (error) {
            _this.alertService.error(error);
        });
    };
    UserReportFeedComponent.prototype.loadAllReports = function () {
        var _this = this;
        this.reportService.getAllByUserId(this.currentUser.id).subscribe(function (reports) { console.log(reports); _this.dailyReports = reports; });
    };
    UserReportFeedComponent.prototype.uploadPhotos = function (event) {
        var files = event.srcElement.files;
        var fileArray = [];
        if (this.model.files === undefined)
            this.model.files = [];
        for (var i = 0; i < files.length; i++) {
            this.model.files.push(files[i]);
        }
        console.log(this.model.files);
    };
    UserReportFeedComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'user-report-feed',
            templateUrl: 'user-report-feed.component.html',
            styleUrls: ['user-report-feed.component.scss'],
        })
    ], UserReportFeedComponent);
    return UserReportFeedComponent;
}());
exports.UserReportFeedComponent = UserReportFeedComponent;
