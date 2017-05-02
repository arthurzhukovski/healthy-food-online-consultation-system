import {Config} from '../../Config';
import { Component, OnInit } from '@angular/core';

import {AlertService} from  '../../services/index';
import { User } from '../../models/index';
import { Report } from '../../models/index';
import {ReportService} from "../../services/report/report.service";

@Component({
    moduleId: module.id,
    selector: 'user-report-feed',
    templateUrl: 'user-report-feed.component.html',
    styleUrls: ['user-report-feed.component.scss'],
})

export class UserReportFeedComponent {
    private model: any = {text: '', userId:0};
    private imgPlaceholder = Config.IMG_PLACEHOLDER;
    private imageToUpload;
    private imgPath = Config.BASE_API_URL+'/report/image/'
    private dailyReports: Report[];
    private currentUser: User;
    private markViewClass = {
                        "GOOD" :"check-circle",
                        "NEUTRAL" :"minus-circle",
                        "BAD" :"times-circle"
    };
    constructor(private reportService: ReportService, private alertService: AlertService) {
        this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    }

    ngOnInit() {
        this.loadAllReports();
        this.model.userId = this.currentUser.id;
    }

    addReport() {
        this.reportService.create(this.model).subscribe(
            data => {
                this.alertService.success('Upload successful', true);
                this.loadAllReports();
            },
            error => {
                this.alertService.error(error);
            });
    }

    private loadAllReports() {
        this.reportService.getAllByUserId(this.currentUser.id).subscribe(
            data => { console.log(data); this.dailyReports = data; },
            error => {
                    this.alertService.error(error);
            });
    }

    private uploadImage(event){
        var files = event.srcElement.files;
        this.imageToUpload = files[0];
        console.log(this.imageToUpload);

        this.reportService.uploadImage(this.imageToUpload).subscribe(
            data => {
                this.model.imageId = data;
                console.log(this.model.imageId);
            },
            error => {
                this.alertService.error(error);
            });

    }
}