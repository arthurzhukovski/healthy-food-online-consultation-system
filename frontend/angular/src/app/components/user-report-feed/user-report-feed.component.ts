import {Config} from '../../Config';
import { Component, OnInit } from '@angular/core';

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
    private imgPlaceholder = Config.IMG_PLACEHOLDER;
    private dailyReports: Report[];
    private currentUser: User;
    private newReport: Report;
    private markViewClass = {
                        "good" :"check-circle",
                        "neutral" :"minus-circle",
                        "bad" :"times-circle"
    };
    constructor(private reportService: ReportService) {
        this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    }

    ngOnInit() {
        this.loadAllReports();
    }

    addReport(newReport: Report) {
        this.reportService.create(newReport).subscribe(() => { this.loadAllReports() });
    }

    private loadAllReports() {
        this.reportService.getAllByUserId(this.currentUser.id).subscribe(reports => { console.log(reports);this.dailyReports = reports; });
    }
}