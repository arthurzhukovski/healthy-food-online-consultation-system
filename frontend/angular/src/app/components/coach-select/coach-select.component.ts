import {Component, OnInit, EventEmitter, Output, Input} from '@angular/core';

import { UserService, AlertService, GroupService } from '../../services/index';

@Component({
    moduleId: module.id,
    selector: 'coach-select',
    templateUrl: 'coach-select.component.html',
    styleUrls: ['coach-select.component.scss'],
})

export class CoachSelectComponent {
    @Output() notify: EventEmitter<number> = new EventEmitter<number>();
    private coaches = [];
    private  selectedCoachId: number = 0;
    constructor(private userService: UserService, private alertService: AlertService) {
    }
    ngOnInit(){
        this.loadActiveGroups();
        console.log(this.selectedCoachId);
    }
    private notifyCoachChanged(){
        console.log('Selected: ' + this.selectedCoachId);
        this.notify.emit(this.selectedCoachId);
    }

    private loadActiveGroups() {
        this.userService.getAll()
            .subscribe(coaches => {
                console.log('Loaded from service: ' + coaches);
                this.coaches = coaches;
            })
    }
}