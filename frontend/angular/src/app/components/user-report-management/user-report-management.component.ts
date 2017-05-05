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
    private dailyReports: Report[];
    private groups: Group[];
    private users: User[];
    private currentUser: User;
    private imgPath = Config.BASE_API_URL+'/report/image/'
    private imgPlaceholder = Config.IMG_PLACEHOLDER;
    private markViewClass = {
                        "GOOD" :"check-circle",
                        "NEUTRAL" :"minus-circle",
                        "BAD" :"times-circle"
    };
    constructor(private reportService: ReportService, private groupService: GroupService, private userService: UserService, private alertService: AlertService) {
        this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    }

    ngOnInit() {
        this.loadAllGroupsAndReports();
    }

    rateReport(report: Report) {
        this.reportService.rateReport(report).subscribe(
            data => {
                this.alertService.success('Report rated successfully', true);
                this.loadReports();
            },
            error => {
                let msg = (error._body != '')? error._body : error;
                this.alertService.error('Ошибка. ' +msg);
            });
    }
    submitRatingForm(day){
        var ratedDay = Object.assign({}, day);
        ratedDay.grade = ratedDay.shadowMark;
        ratedDay.comment = ratedDay.shadowText;
        delete ratedDay.shadowMark;
        delete ratedDay.shadowText;
        delete day.shadowMark;
        delete day.shadowText;
        ratedDay.comment = {coach:{id: this.currentUser.id}, text: ratedDay.comment};
        console.log(ratedDay);
        this.rateReport(ratedDay);

    }
    private loadAllGroupsAndReports() {
            //this.groupService.getAllByCoachId(this.currentUser.id)
            this.groupService.getAllByCoachId(7)
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
                    }, error => {
                        let msg = (error._body != '')? error._body : error;
                this.alertService.error('Ошибка. ' +msg);
                    });
                });
    }
    private loadReports(){
        this.reportService.getAllByUsers(this.users).subscribe(reports => {
            console.log(reports);
            this.dailyReports = reports;
        },error => {
            let msg = (error._body != '')? error._body : error;
                this.alertService.error('Ошибка. ' +msg);
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

