import {Config} from '../../Config';
import { Component, OnInit, Injectable } from '@angular/core';

import {AlertService} from  '../../services/index';
import { User,  Group } from '../../models/index';
import { Report } from '../../models/index'
import {UserService} from "../../services/user/user.service";
import {ReportService} from "../../services/report/report.service";
import {GroupService} from "../../services/group/group.service";

import * as $ from 'jquery';


@Component({
    moduleId: module.id,
    selector: 'user-report-feed',
    templateUrl: 'user-report-management.component.html',
    styleUrls: ['user-report-management.component.scss'],
})

export class UserReportManagement {
    private ratingReport: any = {};
    private dailyReports: Report[];
    private groups: Group[];
    private users: User[];
    private currentUser: User;

    private imgPlaceholder = Config.IMG_PLACEHOLDER;
    private markViewClass = {
                        "good" :"check-circle",
                        "neutral" :"minus-circle",
                        "bad" :"times-circle"
    };
    constructor(private reportService: ReportService, private groupService: GroupService, private userService: UserService, private alertService: AlertService) {
        this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    }

    ngOnInit() {
        this.loadAllGroupsAndReports();
    }

    rateReport(report: Report) {
        this.reportService.update(report).subscribe(
            data => {
                this.alertService.success('Report rated successfully', true);
            },
            error => {
                this.alertService.error(error);
            });
    }
    submitRatingForm(event){
        var $target = $(event.target || event.srcElement || event.currentTarget);
        let reportId = $target.siblings('.report-id').val();
        this.ratingReport.id = reportId;
        console.log(this.ratingReport);
        this.rateReport(this.ratingReport);

    }
    private loadAllGroupsAndReports() {
            this.groupService.getAllByCoachId(this.currentUser.id)
                .subscribe(groups => {
                    console.log(groups);
                    this.groups = groups;

                    this.userService.getAllUsersByGroups(groups).subscribe(users => {
                        console.log(users);
                        this.users = users;
                        this.reportService.getAllByUsers(users).subscribe(reports => {
                            console.log(reports);
                            this.dailyReports = reports;
                        });
                    });
                });
    }

    toggleContent(event){
        var $target = $(event.target || event.srcElement || event.currentTarget);
        console.log($target);
        $target.toggleClass('active');
        var content = $target.siblings('.accordion-content');
        console.log(content);
        $target.children('.fa').toggleClass('fa-angle-down');
        $target.children('.fa').toggleClass('fa-angle-right');
        content.toggle(300);

    }
}

