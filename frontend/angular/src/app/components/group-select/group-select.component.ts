import {Component, OnInit, EventEmitter, Output, Input} from '@angular/core';

import { UserService, AlertService, GroupService } from '../../services/index';

@Component({
    moduleId: module.id,
    selector: 'group-select',
    templateUrl: 'group-select.component.html',
    styleUrls: ['group-select.component.scss'],
})

export class GroupSelectComponent {
    @Output() notify: EventEmitter<number> = new EventEmitter<number>();
    @Input() groups = [];
    @Input()  selectedGroupId: number = 0;
    constructor(private userService: UserService, private alertService: AlertService, private groupService: GroupService) {
    }
    ngOnInit(){
        this.loadActiveGroups();
        console.log(this.selectedGroupId);
    }
    private notifyGroupChanged(){
        console.log('Selected: ' + this.selectedGroupId);
        this.notify.emit(this.selectedGroupId);
    }

    private loadActiveGroups() {
        this.groupService.getAll()
            .subscribe(groups => {
                console.log('Loaded from service: ' + groups);
                this.groups = groups;
            })
    }
}