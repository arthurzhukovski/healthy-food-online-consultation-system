import {Config} from '../../Config';
import { Component, OnInit } from '@angular/core';

import {AlertService} from  '../../services/index';
import { User } from '../../models/index';
import { Report } from '../../models/index';
import {ReportService} from "../../services/report/report.service";
import { FormsModule }        from '@angular/forms';
@Component({
    moduleId: module.id,
    selector: 'user-report-feed',
    templateUrl: 'user-report-feed.component.html',
    styleUrls: ['user-report-feed.component.scss'],
})

export class UserReportFeedComponent {
    private model: any = {};
    private imgPlaceholder = Config.IMG_PLACEHOLDER;
    private dailyReports: Report[];
    private currentUser: User;
    private newReport: Report;
    private markViewClass = {
                        "good" :"check-circle",
                        "neutral" :"minus-circle",
                        "bad" :"times-circle"
    };
    constructor(private reportService: ReportService, private alertService: AlertService) {
        this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    }

    ngOnInit() {
        this.loadAllReports();
    }

    addReport() {
        this.reportService.create(this.model).subscribe(
            data => {
                this.alertService.success('Upload successful', true);
            },
            error => {
                this.alertService.error(error);
            });
    }

    private loadAllReports() {
        this.reportService.getAllByUserId(this.currentUser.id).subscribe(reports => { console.log(reports); this.dailyReports = reports; });
    }

    private uploadPhotos(event){
        var files = event.srcElement.files;
        var fileArray = [];
        if (this.model.files === undefined)
            this.model.files = [];
        for (var i = 0; i < files.length; i++) {
            this.model.files.push(files[i]);
        }
        console.log(this.model.files);
    }
}