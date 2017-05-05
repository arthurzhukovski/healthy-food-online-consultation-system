import { Component, OnInit } from '@angular/core';

import { AlertService } from '../../services/index';

@Component({
    moduleId: module.id,
    selector: 'alert',
    styleUrls: ['alert.component.scss'],
    templateUrl: 'alert.component.html'
})

export class AlertComponent {
    private messages: any[] = [];

    constructor(private alertService: AlertService) { }

    ngOnInit() {
        this.alertService.getMessage().subscribe(message => { this.messages.push(message)});
    }
    private onAlertClicked(event){
        event.target.remove();
    }
}