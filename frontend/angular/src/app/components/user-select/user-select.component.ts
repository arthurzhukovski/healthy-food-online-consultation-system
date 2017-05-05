import {Component, OnInit, EventEmitter, Output, Input} from '@angular/core';

import { UserService, AlertService, GroupService } from '../../services/index';
import {User} from "../../models/user";

@Component({
    moduleId: module.id,
    selector: 'user-select',
    templateUrl: 'user-select.component.html',
    styleUrls: ['user-select.component.scss'],
})

export class UserSelectComponent {
    @Output() notify: EventEmitter<number> = new EventEmitter<number>();
    @Input() users: User[] = [];
    private  selectedUserId: number = 0;
    constructor(private userService: UserService, private alertService: AlertService) {
    }
    ngOnInit(){
        console.log(this.selectedUserId);
    }
    private notifyCoachChanged(){
        console.log('Selected: ' + this.selectedUserId);
        this.notify.emit(this.selectedUserId);
    }

}